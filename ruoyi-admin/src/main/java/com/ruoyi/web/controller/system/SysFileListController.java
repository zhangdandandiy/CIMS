package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysFileList;
import com.ruoyi.system.service.ISysFileListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 已上传文件列表
 *
 * @author Dandan
 * @date 2023/8/15 9:08
 **/
@Api(tags = "已上传文件相关接口")
@RestController
@RequestMapping("/system/fileList")
public class SysFileListController extends BaseController {

    @Autowired
    private ISysFileListService iSysFileListService;

    /**
     * 查询已上传文件列表
     */
    @ApiOperation("查询文件列表")
    @GetMapping("/list")
    public TableDataInfo list(SysFileList sysFileList) {
        startPage();
        List<SysFileList> list = iSysFileListService.selectSysFileListList(sysFileList);
        return getDataTable(list);
    }

    /**
     * 导出已上传文件列表
     */
    @ApiOperation("导出文件列表")
    @Log(title = "文件列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysFileList sysFileList) {
        List<SysFileList> list = iSysFileListService.selectSysFileListList(sysFileList);
        ExcelUtil<SysFileList> util = new ExcelUtil<>(SysFileList.class);
        return util.exportExcel(list, "fileList");
    }

    /**
     * 获取已上传文件列表详细信息
     */
    @ApiOperation("获取文件详细信息")
    @GetMapping("/info")
    public AjaxResult getInfo(Long id) {
        return AjaxResult.success(iSysFileListService.selectSysFileListById(id));
    }

    /**
     * 新增文件列表
     */
    @ApiOperation("新增文件列表")
    @Log(title = "文件列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SysFileList sysFileList) {
        return toAjax(iSysFileListService.insertSysFileList(sysFileList));
    }

    /**
     * 修改文件列表
     */
    @ApiOperation("修改文件列表")
    @Log(title = "文件列表", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody SysFileList sysFileList) {
        return toAjax(iSysFileListService.updateSysFileList(sysFileList));
    }

    @ApiOperation("删除文件列表")
    @Log(title = "文件列表", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] ids) {
        return toAjax(iSysFileListService.deleteSysFileListByIds(ids));
    }

}
