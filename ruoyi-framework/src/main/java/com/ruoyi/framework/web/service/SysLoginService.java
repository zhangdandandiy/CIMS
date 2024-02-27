package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.user.CaptchaException;
import com.ruoyi.common.exception.user.CaptchaExpireException;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.framework.security.context.AuthenticationContextHolder;
import com.ruoyi.system.domain.SysCheckUser;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserMapper;
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
import java.util.LinkedHashMap;

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
    private RestTemplate restTemplate;

    // 请求路径
    private static final String RequestUrl = "https://m.luxshare-ict.com/api/Account/CheckUser";

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
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
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
                // 1、先查有没有这个用户
                SysUser user = sysUserMapper.selectUserByUserName(username);
                if (user != null) {
                    // 有这个用户，校验密码
                    if (!SecurityUtils.matchesPassword(password, user.getPassword())) {
                        // 密码不相同，重置数据库中的密码
                        userService.resetUserPwd(username, SecurityUtils.encryptPassword(password));
                    }
                } else {
                    // 没有这个用户说明是第一次登录，需要插入数据库表
                    user.setUserName(username);
                    user.setPassword(SecurityUtils.encryptPassword(password));
                    // 查询用户的部门编号
                    String deptCode = sysUserMapper.getUserDeptCode(username);
                    // 根据部门编号查询部门ID(查不到部门的情况暂时不考虑)
                    Long deptID = sysDeptMapper.getDeptId(deptCode);
                    user.setDeptId(deptID);
                    userService.insertUser(user);
                }
            }
        }
//        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
//                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
//            throw new UserPasswordNotMatchException();
//        }
        // 用户名或密码为空 错误
//        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("not.null")));
//            throw new UserNotExistsException();
//        }
//        // 用户名不在指定范围内 错误
//        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
//                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
//            throw new UserPasswordNotMatchException();
//        }
//        // IP黑名单校验
//        String blackStr = configService.selectConfigByKey("sys.login.blackIPList");
//        if (IpUtils.isMatchedIp(blackStr, IpUtils.getIpAddr())) {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("login.blocked")));
//            throw new BlackListException();
//        }
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
        HttpHeaders headers = new HttpHeaders();
        // 创建HttpEntity来包装请求体和请求头
        HttpEntity<Object> requestEntity = new HttpEntity<>(sysCheckUser, headers);
        RetryTemplate retryTemplate = new RetryTemplate();
        LinkedHashMap response = retryTemplate.execute(context -> restTemplate.exchange(RequestUrl, HttpMethod.POST, requestEntity, LinkedHashMap.class).getBody());
        return (Boolean) response.get("IsSuccess");
    }

}
