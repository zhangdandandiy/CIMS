package com.ruoyi.web.controller.packing;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.packing.domain.PackSysInfo;
import com.ruoyi.packing.service.IPackSysInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 包装校验信息 Controller
 *
 * @author Dandan
 * @date 2023/9/8 21:09
 **/
@Api(tags = "包装校验条码信息相关接口")
@RestController
@RequestMapping("/packing/info")
public class PackSysInfoController extends BaseController {
    @Autowired
    private IPackSysInfoService packSysInfoService;

    /**
     * 查询包装校验信息列表
     */
    @ApiOperation("查询包装校验信息列表")
    @GetMapping("/list")
    public TableDataInfo list(PackSysInfo packSysInfo) {
        startPage();
        List<PackSysInfo> list = packSysInfoService.selectPackSysInfoList(packSysInfo);
        return getDataTable(list);
    }

    /**
     * 导出包装校验信息列表
     */
    @ApiOperation("导出包装校验信息列表")
    @Log(title = "包装校验信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PackSysInfo packSysInfo) {
        List<PackSysInfo> list = packSysInfoService.selectPackSysInfoList(packSysInfo);
        ExcelUtil<PackSysInfo> util = new ExcelUtil<PackSysInfo>(PackSysInfo.class);
        util.exportExcel(response, list, "包装校验信息数据");
    }

    /**
     * 获取包装校验信息详细信息
     */
    @ApiOperation("获取包装校验信息详细信息")
    @GetMapping("/info")
    public AjaxResult getInfo(Long processId) {
        return success(packSysInfoService.selectPackSysInfoByProcessId(processId));
    }

    /**
     * 新增包装校验信息
     */
    @ApiOperation("新增包装校验信息")
    @Log(title = "包装校验信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PackSysInfo packSysInfo) {
        packSysInfo.setCreateBy(getUsername());
        return toAjax(packSysInfoService.insertPackSysInfo(packSysInfo));
    }

    /**
     * 修改包装校验信息
     */
    @ApiOperation("修改包装校验信息")
    @Log(title = "包装校验信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody PackSysInfo packSysInfo) {
        packSysInfo.setUpdateBy(getUsername());
        return toAjax(packSysInfoService.updatePackSysInfo(packSysInfo));
    }

    /**
     * 删除包装校验信息
     */
    @ApiOperation("删除包装校验信息")
    @Log(title = "包装校验信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] processIds) {
        return toAjax(packSysInfoService.deletePackSysInfoByProcessIds(processIds));
    }

    @ApiOperation("获取最大主键ID")
    @GetMapping("/maxId")
    public AjaxResult getMaxProcessId() {
        return success(packSysInfoService.getMaxProcessId());
    }

}
