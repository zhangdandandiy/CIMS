package com.ruoyi.web.controller.material;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mat.domain.MatSysUserFloor;
import com.ruoyi.mat.domain.dto.MatSysUserFloorResultDto;
import com.ruoyi.mat.domain.dto.MatSysUserFloorResultInfoDto;
import com.ruoyi.mat.service.IMatSysUserFloorService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 备品用户和楼层关联 Controller层
 *
 * @author Dandan
 * @date 2023/8/21 13:35
 **/
@Api(tags = "备品用户和楼层相关接口")
@RestController
@RequestMapping("/material/userFloor")
public class MatSysUserFloorController extends BaseController {

    @Autowired
    private IMatSysUserFloorService matSysUserFloorService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    /**
     * 新增备品用户和楼层关联
     */
    @ApiOperation("新增备品用户和楼层关联")
    @Log(title = "备品用户和楼层关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody MatSysUserFloor matSysUserFloor) {
        return toAjax(matSysUserFloorService.insertMatSysUserFloor(matSysUserFloor));
    }

    /**
     * 查询用户和楼层关联数据
     */
    @ApiOperation("查询用户和楼层关联数据")
    @PostMapping("/list")
    public TableDataInfo selectSysUserFloorList(@RequestBody MatSysUserFloorResultDto userFloor) {
        startPage();
        List<MatSysUserFloorResultDto> list = matSysUserFloorService.selectSysUserFloorList(userFloor);
        return getDataTable(list);
    }

    /**
     * 修改备品用户和楼层关联
     */
    @ApiOperation("修改备品用户和楼层关联")
    @PostMapping("/edit")
    public AjaxResult updateSysUserFloor(@RequestBody MatSysUserFloor matSysUserFloor) {
        return toAjax(matSysUserFloorService.editSysUserFloor(matSysUserFloor));
    }

    /**
     * 根据用户Id查询对应的楼层信息
     */
    @ApiOperation("根据用户Id查询对应的楼层信息")
    @GetMapping("/info")
    public AjaxResult searchUserFloorInfoByUserId(Long userId) {
        return success(matSysUserFloorService.searchMatSysUserFloorInfoByUserId(userId));
    }

    /**
     * 获取用户列表
     */
    @ApiOperation("获取用户列表")
    @GetMapping("/userList")
    public TableDataInfo list(MatSysUserFloorResultInfoDto userInfo) {
        startPage();
        List<MatSysUserFloorResultInfoDto> list = matSysUserFloorService.searchUserList(userInfo);
        return getDataTable(list);
    }

    /**
     * 获取部门树列表
     */
    @ApiOperation("获取部门树列表")
    @GetMapping("/deptTree")
    public AjaxResult deptTree(SysDept dept) {
        return success(deptService.selectDeptTreeList(dept));
    }

}
