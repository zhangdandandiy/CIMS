package com.ruoyi.mat.mapper;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.mat.domain.MatSysFloor;
import com.ruoyi.mat.domain.MatSysUserFloor;
import com.ruoyi.mat.domain.dto.MatSysUserFloorResultDto;
import com.ruoyi.mat.domain.dto.MatSysUserFloorResultInfoDto;

import java.util.List;

/**
 * 备品用户和楼层关联 Mapper接口
 *
 * @author Dandan
 * @date 2023/8/21 13:27
 **/
public interface MatSysUserFloorMapper {

    /**
     * 新增备品用户和楼层关联
     *
     * @param matSysUserFloor 备品用户和楼层关联
     * @return 结果
     */
    public int insertMatSysUserFloor(MatSysUserFloor matSysUserFloor);

    /**
     * 根据用户Id删除备品用户和楼层关联
     *
     * @param userId 备品用户和楼层关联主键
     * @return 结果
     */
    public int deleteMatSysUserFloorByUserId(Long userId);

    /**
     * 根据用户Id查询对应的楼层Id
     *
     * @param userId
     * @return
     */
    public Long searchMatSysUserFloorByUserId(Long userId);

    /**
     * 查询用户和楼层关联数据
     *
     * @return
     */
    public List<MatSysUserFloorResultDto> selectSysUserFloorList(MatSysUserFloorResultDto userFloor);

    /**
     * 根据用户Id查询对应的楼层信息
     *
     * @param userId
     * @return
     */
    public MatSysFloor searchMatSysUserFloorInfoByUserId(Long userId);

    /**
     * 查找用户与楼层关联列表
     *
     * @param user
     * @return
     */
    public List<MatSysUserFloorResultInfoDto> searchUserList(MatSysUserFloorResultInfoDto userInfo);

}
