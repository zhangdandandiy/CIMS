package com.ruoyi.web.controller.lab;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.lab.domain.LabSysTestType;
import com.ruoyi.lab.domain.dto.LabSysTestOperSearchDto;
import com.ruoyi.lab.service.ILabSysTestTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 测试类型 Controller
 *
 * @author Dandan
 * @date 2023-08-03
 */
@Api(tags = "测试类型相关接口")
@RestController
@RequestMapping("/lab/type")
public class LabSysTestTypeController extends BaseController {
    @Autowired
    private ILabSysTestTypeService labSysTestTypeService;

    /**
     * 查询测试类型列表
     */
    @ApiOperation("查询测试类型列表")
    @GetMapping("/list")
    public AjaxResult list(LabSysTestType labSysTestType) {
        List<LabSysTestType> list = labSysTestTypeService.selectLabSysTestTypeList(labSysTestType);
        return success(list);
    }

    /**
     * 导出测试类型及其相关负责人列表
     */
    @ApiOperation("导出测试类型及其相关负责人列表")
    @PreAuthorize("@ss.hasPermi('lab:basic:type:export')")
    @Log(title = "测试类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LabSysTestOperSearchDto labSysTestType) {
        List<LabSysTestType> list = labSysTestTypeService.selectLabSysTestTypeOperatorList(labSysTestType);
        ExcelUtil<LabSysTestType> util = new ExcelUtil<>(LabSysTestType.class);
        util.exportExcel(response, list, "测试类型数据");
    }

    /**
     * 根据测试类型ID获取测试类型及其相关负责人详细信息
     */
    @ApiOperation("根据测试类型ID获取测试类型及其相关负责人详细信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:type:query')")
    @GetMapping("/searchInfo")
    public AjaxResult getInfo(Long testTypeId) {
        return success(labSysTestTypeService.selectLabSysTestTypeByTestTypeId(testTypeId));
    }

    /**
     * 新增测试类型及其相关负责人
     */
    @ApiOperation("新增测试类型及其相关负责人")
    @PreAuthorize("@ss.hasPermi('lab:basic:type:add')")
    @Log(title = "测试类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody LabSysTestType labSysTestType) {
        labSysTestType.setCreateBy(getUsername());
        return toAjax(labSysTestTypeService.insertLabSysTestType(labSysTestType));
    }

    /**
     * 修改测试类型及其相关负责人
     */
    @ApiOperation("修改测试类型及其相关负责人")
    @PreAuthorize("@ss.hasPermi('lab:basic:type:edit')")
    @Log(title = "测试类型", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody LabSysTestType labSysTestType) {
        labSysTestType.setUpdateBy(getUsername());
        return toAjax(labSysTestTypeService.updateLabSysTestType(labSysTestType));
    }

    /**
     * 删除测试类型及其相关负责人
     */
    @ApiOperation("删除测试类型及其相关负责人")
    @PreAuthorize("@ss.hasPermi('lab:basic:type:remove')")
    @Log(title = "测试类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] testTypeIds) {
        return toAjax(labSysTestTypeService.deleteLabSysTestTypeByTestTypeIds(testTypeIds));
    }

    /**
     * 查询测试类型及其对应的责任人
     *
     * @param labSysTestType
     * @return
     */
    @ApiOperation("查询测试类型及其对应的责任人")
    @GetMapping("/operlist")
    @PreAuthorize("@ss.hasPermi('lab:basic:type:list')")
    public AjaxResult getTestOperlist(LabSysTestOperSearchDto labSysTestType) {
        List<LabSysTestType> list = labSysTestTypeService.selectLabSysTestTypeOperatorList(labSysTestType);
        return success(list);
    }

    /**
     * 获取测试类型最大的主键ID
     *
     * @return
     */
    @ApiOperation("获取测试类型最大的主键ID")
    @GetMapping("/getMaxId")
    public AjaxResult getMaxId() {
        return success(labSysTestTypeService.getLabSysTestMaxTestTypeId());
    }

    /**
     * 查询所有测试类型
     */
    @ApiOperation("查询所有测试类型")
    @GetMapping("/all")
    public AjaxResult selectAllTestType(String testType) {
        return success(labSysTestTypeService.selectAllTestType(testType));
    }

}
