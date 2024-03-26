package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mat.domain.MatSysInput;
import com.ruoyi.mat.domain.dto.*;
import com.ruoyi.mat.service.IMatSysInputService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 备品入库信息 Controller层
 *
 * @author Dandan
 * @date 2023/8/21 18:35
 **/

@Api(tags = "备品入库作业相关接口")
@RestController
@RequestMapping("/material/input")
public class MatSysInputController extends BaseController {

    @Autowired
    private IMatSysInputService matSysInputService;

    @ApiOperation("修改备品出库信息")
    @PostMapping("/edit")
    public AjaxResult editMatSysInput(@RequestBody MatSysInputEditDto matSysInputEditDto) {
        matSysInputEditDto.setMatInputUser(getUsername());
        matSysInputService.editMatSysInput(matSysInputEditDto);
        return success();
    }

    @ApiOperation("查询备品入库信息列表")
    @GetMapping("/list")
    public TableDataInfo selectMatSysInputList(MatSysInputSearchListDto searchList) {
        searchList.setUserId(getUserId());
        startPage();
        List<MatSysInput> list = matSysInputService.selectMatSysInputList(searchList);
        return getDataTable(list);
    }

    @ApiOperation("查询总的备品入库信息列表")
    @GetMapping("/totalList")
    public TableDataInfo selectMatSysInputTotalList(MatSysInputSearchListDto searchList) {
        searchList.setUserId(getUserId());
        startPage();
        List<MatSysInputTotalInfoDto> list = matSysInputService.selectMatSysInputTotalList(searchList);
        return getDataTable(list);
    }

    @ApiOperation("导出总的备品入库信息列表")
    @Log(title = "备品入库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MatSysInputSearchListDto searchList) {
        searchList.setUserId(getUserId());
        List<MatSysInputTotalInfoDto> list = matSysInputService.selectMatSysInputTotalList(searchList);
        ExcelUtil<MatSysInputTotalInfoDto> util = new ExcelUtil<MatSysInputTotalInfoDto>(MatSysInputTotalInfoDto.class);
        util.exportExcel(response, list, "备品入库记录");
    }

    @ApiOperation("根据入库Id查询备品入库信息")
    @GetMapping("/info")
    public AjaxResult selectMatSysInputByMatInputId(Long matInputId) {
        MatSysInputSearchInfoDto matSysInput = new MatSysInputSearchInfoDto();
        matSysInput.setMatInputId(matInputId);
        matSysInput.setUserId(getUserId());
        return success(matSysInputService.selectMatSysInputByMatInputId(matSysInput));
    }

    @ApiOperation("新增备品入库信息")
    @Log(title = "备品入库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult insertMatSysInput(@RequestBody MatSysInput matSysInput) {
        matSysInput.setMatInputUser(getUsername());
        return toAjax(matSysInputService.insertMatSysInput(matSysInput));
    }

    /**
     * 删除备品入库信息
     */
    @ApiOperation("根据Id批量删除入库信息")
    @Log(title = "备品入库信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] matInputIds) {
        return toAjax(matSysInputService.deleteMatSysInputByMatInputIds(matInputIds));
    }

}
