package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.mat.domain.MatSysDetail;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchListDto;
import com.ruoyi.mat.mapper.MatSysDetailMapper;
import com.ruoyi.mat.service.IMatSysDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 备品详细信息 Controller层
 *
 * @author Dandan
 * @date 2023/8/21 9:07
 **/

@Api(tags = "备品详细信息相关接口")
@RestController
@RequestMapping("/material/detail")
public class MatSysDetailController extends BaseController {

    @Autowired
    private IMatSysDetailService matSysDetailService;

    @Autowired
    private MatSysDetailMapper matSysDetailMapper;

    /**
     * 查询备品详细信息列表
     */
    @ApiOperation("查询备品详细信息列表(数据过滤)")
    @GetMapping("/list")
    public TableDataInfo list(MatSysDetailSearchListDto matSysDetail) {
        // 获取用户Id(用于数据过滤)
        matSysDetail.setUserId(getUserId());
        startPage();
        List<MatSysDetail> list = matSysDetailService.selectMatSysDetailList(matSysDetail);
        return getDataTable(list);
    }

    /**
     * 导出备品详细信息列表
     */
    @ApiOperation("导出备品详细信息列表")
    @Log(title = "备品详细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MatSysDetailSearchListDto matSysDetail) {
        // 获取用户Id(用于数据过滤)
        matSysDetail.setUserId(getUserId());
        List<MatSysDetail> list = matSysDetailService.selectMatSysDetailList(matSysDetail);
        ExcelUtil<MatSysDetail> util = new ExcelUtil<MatSysDetail>(MatSysDetail.class);
        util.exportExcel(response, list, "备品详细信息数据");
    }

    /**
     * 获取备品详细信息详细信息
     */
    @ApiOperation("根据Id获取备品详细信息")
    @GetMapping(value = "/info")
    public AjaxResult getInfo(Long matId) {
        MatSysDetailSearchInfoDto matSysDetail = new MatSysDetailSearchInfoDto();
        matSysDetail.setMatId(matId);
        matSysDetail.setUserId(getUserId());
        return success(matSysDetailService.selectMatSysDetailByMatId(matSysDetail));
    }

    /**
     * 新增备品详细信息
     */
    @ApiOperation("新增备品详细信息")
    @Log(title = "备品详细信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody MatSysDetail matSysDetail) {
        MatSysDetailSearchListDto matSearchSysDetail = new MatSysDetailSearchListDto();
        matSearchSysDetail.setMatCode(matSysDetail.getMatCode());
        matSearchSysDetail.setUserId(getUserId());
        if (!matSysDetailService.checkMatCodeUnique(matSearchSysDetail)) {
            return error("新增料号'" + matSysDetail.getMatCode() + "'失败，料号已存在");
        }
        matSysDetail.setCreateBy(getUsername());
        return toAjax(matSysDetailService.insertMatSysDetail(matSysDetail, getUserId()));
    }

    /**
     * 修改备品详细信息
     */
    @ApiOperation("修改备品详细信息")
    @Log(title = "备品详细信息", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public AjaxResult edit(@Validated @RequestBody MatSysDetail matSysDetail) {
        return toAjax(matSysDetailService.updateMatSysDetail(matSysDetail));
    }

    /**
     * 删除备品详细信息
     */
    @ApiOperation("批量删除备品详细信息")
    @Log(title = "备品详细信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/remove")
    public AjaxResult remove(Long[] matIds) {
        return toAjax(matSysDetailService.deleteMatSysDetailByMatIds(matIds));
    }

    @ApiOperation("导入备品数据")
    @Log(title = "备品详细信息", businessType = BusinessType.IMPORT)
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<MatSysDetail> util = new ExcelUtil<MatSysDetail>(MatSysDetail.class);
        List<MatSysDetail> materialList = util.importExcel(file.getInputStream());
        String operName = getUsername();
        String message = matSysDetailService.importDetail(materialList, updateSupport, operName, getUserId());
        return success(message);
    }

    @ApiOperation("下载导入备品数据的模板")
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<MatSysDetail> util = new ExcelUtil<MatSysDetail>(MatSysDetail.class);
        util.importTemplateExcel(response, "备品详情数据");
    }

    @ApiOperation("通过备品编号查询备品")
    @GetMapping("/infoByCode")
    public AjaxResult selectDetailByMatCode(MatSysDetailSearchInfoDto matSysDetail) {
        matSysDetail.setUserId(getUserId());
        return success(matSysDetailService.selectDetailByMatCode(matSysDetail));
    }

    @ApiOperation("查询所有备品详细信息列表")
    @GetMapping("/allList")
    public TableDataInfo AllList(MatSysDetail matSysDetail) {
        startPage();
        List<MatSysDetail> list = matSysDetailService.selectAllMatSysDetailList(matSysDetail);
        return getDataTable(list);
    }

    @ApiOperation("导出所有备品详细信息列表")
    @Log(title = "备品详细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/allExport")
    public void exportAllList(HttpServletResponse response, MatSysDetail matSysDetail) {
        List<MatSysDetail> list = matSysDetailService.selectAllMatSysDetailList(matSysDetail);
        ExcelUtil<MatSysDetail> util = new ExcelUtil<MatSysDetail>(MatSysDetail.class);
        util.exportExcel(response, list, "备品详细信息数据");
    }

    @ApiOperation("查询所有应用专案")
    @GetMapping("/allProject")
    public AjaxResult selectAllMatProject() {
        List<String> projectList = matSysDetailMapper.selectAllMatProject();
        projectList = projectList.stream()
                .filter(s -> s != null)
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.toList());
        return success(projectList);
    }

    @ApiOperation("查询对应楼层下的所有应用专案")
    @GetMapping("/allProjectFloor")
    public AjaxResult selectAllMatProjectByUserId() {
        Long userId = getUserId();
        List<String> projectList = matSysDetailMapper.selectAllMatProjectByUserId(userId);
        projectList = projectList.stream()
                .filter(s -> s != null)
                .filter(s -> !s.trim().isEmpty())
                .collect(Collectors.toList());
        return success(projectList);
    }

}
