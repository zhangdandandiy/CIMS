package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mat.domain.MatSysFloor;
import com.ruoyi.mat.service.IMatSysFloorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 备品楼层 Controller
 *
 * @author Dandan
 * @date 2023/8/22 10:49
 **/
@Api(tags = "备品楼层相关接口")
@RestController
@RequestMapping("/material/floor")
public class MatSysFloorController extends BaseController {

    @Autowired
    private IMatSysFloorService matSysFloorService;

    /**
     * 查询备品楼层列表
     */
    @ApiOperation("查询备品楼层列表")
    @GetMapping("/list")
    public TableDataInfo list(MatSysFloor matSysFloor) {
        startPage();
        List<MatSysFloor> list = matSysFloorService.selectMatSysFloorList(matSysFloor);
        return getDataTable(list);
    }

    @ApiOperation("查询所有楼层编号")
    @GetMapping("/allCode")
    public AjaxResult allList() {
        return success(matSysFloorService.selectMatSysAllFloorList());
    }

    /**
     * 获取备品楼层详细信息
     */
    @ApiOperation("获取备品楼层详细信息")
    @GetMapping("/info")
    public AjaxResult getInfo(Long floorId) {
        return success(matSysFloorService.selectMatSysFloorByFloorId(floorId));
    }

    /**
     * 新增备品楼层
     */
    @ApiOperation("新增备品楼层信息")
    @Log(title = "备品楼层", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody MatSysFloor matSysFloor) {
        matSysFloor.setCreateBy(getUsername());
        return toAjax(matSysFloorService.insertMatSysFloor(matSysFloor));
    }

    /**
     * 修改备品楼层
     */
    @ApiOperation("修改备品楼层信息")
    @Log(title = "备品楼层", businessType = BusinessType.UPDATE)
    @PutMapping("edit")
    public AjaxResult edit(@RequestBody MatSysFloor matSysFloor) {
        matSysFloor.setUpdateBy(getUsername());
        return toAjax(matSysFloorService.updateMatSysFloor(matSysFloor));
    }

    /**
     * 删除备品楼层
     */
    @ApiOperation("根据Id批量删除备品楼层")
    @Log(title = "备品楼层", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public AjaxResult remove(Long[] floorIds) {
        return toAjax(matSysFloorService.deleteMatSysFloorByFloorIds(floorIds));
    }

    @ApiOperation("查询所有楼层列表")
    @GetMapping("/allList")
    public AjaxResult selectAllFloorCode() {
        return success(matSysFloorService.selectAllFloorCode());
    }

}
