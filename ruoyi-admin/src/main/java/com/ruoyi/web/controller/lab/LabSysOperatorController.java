package com.ruoyi.web.controller.lab;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lab.domain.LabSysOperator;
import com.ruoyi.lab.service.ILabSysOperatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 责任人 Controller
 *
 * @author Dandan
 * @date 2023-08-04
 */
@Api(tags = "责任人信息相关接口")
@RestController
@RequestMapping("/lab/operator")
public class LabSysOperatorController extends BaseController {
    @Autowired
    private ILabSysOperatorService labSysOperatorService;

    /**
     * 查询责任人列表
     */
    @ApiOperation("查询责任人列表")
    @GetMapping("/list")
    public AjaxResult list(LabSysOperator labSysOperator) {
        return success(labSysOperatorService.selectLabSysOperatorList(labSysOperator));
    }

    /**
     * 获取责任人及其关联信息
     */
    @ApiOperation("获取责任人及其关联信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:oper:query')")
    @GetMapping("/getInfo")
    public AjaxResult getInfo(Long operatorId) {
        return success(labSysOperatorService.selectLabSysOperatorByOperatorId(operatorId));
    }

    /**
     * 新增责任人及其关联信息
     */
    @ApiOperation("新增责任人及其关联信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:oper:add')")
    @Log(title = "责任人信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LabSysOperator labSysOperator) {
        labSysOperator.setCreateBy(getUsername());
        return toAjax(labSysOperatorService.insertLabSysOperator(labSysOperator));
    }

    /**
     * 修改责任人及其关联信息
     */
    @ApiOperation("修改责任人及其关联信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:oper:edit')")
    @Log(title = "责任人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LabSysOperator labSysOperator) {
        labSysOperator.setUpdateBy(getUsername());
        return toAjax(labSysOperatorService.updateLabSysOperator(labSysOperator));
    }

    /**
     * 删除责任人及其关联信息
     */
    @ApiOperation("删除责任人及其关联信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:oper:remove')")
    @Log(title = "责任人信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] operatorIds) {
        return toAjax(labSysOperatorService.deleteLabSysOperatorByOperatorIds(operatorIds));
    }

    /**
     * 获取测试类型查询所有相关联的责任人
     */
    @ApiOperation("获取测试类型所有关联责任人")
    @GetMapping("/all")
    public AjaxResult searchAllOperator(String testType) {
        return success(labSysOperatorService.searchAllOperator(testType));
    }
}
