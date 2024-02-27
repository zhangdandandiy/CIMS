package com.ruoyi.web.controller.packing;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.packing.domain.PackSysModel;
import com.ruoyi.packing.service.IPackSysModelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dandan
 * @date 2023/9/8 20:57
 **/

@Api(tags = "包装校验机种信息相关接口")
@RestController
@RequestMapping("/packing/model")
public class PackSysModelController extends BaseController {
    @Autowired
    private IPackSysModelService packSysModelService;

    /**
     * 新增包装校验机种信息
     */
    @ApiOperation("新增包装校验机种信息")
    @Log(title = "包装校验机种信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PackSysModel packSysModel) {
        packSysModel.setCreateBy(getUsername());
        return toAjax(packSysModelService.insertPackSysModel(packSysModel));
    }

    /**
     * 查询所有机种信息
     */
    @ApiOperation("查询所有机种信息")
    @GetMapping("/all")
    public AjaxResult selectAllPackSysModel() {
        return success(packSysModelService.selectAllPackSysModel());
    }

    /**
     * 获取包装校验机种信息详细信息
     */
    @ApiOperation("获取包装校验机种信息详细信息")
    @GetMapping("/info")
    public AjaxResult getInfo(String modelName) {
        return success(packSysModelService.selectPackSysModelByModelName(modelName));
    }

}
