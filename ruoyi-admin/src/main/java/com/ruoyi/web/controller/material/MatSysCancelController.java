package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mat.domain.MatSysCancel;
import com.ruoyi.mat.domain.dto.MatSysCancelSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysCancelSearchListDto;
import com.ruoyi.mat.domain.dto.MatSysCancelTotalInfoDto;
import com.ruoyi.mat.service.IMatSysCancelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 备品退库作业 Controller层
 *
 * @author Dandan
 * @date 2023/8/22 8:46
 **/
@Api(tags = "备品退库作业相关接口")
@RestController
@RequestMapping("/material/cencel")
public class MatSysCancelController extends BaseController {

    @Autowired
    private IMatSysCancelService matSysCancelService;

    @ApiOperation("查询备品退库信息列表")
    @GetMapping("/list")
    public TableDataInfo selectMatSysInputList(MatSysCancelSearchListDto searchList) {
        searchList.setUserId(getUserId());
        startPage();
        List<MatSysCancel> list = matSysCancelService.selectMatSysCancelList(searchList);
        return getDataTable(list);
    }

    @ApiOperation("查询总的退库信息列表")
    @GetMapping("/totalList")
    public TableDataInfo selectMatSysInputTotalList(MatSysCancelTotalInfoDto searchList) {
        searchList.setUserId(getUserId());
        startPage();
        List<MatSysCancelTotalInfoDto> list = matSysCancelService.selectMatSysCancelTotalList(searchList);
        return getDataTable(list);
    }

    @ApiOperation("导出总的退库信息列表")
    @Log(title = "备品退库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MatSysCancelTotalInfoDto searchList) {
        // 获取用户Id(用于数据过滤)
        searchList.setUserId(getUserId());
        List<MatSysCancelTotalInfoDto> list = matSysCancelService.selectMatSysCancelTotalList(searchList);
        ExcelUtil<MatSysCancelTotalInfoDto> util = new ExcelUtil<MatSysCancelTotalInfoDto>(MatSysCancelTotalInfoDto.class);
        util.exportExcel(response, list, "备品退库信息数据");
    }

    @ApiOperation("根据退库Id查询备品退库信息")
    @GetMapping("/info")
    public AjaxResult selectMatSysInputByMatInputId(Long matCancelId) {
        MatSysCancelSearchInfoDto matSysCancel = new MatSysCancelSearchInfoDto();
        matSysCancel.setMatCancelId(matCancelId);
        matSysCancel.setUserId(getUserId());
        return success(matSysCancelService.selectMatSysCancelByMatCancelId(matSysCancel));
    }

    @ApiOperation("新增备品退库信息")
    @Log(title = "备品退库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult insertMatSysInput(@RequestBody MatSysCancel matSysCancel) {
        matSysCancel.setMatCancelUser(getUsername());
        return toAjax(matSysCancelService.insertMatSysCancel(matSysCancel));
    }

    /**
     * 删除备品退库信息
     */
    @ApiOperation("根据Id批量删除退库信息")
    @Log(title = "备品退库信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] matCancelIds) {
        return toAjax(matSysCancelService.deleteMatSysCancelByMatCancelIds(matCancelIds));
    }

}
