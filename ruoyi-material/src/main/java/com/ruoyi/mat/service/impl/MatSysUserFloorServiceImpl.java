package com.ruoyi.mat.service.impl;

import com.ruoyi.mat.domain.MatSysFloor;
import com.ruoyi.mat.domain.MatSysUserFloor;
import com.ruoyi.mat.domain.dto.MatSysUserFloorResultDto;
import com.ruoyi.mat.domain.dto.MatSysUserFloorResultInfoDto;
import com.ruoyi.mat.mapper.MatSysUserFloorMapper;
import com.ruoyi.mat.service.IMatSysUserFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/21 13:32
 **/
@Service
public class MatSysUserFloorServiceImpl implements IMatSysUserFloorService {

    @Autowired
    private MatSysUserFloorMapper matSysUserFloorMapper;

    /**
     * 新增备品用户和楼层关联
     *
     * @param matSysUserFloor 备品用户和楼层关联
     * @return 结果
     */
    @Override
    public int insertMatSysUserFloor(MatSysUserFloor matSysUserFloor) {
        return matSysUserFloorMapper.insertMatSysUserFloor(matSysUserFloor);
    }

    /**
     * 删除备品用户和楼层关联信息
     *
     * @param userId 备品用户和楼层关联主键
     * @return 结果
     */
    @Override
    public int deleteMatSysUserFloorByUserId(Long userId) {
        return matSysUserFloorMapper.deleteMatSysUserFloorByUserId(userId);
    }

    /**
     * 查询用户和楼层关联数据
     *
     * @return
     */
    @Override
    public List<MatSysUserFloorResultDto> selectSysUserFloorList(MatSysUserFloorResultDto userFloor) {
        return matSysUserFloorMapper.selectSysUserFloorList(userFloor);
    }

    /**
     * 修改用户和楼层关联信息
     *
     * @param matSysUserFloor
     * @return
     */
    @Transactional
    @Override
    public int editSysUserFloor(MatSysUserFloor matSysUserFloor) {
        // 获取用户Id
        Long userId = matSysUserFloor.getUserId();
        // 根据用户Id删除关联信息
        matSysUserFloorMapper.deleteMatSysUserFloorByUserId(userId);
        // 重新插入
        return matSysUserFloorMapper.insertMatSysUserFloor(matSysUserFloor);
    }

    /**
     * 根据用户Id查询对应的楼层信息
     *
     * @param userId
     * @return
     */
    @Override
    public MatSysFloor searchMatSysUserFloorInfoByUserId(Long userId) {
        return matSysUserFloorMapper.searchMatSysUserFloorInfoByUserId(userId);
    }

    /**
     * 查找用户与楼层关联列表
     *
     * @param user
     * @return
     */
    @Override
    public List<MatSysUserFloorResultInfoDto> searchUserList(MatSysUserFloorResultInfoDto userInfo){
        return matSysUserFloorMapper.searchUserList(userInfo);
    }

}
