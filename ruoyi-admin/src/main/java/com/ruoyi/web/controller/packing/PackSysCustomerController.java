package com.ruoyi.web.controller.packing;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.packing.domain.PackSysCustomer;
import com.ruoyi.packing.service.IPackSysCustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 包装校验客户信息Controller
 *
 * @author Dandan
 * @date 2023/9/8 19:43
 **/
@Api(tags = "包装校验客户信息相关接口")
@RestController
@RequestMapping("/packing/customer")
public class PackSysCustomerController extends BaseController {
    @Autowired
    private IPackSysCustomerService packSysCustomerService;

    /**
     * 新增包装校验客户信息
     */
    @ApiOperation("新增包装校验客户信息")
    @Log(title = "包装校验客户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PackSysCustomer packSysCustomer) {
        packSysCustomer.setCreateBy(getUsername());
        return toAjax(packSysCustomerService.insertPackSysCustomer(packSysCustomer));
    }

    /**
     * 查询所有客户信息
     */
    @ApiOperation("查询所有客户信息")
    @GetMapping("/all")
    public AjaxResult selectAllPackSysCustomer() {
        return success(packSysCustomerService.selectAllPackSysCustomer());
    }

}
