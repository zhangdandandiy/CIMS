package com.ruoyi.web.controller.packing;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.packing.domain.PackSysIdentify;
import com.ruoyi.packing.domain.PackSysInfo;
import com.ruoyi.packing.service.IPackSysIdentifyService;
import com.ruoyi.packing.service.IPackSysInfoService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Dandan
 * @date 2023/9/9 21:23
 **/
@Api(tags = "包装校验身份认证相关接口")
@RestController
@RequestMapping("/packing/identify")
public class PackSysIdentifyController extends BaseController {
    @Autowired
    private IPackSysIdentifyService packSysIdentifyService;

    @Autowired
    private IPackSysInfoService packSysInfoService;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询包装校验认证列表
     */
    @ApiOperation("查询包装校验认证列表")
    @GetMapping("/list")
    public TableDataInfo list(PackSysIdentify packSysIdentify) {
        startPage();
        List<PackSysIdentify> list = packSysIdentifyService.selectPackSysIdentifyList(packSysIdentify);
        return getDataTable(list);
    }

    /**
     * 企业微信发送消息
     */
    @ApiOperation("企业微信发送消息")
    @PostMapping("/sendMsg")
    public String sendMsg(Long processId, String typeCode) {
        String url = "https://m.luxshare-ict.com/api/WorkWeChat/SendTextMessage";

        Integer SendApp = 2;
        String EmpCodes = getUsername();

        PackSysInfo packSysInfo = packSysInfoService.selectPackSysInfoByProcessId(processId);

        String productInfoCode = packSysInfo.getProductInfoCode();
        String workShopMsg = "车间：" + packSysInfo.getWorkshop() + "\n";
        String customerMsg = "客户：" + packSysInfo.getCustomerName() + "\n";
        String modelMsg = "机种：" + packSysInfo.getModel() + "\n";
        String pnMsg = "P/N：" + packSysInfo.getProductInfoCode() + "\n";
        String productNameMsg = "名称：" + packSysInfo.getProductName() + "\n";
        String warnMsg = "报警信息：" + typeCode + " 与设置料号 " + productInfoCode + " 不符，存在混料风险，现场已报警拦截";

        String totalMsg = workShopMsg + customerMsg + modelMsg + pnMsg + productNameMsg + warnMsg;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 构建请求体
        String postData = String.format("{\"SendApp\":\"%s\",\"Account\":\"%s\",\"Password\":\"%s\",\"EmpCodes\":\"%s\",\"Content\":\"%s\"}", SendApp, "12213243", "7kcH8S0Bt&^buGtS", EmpCodes, totalMsg);

        HttpEntity<String> entity = new HttpEntity<>(postData, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        return response.getBody();
    }

    /**
     * 导出包装校验认证列表
     */
    @ApiOperation("导出包装校验认证列表")
    @Log(title = "包装校验认证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackSysIdentify packSysIdentify) {
        List<PackSysIdentify> list = packSysIdentifyService.selectPackSysIdentifyList(packSysIdentify);
        ExcelUtil<PackSysIdentify> util = new ExcelUtil<PackSysIdentify>(PackSysIdentify.class);
        util.exportExcel(response, list, "包装校验认证数据");
    }

    /**
     * 获取包装校验认证详细信息
     */
    @ApiOperation("获取包装校验认证详细信息")
    @GetMapping(value = "/{identifyId}")
    public AjaxResult getInfo(@PathVariable("identifyId") Long identifyId) {
        return success(packSysIdentifyService.selectPackSysIdentifyByIdentifyId(identifyId));
    }

    /**
     * 新增包装校验认证
     */
    @ApiOperation("新增包装校验认证")
    @Log(title = "包装校验认证", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PackSysIdentify packSysIdentify) {
        AjaxResult ajax = AjaxResult.success();
        packSysIdentify.setCreateBy(getUsername());
        String identifyResult = "NG";
        String userName = packSysIdentify.getUserName();
        String userPassword = packSysIdentify.getPassword();

        SysUser sysUser = sysUserService.selectUserByUserName(userName);

        if (SecurityUtils.matchesPassword(userPassword, sysUser.getPassword())) {
            identifyResult = "OK";
        }
        packSysIdentify.setIdentifyResult(identifyResult);
        packSysIdentify.setPassword(userName);
        packSysIdentifyService.insertPackSysIdentify(packSysIdentify);
        ajax.put("data", identifyResult);
        return ajax;
    }

    /**
     * 修改包装校验认证
     */
    @ApiOperation("修改包装校验认证")
    @Log(title = "包装校验认证", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PackSysIdentify packSysIdentify) {
        packSysIdentify.setUpdateBy(getUsername());
        return toAjax(packSysIdentifyService.updatePackSysIdentify(packSysIdentify));
    }

    /**
     * 删除包装校验认证
     */
    @ApiOperation("删除包装校验认证")
    @Log(title = "包装校验认证", businessType = BusinessType.DELETE)
    @DeleteMapping("/{identifyIds}")
    public AjaxResult remove(@PathVariable Long[] identifyIds) {
        return toAjax(packSysIdentifyService.deletePackSysIdentifyByIdentifyIds(identifyIds));
    }
}
