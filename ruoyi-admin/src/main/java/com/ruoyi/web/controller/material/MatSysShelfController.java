package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mat.domain.MatSysShelf;
import com.ruoyi.mat.domain.dto.MatSysShelfSearchInfoDto;
import com.ruoyi.mat.mapper.MatSysShelfMapper;
import com.ruoyi.mat.service.IMatSysShelfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/22 13:18
 **/

@Api(tags = "备品储位信息相关接口")
@RestController
@RequestMapping("/material/shelf")
public class MatSysShelfController extends BaseController {

    @Autowired
    private IMatSysShelfService matSysShelfService;

    @Autowired
    private MatSysShelfMapper matSysShelfMapper;

    @ApiOperation("查询储位信息列表")
    @GetMapping("/list")
    public TableDataInfo selectMatSysShelfList(MatSysShelfSearchInfoDto searchList) {
        searchList.setUserId(getUserId());
        startPage();
        List<MatSysShelf> list = matSysShelfService.selectMatSysShelfList(searchList);
        return getDataTable(list);
    }

    @ApiOperation("查询所有未被占用储位列表")
    @GetMapping("/noOccupy")
    public AjaxResult selectAllNoOccupyShelfCode(){
        return success(matSysShelfService.selectAllNoOccupyShelfCode(getUserId()));
    }

    @ApiOperation("根据储位Id查询备品储位信息")
    @GetMapping("/info")
    public AjaxResult selectMatSysShelfMatShelfId(Long matShelfId) {
        MatSysShelfSearchInfoDto matSysShelf = new MatSysShelfSearchInfoDto();
        matSysShelf.setShelfId(matShelfId);
        matSysShelf.setUserId(getUserId());
        return success(matSysShelfService.selectMatSysShelfByShelfId(matSysShelf));
    }

    @ApiOperation("新增备品储位及关联信息")
    @Log(title = "备品储位信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult insertMatSysShelf(@Validated @RequestBody MatSysShelf matSysShelf) {
        MatSysShelfSearchInfoDto matSearchSysShelf = new MatSysShelfSearchInfoDto();
        matSearchSysShelf.setShelfCode(matSysShelf.getShelfCode());
        matSearchSysShelf.setUserId(getUserId());
        if (!matSysShelfService.checkMatSysShelfUnique(matSearchSysShelf)) {
            return error("新增储位'" + matSysShelf.getShelfCode() + "'失败，储位已存在");
        }
        matSysShelf.setCreateBy(getUsername());
        return toAjax(matSysShelfService.insertMatSysShelf(matSysShelf, getUserId()));
    }

    @ApiOperation("修改备品储位信息")
    @Log(title = "备品储位信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult updateMatSysShelf(@Validated @RequestBody MatSysShelf matSysShelf) {
        MatSysShelfSearchInfoDto matSysShelfSearchInfo = new MatSysShelfSearchInfoDto();
        matSysShelfSearchInfo.setShelfCode(matSysShelf.getShelfCode());
        matSysShelfSearchInfo.setUserId(getUserId());

        MatSysShelf matSysShelfInfo = matSysShelfMapper.checkMatSysShelfUnique(matSysShelfSearchInfo);

        if (!matSysShelfService.checkMatSysShelfUnique(matSysShelfSearchInfo) && matSysShelfInfo.getShelfId() != matSysShelf.getShelfId()) {
            return error("修改储位'" + matSysShelf.getShelfCode() + "'失败，储位已存在");
        }

        matSysShelf.setUpdateBy(getUsername());
        return toAjax(matSysShelfService.updateMatSysShelf(matSysShelf));
    }

    @ApiOperation("批量删除备品储位及关联信息")
    @Log(title = "备品储位信息", businessType = BusinessType.UPDATE)
    @DeleteMapping("/delete")
    public AjaxResult deleteMatSysShelfByShelfIds(Long[] shelfIds) {
        return toAjax(matSysShelfService.deleteMatSysShelfByShelfIds(shelfIds));
    }

    @ApiOperation("获取最大主键Id")
    @GetMapping("/maxId")
    public AjaxResult getMatSysShelfMaxShelfId() {
        return success(matSysShelfService.getMatSysShelfMaxShelfId());
    }

}
