package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mat.domain.MatSysDetail;
import com.ruoyi.mat.domain.MatSysOutput;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysOutputSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysOutputSearchListDto;
import com.ruoyi.mat.domain.dto.MatSysOutputTotalInfoDto;
import com.ruoyi.mat.service.IMatSysDetailService;
import com.ruoyi.mat.service.IMatSysOutputService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 备品出库信息 Controller层
 *
 * @author Dandan
 * @date 2023/8/21 20:11
 **/

@Api(tags = "备品出库作业相关接口")
@RestController
@RequestMapping("/material/output")
public class MatSysOutputController extends BaseController {

    @Autowired
    private IMatSysOutputService matSysOutputService;

    @Autowired
    private IMatSysDetailService matSysDetailService;

    @ApiOperation("查询备品出库信息列表")
    @GetMapping("/list")
    public TableDataInfo selectMatSysOutputList(MatSysOutputSearchListDto searchList) {
        searchList.setUserId(getUserId());
        startPage();
        List<MatSysOutput> list = matSysOutputService.selectMatSysOutputList(searchList);
        return getDataTable(list);
    }

    @ApiOperation("查询总的备品出库信息列表")
    @GetMapping("/totalList")
    public TableDataInfo selectMatSysOutputTotalList(MatSysOutputSearchListDto searchList) {
        searchList.setUserId(getUserId());
        startPage();
        List<MatSysOutputTotalInfoDto> list = matSysOutputService.selectMatSysOutputTotalList(searchList);
        return getDataTable(list);
    }

    @ApiOperation("导出总的备品出库信息列表")
    @Log(title = "备品出库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MatSysOutputSearchListDto searchList) {
        searchList.setUserId(getUserId());
        List<MatSysOutputTotalInfoDto> list = matSysOutputService.selectMatSysOutputTotalList(searchList);
        ExcelUtil<MatSysOutputTotalInfoDto> util = new ExcelUtil<MatSysOutputTotalInfoDto>(MatSysOutputTotalInfoDto.class);
        util.exportExcel(response, list, "备品出库记录");
    }

    @ApiOperation("根据出库Id查询备品出库信息")
    @GetMapping("/info")
    public AjaxResult selectMatSysOutputByMatOutputId(Long matOutputId) {
        MatSysOutputSearchInfoDto matSysOutput = new MatSysOutputSearchInfoDto();
        matSysOutput.setMatOutputId(matOutputId);
        matSysOutput.setUserId(getUserId());
        return success(matSysOutputService.selectMatSysOutputByMatOutputId(matSysOutput));
    }

    @ApiOperation("新增备品出库信息")
    @Log(title = "备品出库信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult insertMatSysOutput(@RequestBody MatSysOutput matSysOutput) {
        matSysOutput.setMatOutputUser(getUsername());
        // 获取出库数量
        long outputNumber = matSysOutput.getMatOutputNumber();
        // 备品编号
        String matCode = matSysOutput.getMatCode();
        // 根据备品编号查询备品数量
        MatSysDetailSearchInfoDto matSysDetailSearchInfoDto = new MatSysDetailSearchInfoDto();
        matSysDetailSearchInfoDto.setUserId(getUserId());
        matSysDetailSearchInfoDto.setMatCode(matCode);
        MatSysDetail matSysDetail = matSysDetailService.selectDetailByMatCode(matSysDetailSearchInfoDto);
        long matNumber = matSysDetail.getMatNumber();
        if (matNumber - outputNumber < 0) {
            return error("备品'" + matSysOutput.getMatCode() + "'出库失败，备品不足，无法出库");
        } else {
            return toAjax(matSysOutputService.insertMatSysOutput(matSysOutput));
        }
    }

    /**
     * 删除备品出库信息
     */
    @ApiOperation("根据Id批量删除出库信息")
    @Log(title = "备品出库信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] matOutputIds) {
        return toAjax(matSysOutputService.deleteMatSysOutputByMatOutputIds(matOutputIds));
    }
}
