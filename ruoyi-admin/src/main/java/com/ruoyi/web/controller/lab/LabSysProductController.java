package com.ruoyi.web.controller.lab;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.lab.domain.LabSysProduct;
import com.ruoyi.lab.service.ILabSysProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 专案信息操作处理
 *
 * @author Dandan
 * @date 2023-08-04
 */
@Api(tags = "专案信息相关接口")
@RestController
@RequestMapping("/lab/product")
public class LabSysProductController extends BaseController {
    @Autowired
    private ILabSysProductService labSysProductService;

    /**
     * 查询专案列表
     */
    @PreAuthorize("@ss.hasPermi('lab:basic:product:list')")
    @ApiOperation("查询专案列表")
    @GetMapping("/list")
    public TableDataInfo list(LabSysProduct labSysProduct) {
        startPage();
        List<LabSysProduct> list = labSysProductService.selectLabSysProductList(labSysProduct);
        return getDataTable(list);
    }

    /**
     * 导出专案列表
     */
    @PreAuthorize("@ss.hasPermi('lab:basic:product:export')")
    @ApiOperation("导出专案列表")
    @Log(title = "专案信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LabSysProduct labSysProduct) {
        List<LabSysProduct> list = labSysProductService.selectLabSysProductList(labSysProduct);
        ExcelUtil<LabSysProduct> util = new ExcelUtil<LabSysProduct>(LabSysProduct.class);
        util.exportExcel(response, list, "专案数据");
    }

    /**
     * 获取专案详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:basic:product:query')")
    @ApiOperation("获取专案详细信息")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(labSysProductService.selectLabSysProductById(id));
    }

    /**
     * 新增专案
     */
    @PreAuthorize("@ss.hasPermi('lab:basic:product:add')")
    @ApiOperation("新增专案")
    @Log(title = "专案信息", businessType = BusinessType.INSERT)
    @PostMapping("add")
    public AjaxResult add(@RequestBody LabSysProduct labSysProduct) {
        labSysProduct.setCreateBy(getUsername());
        return toAjax(labSysProductService.insertLabSysProduct(labSysProduct));
    }

    /**
     * 修改专案
     */
    @PreAuthorize("@ss.hasPermi('lab:basic:product:edit')")
    @ApiOperation("修改专案")
    @Log(title = "专案信息", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    public AjaxResult edit(@RequestBody LabSysProduct labSysProduct) {
        labSysProduct.setUpdateBy(getUsername());
        return toAjax(labSysProductService.updateLabSysProduct(labSysProduct));
    }

    /**
     * 删除专案
     */
    @PreAuthorize("@ss.hasPermi('lab:basic:product:remove')")
    @ApiOperation("批量删除专案")
    @Log(title = "专案信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(labSysProductService.deleteLabSysProductByIds(ids));
    }

    /**
     * 查询所有专案
     */
    @ApiOperation("查询所有专案")
    @GetMapping("/all")
    public AjaxResult allProduct() {
        return success(labSysProductService.selectAllProduct());
    }
}
