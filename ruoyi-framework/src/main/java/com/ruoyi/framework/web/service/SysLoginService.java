package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserInfoNoSyncException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 登录校验方法
 *
 * @author Dandan
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysDeptRoleMapper sysDeptRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private RestTemplate restTemplate;

    // 是否允许账户多终端同时登录（true允许 false不允许）
    @Value("${token.soloLogin}")
    private boolean soloLogin;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {

        // 验证码校验
        validateCaptcha(username, code, uuid);
        // 登录前置校验
        loginPreCheck(username, password);
        // 用户验证
        Authentication authentication;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            // 调用身份验证流程
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用 UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                // 用户密码不正确或不符合规范
                // 异步执行
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        } finally {
            AuthenticationContextHolder.clearContext();
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 判断是否允许账户多终端同时登录
        if (!soloLogin) {
            // 如果用户不允许多终端同时登录，清除缓存信息
            String userIdKey = Constants.LOGIN_USERID_KEY + loginUser.getUser().getUserId();
            String userKey = redisCache.getCacheObject(userIdKey);
            if (StringUtils.isNotEmpty(userKey)) {
                redisCache.deleteObject(userIdKey);
                redisCache.deleteObject(userKey);
            }
        }
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        if (captchaEnabled) {
            String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
            String captcha = redisCache.getCacheObject(verifyKey);
            redisCache.deleteObject(verifyKey);
            if (captcha == null) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
                throw new CaptchaExpireException();
            }
            // 忽略大小写比较
            if (!code.equalsIgnoreCase(captcha)) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
                throw new CaptchaException();
            }
        }
    }

    /**
     * 登录前置校验
     *
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password) {
        SysCheckUser sysCheckUser = new SysCheckUser(username, password);
        boolean isSuccess = validateUser(sysCheckUser);
        // 如果用户是admin，不需要做此校验
        if (!username.equals("admin")) {
            // 用户名||密码错误抛出异常
            if (!isSuccess) {
                throw new UserPasswordNotMatchException();
            } else { // 用户名和密码一定是正确的
                // 先查有没有这个用户
                SysUser user = sysUserMapper.selectUserByUserName(username);
                if (user != null) {
                    // 有这个用户，校验密码
                    if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
                        // 密码不相同，重置数据库中的密码
                        userService.resetUserPwd(username, SecurityUtils.encryptPassword(password));
                    }
                } else {
                    // 没有这个用户说明是第一次登录，需要插入数据库表
                    SysUser newUser = new SysUser();
                    newUser.setUserName(username);
                    newUser.setPassword(SecurityUtils.encryptPassword(password));
                    // 查询用户的部门编号
                    String deptCode = sysUserMapper.getUserDeptCode(username);
                    // 根据部门编号查询部门ID(查不到部门的情况暂时不考虑)
                    Long deptId = sysDeptMapper.getDeptId(deptCode);
                    newUser.setDeptId(deptId);
                    // 根据工号查询姓名
                    String nickName = sysUserMapper.getUserNickName(username);
                    newUser.setNickName(nickName);
                    userService.insertUser(newUser);

                    // 插入完才知道 用户id
                    Long userId = sysUserMapper.getSysUserId(username);

                    // 插入用户角色信息
                    Long roleId = sysDeptRoleMapper.getSysUserRoleId(deptId);
                    SysUserRole sysUserRole = new SysUserRole();
                    sysUserRole.setRoleId(roleId);
                    sysUserRole.setUserId(userId);
                    sysUserRoleMapper.insertUserRole(sysUserRole);

                }
            }
        }

        // 同步信息
        if (!syncInfo(username, password)) {
            throw new UserInfoNoSyncException();
        }

    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtils.getIpAddr());
        sysUser.setLoginDate(DateUtils.getNowDate());
        userService.updateUserProfile(sysUser);
    }

    /**
     * 根据用户名密码校验用户
     *
     * @return
     */
    public boolean validateUser(SysCheckUser sysCheckUser) {
        // 请求路径
        String RequestUrl = "https://m.luxshare-ict.com/api/Account/CheckUser";
        HttpHeaders headers = new HttpHeaders();
        // 创建HttpEntity来包装请求体和请求头
        HttpEntity<Object> requestEntity = new HttpEntity<>(sysCheckUser, headers);
        RetryTemplate retryTemplate = new RetryTemplate();
        LinkedHashMap response = retryTemplate.execute(context -> restTemplate.exchange(RequestUrl, HttpMethod.POST, requestEntity, LinkedHashMap.class).getBody());
        return (Boolean) response.get("IsSuccess");
    }

    /**
     * 同步信息
     */
    public boolean syncInfo(String username, String password) {
        // 根据用户名查询用户信息
        SysUser sysUser = sysUserMapper.selectUserByUserName(username);
        // 部门信息
        SysDept sysDept = sysUser.getDept();
        // 部门 Id
        Long deptId = sysDept.getDeptId();
        // 角色 key 一个用户可能拥有多个角色
        List<String> roleKeys = sysRoleMapper.getSysRoleKeyByUserName(username, deptId);

        // 用户信息同步
        boolean syncUserResult = syncUser(username, password, deptId.toString(), String.join(",", roleKeys));
        // 角色权限同步
        boolean syncRolePermissionResult = syncRolePermission(roleKeys);
        // 部门权限同步
        boolean syncDeptPermissionResult = syncDeptPermission(deptId);
        // 用户角色同步
        boolean syncRoleResult = syncRole(username, deptId);
        // 用户部门同步
        boolean syncDeptResult = syncDept(sysDept);

        return syncUserResult && syncRolePermissionResult && syncDeptPermissionResult && syncRoleResult && syncDeptResult;

    }

    /**
     * 同步用户信息
     *
     * @param password        用户明文密码
     * @param username        用户名
     * @param selectedDeparts 所属部门id，多个用半角逗号隔开
     * @param selectedRoles   所属角色id，多个用半角逗号隔开
     */
    public boolean syncUser(String username, String password, String selectedDeparts, String selectedRoles) {
        SysSyncUser sysSyncUser = new SysSyncUser();
        String nickName = sysUserMapper.getUserNickName(username);
        sysSyncUser.setUsername(username);
        sysSyncUser.setRealname(nickName);
        sysSyncUser.setPassword(password);
        sysSyncUser.setSelecteddeparts(selectedDeparts);
        sysSyncUser.setSelectedroles(selectedRoles);

        List<SysSyncUser> sysSyncUsers = new ArrayList<>();
        sysSyncUsers.add(sysSyncUser);

        String url = "http://10.52.6.61:80/app/push/user";
        String result = HttpUtils.sendPost(url, sysSyncUsers);
        return result.contains("\"success\":true");
    }

    /**
     * 同步案例库角色权限
     */
    public boolean syncRolePermission(List<String> roleKeys) {
        List<SysSyncRolePermission> sysSyncRolePermissions = new ArrayList<>();
        SysSyncRolePermission sysSyncRolePermission = new SysSyncRolePermission();
        for (String roleKey : roleKeys) {
            List<String> permissions = roleKey.equals("superAdmin")
                    ? sysMenuMapper.getAllRoleMenuPre()
                    : sysMenuMapper.getRoleMenuPre(roleKey);
            sysSyncRolePermission.setPermissionIds(String.join(",", permissions));
            sysSyncRolePermission.setRoleId(roleKey);
            sysSyncRolePermissions.add(sysSyncRolePermission);
        }
        String url = "http://10.52.6.61:80/app/push/rolepermiss";
        String result = HttpUtils.sendPost(url, sysSyncRolePermissions);
        return result.contains("\"success\":true");
    }

    /**
     * 同步部门权限
     * 一个用户只能有一个部门
     */
    public boolean syncDeptPermission(Long deptId) {
        SysSyncDeptPermission sysSyncDeptPermission = new SysSyncDeptPermission();
        List<String> permissions = sysMenuMapper.getDeptMenuPre(deptId);
        sysSyncDeptPermission.setDepartId(deptId.toString());
        sysSyncDeptPermission.setPermissionIds(String.join(",", permissions));

        List<SysSyncDeptPermission> sysSyncDeptPermissions = new ArrayList<>();
        sysSyncDeptPermissions.add(sysSyncDeptPermission);

        String url = "http://10.52.6.61:80/app/push/departpermiss";
        String result = HttpUtils.sendPost(url, sysSyncDeptPermissions);
        return result.contains("\"success\":true");
    }

    /**
     * 同步角色
     * 一个用户有多个角色
     */
    public boolean syncRole(String username, Long deptId) {
        List<SysSyncRole> sysSyncRoles = sysRoleMapper.getSysRoleByUserName(username, deptId);
        String url = "http://10.52.6.61:80/app/push/role";
        String result = HttpUtils.sendPost(url, sysSyncRoles);
        return result.contains("\"success\":true");
    }

    /**
     * 同步部门
     * 先要同步上级部门
     * 祖级列表中处理 0 的都要先同步
     */
    public boolean syncDept(SysDept sysDept) {
        List<SysSyncDept> sysSyncDepts = new ArrayList<>();
        String ancestors = sysDept.getAncestors();

        if (ancestors != null) {
            String[] ancestorsList = ancestors.split(",");
            for (String deptId : ancestorsList) {
                if (!deptId.equals("0")) {
                    SysDept sysDeptParent = sysDeptMapper.selectDeptById(Long.valueOf(deptId));
                    if (sysDeptParent != null) {
                        sysSyncDepts.add(createSyncDept(sysDeptParent));
                    }
                }
            }
        }

        sysSyncDepts.add(createSyncDept(sysDept));

        String url = "http://10.52.6.61:80/app/push/depart";
        String result = HttpUtils.sendPost(url, sysSyncDepts);
        return result.contains("\"success\":true");
    }

    private SysSyncDept createSyncDept(SysDept sysDept) {
        SysSyncDept sysSyncDept = new SysSyncDept();
        sysSyncDept.setId(sysDept.getDeptId().toString());
        sysSyncDept.setParentId(sysDept.getParentId().toString().equals("0") ? null : sysDept.getParentId().toString());
        sysSyncDept.setDepartName(sysDept.getDeptName());
        sysSyncDept.setDepartOrder(sysDept.getOrderNum());
        return sysSyncDept;
    }

}
