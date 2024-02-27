package com.ruoyi.web.controller.lab;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lab.domain.LabSysMileage;
import com.ruoyi.lab.domain.dto.LabSysMileageInsertDto;
import com.ruoyi.lab.service.ILabSysMileageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dandan
 * @date 2023/8/4 18:48
 **/
@Api(tags = "里程相关接口")
@RestController
@RequestMapping("/lab/mileage")
public class LabSysMileageController extends BaseController {

    @Autowired
    private ILabSysMileageService labSysMileageService;

    /**
     * lab_sys_mileage表中最大的主键ID
     */
    @ApiOperation("获取里程最大的主键ID")
    @GetMapping("/getMaxId")
    public AjaxResult getMaxMileageId() {
        return success(labSysMileageService.getLabSysMileageMaxMileageId());
    }

    /**
     * 通过里程ID批量删除里程及其关联信息
     */
    @ApiOperation("通过里程ID删除里程及关联信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:mileage:remove')")
    @Log(title = "里程", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] ids) {
        return toAjax(labSysMileageService.deleteLabSysMileageByMileageIds(ids));
    }

    /**
     * 通过stage查询关联下的里程
     */
    @ApiOperation("通过stage查询关联下的里程")
    @GetMapping
    public AjaxResult searchAllMileage(String stage) {
        return success(labSysMileageService.searchAllMileage(stage));
    }

    /**
     * 修改里程及关联信息
     */
    @ApiOperation("修改里程及关联信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:mileage:edit')")
    @Log(title = "里程", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult updateMileage(@RequestBody LabSysMileage labSysMileage) {
        labSysMileage.setUpdateBy(getUsername());
        return toAjax(labSysMileageService.updateLabSysMileage(labSysMileage));
    }

    /**
     * 新增里程及关联数据信息
     */
    @ApiOperation("新增里程及关联数据信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:mileage:add')")
    @Log(title = "里程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addMileage(@RequestBody LabSysMileageInsertDto labSysMileage) {
        labSysMileage.setCreateBy(getUsername());
        return toAjax(labSysMileageService.insertLabSysMileage(labSysMileage));
    }

    /**
     * 通过里程Id查询里程信息
     */
    @ApiOperation("通过里程ID查询里程")
    @PreAuthorize("@ss.hasPermi('lab:basic:mileage:query')")
    @GetMapping("/query")
    public AjaxResult queryMileageByMileageId(Long mileageId) {
        return success(labSysMileageService.selectLabSysMileageByMileageId(mileageId));
    }

    /**
     * 通过里程关联查询所有阶段
     */
    @ApiOperation("通过里程关联查询所有阶段")
    @GetMapping("/all")
    public AjaxResult selectAllMileage(String stage) {
        return success(labSysMileageService.selectAllMileage(stage));
    }

}
