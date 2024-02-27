package com.ruoyi.web.controller.lab;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.lab.domain.LabSysGroup;
import com.ruoyi.lab.service.ILabSysGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Group Controller
 *
 * @author Dandan
 * @date 2023-08-04
 */
@Api(tags = "Group信息相关接口")
@RestController
@RequestMapping("/lab/group")
public class LabSysGroupController extends BaseController {
    @Autowired
    private ILabSysGroupService labSysGroupService;

    /**
     * 查询Group列表
     */
    @ApiOperation("查询Group列表")
    @PreAuthorize("@ss.hasPermi('lab:basic:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(LabSysGroup labSysGroup) {
        startPage();
        List<LabSysGroup> list = labSysGroupService.selectLabSysGroupList(labSysGroup);
        return getDataTable(list);
    }

    /**
     * 导出Group列表
     */
    @ApiOperation("导出Group列表")
    @PreAuthorize("@ss.hasPermi('lab:basic:group:export')")
    @Log(title = "Group信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LabSysGroup labSysGroup) {
        List<LabSysGroup> list = labSysGroupService.selectLabSysGroupList(labSysGroup);
        ExcelUtil<LabSysGroup> util = new ExcelUtil<LabSysGroup>(LabSysGroup.class);
        util.exportExcel(response, list, "Group数据");
    }

    /**
     * 获取Group详细信息
     */
    @ApiOperation("获取Group详细信息")
    @PreAuthorize("@ss.hasPermi('lab:basic:group:query')")
    @GetMapping("/info")
    public AjaxResult getInfo(Long id) {
        return success(labSysGroupService.selectLabSysGroupById(id));
    }

    /**
     * 新增Group
     */
    @ApiOperation("新增Group")
    @PreAuthorize("@ss.hasPermi('lab:basic:group:add')")
    @Log(title = "Group信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody LabSysGroup labSysGroup) {
        if (!labSysGroupService.checkLabSysGroupUnique(labSysGroup)) {
            return error("新增Group'" + labSysGroup.getGroups() + "'失败，Group已存在");
        }
        labSysGroup.setCreateBy(getUsername());
        return toAjax(labSysGroupService.insertLabSysGroup(labSysGroup));
    }

    /**
     * 修改Group
     */
    @ApiOperation("修改Group")
    @PreAuthorize("@ss.hasPermi('lab:basic:group:edit')")
    @Log(title = "Group信息", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    public AjaxResult edit(@RequestBody LabSysGroup labSysGroup) {
        labSysGroup.setUpdateBy(getUsername());
        return toAjax(labSysGroupService.updateLabSysGroup(labSysGroup));
    }

    /**
     * 删除Group
     */
    @ApiOperation("删除Group")
    @PreAuthorize("@ss.hasPermi('lab:basic:group:remove')")
    @Log(title = "Group信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(labSysGroupService.deleteLabSysGroupByIds(ids));
    }

    /**
     * 查询所有group
     */
    @ApiOperation("查询所有group")
    @GetMapping("/all")
    public AjaxResult searchAllGroups() {
        return success(labSysGroupService.searchAllGroups());
    }
}
