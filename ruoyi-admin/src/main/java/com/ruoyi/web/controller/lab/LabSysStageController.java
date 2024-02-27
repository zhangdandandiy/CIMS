package com.ruoyi.web.controller.lab;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.lab.domain.LabSysStage;
import com.ruoyi.lab.domain.dto.LabSysStageMilSearchDto;
import com.ruoyi.lab.service.ILabSysStageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 阶段 Controller层
 *
 * @author Dandan
 * @date 2023/8/4 15:50
 **/
@Api(tags = "阶段相关接口")
@RestController
@RequestMapping("/lab/stage")
public class LabSysStageController extends BaseController {

    @Autowired
    private ILabSysStageService labSysStageService;

    /**
     * 查询阶段及其相关里程的数据
     */
    @ApiOperation("查询阶段及其相关里程")
    @PreAuthorize("@ss.hasPermi('lab:basic:stage:list')")
    @GetMapping("/list")
    public AjaxResult getStageMileagelist(LabSysStageMilSearchDto labSysStageMilDto) {
        List<LabSysStage> list = labSysStageService.selectLabSysStageMileageList(labSysStageMilDto);
        return success(list);
    }

    /**
     * 新增阶段及其相关里程
     */
    @ApiOperation("新增阶段及其相关里程")
    @PreAuthorize("@ss.hasPermi('lab:basic:stage:add')")
    @Log(title = "阶段信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody LabSysStage labSysStage) {
        labSysStage.setCreateBy(getUsername());
        return toAjax(labSysStageService.insertLabSysStageMileage(labSysStage));
    }

    /**
     * lab_sys_stage表中最大的主键ID
     */
    @ApiOperation("获取阶段最大的主键ID")
    @GetMapping("/getMaxId")
    public AjaxResult getMaxStageId() {
        return success(labSysStageService.getLabSysStageMaxStageId());
    }

    /**
     * 修改阶段及其相关里程
     */
    @ApiOperation("修改阶段及其相关里程")
    @PreAuthorize("@ss.hasPermi('lab:basic:stage:edit')")
    @Log(title = "阶段信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody LabSysStage labSysStage) {
        labSysStage.setUpdateBy(getUsername());
        return toAjax(labSysStageService.updateLabSysStage(labSysStage));
    }

    /**
     * 批量删除阶段及其里程信息
     *
     * @param ids
     * @return
     */
    @ApiOperation("删除阶段及其相关里程")
    @PreAuthorize("@ss.hasPermi('lab:basic:stage:remove')")
    @Log(title = "阶段信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] ids) {
        return toAjax(labSysStageService.deleteLabSysStageByIds(ids));
    }

    /**
     * 导出阶段及其相关里程
     */
    @ApiOperation("导出阶段及其相关里程")
    @PreAuthorize("@ss.hasPermi('lab:basic:stage:export')")
    @Log(title = "阶段信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LabSysStageMilSearchDto labSysStageMilDto) {
        List<LabSysStage> list = labSysStageService.selectLabSysStageMileageList(labSysStageMilDto);
        ExcelUtil<LabSysStage> util = new ExcelUtil<>(LabSysStage.class);
        util.exportExcel(response, list, "阶段数据");
    }

    /**
     * 根据stageId查询阶段及其相关里程的数据
     */
    @ApiOperation("根据阶段Id查询阶段及其相关里程")
    @PreAuthorize("@ss.hasPermi('lab:basic:stage:query')")
    @GetMapping("/query")
    public AjaxResult getStageMileageByStageId(Long stageId) {
        LabSysStageMilSearchDto labSysStageMilDto = new LabSysStageMilSearchDto();
        labSysStageMilDto.setStageId(stageId);
        return success(labSysStageService.selectLabSysStageMileageList(labSysStageMilDto));
    }

    /**
     * 查询所有阶段
     */
    @ApiOperation("查询所有阶段")
    @GetMapping("/all")
    public AjaxResult allProduct() {
        return success(labSysStageService.searchAllStage());
    }

}
