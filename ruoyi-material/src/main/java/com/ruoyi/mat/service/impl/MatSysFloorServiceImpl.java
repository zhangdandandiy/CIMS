package com.ruoyi.mat.service.impl;

import com.ruoyi.mat.domain.MatSysFloor;
import com.ruoyi.mat.mapper.MatSysFloorMapper;
import com.ruoyi.mat.service.IMatSysFloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 备品楼层 Service业务层处理
 *
 * @author Dandan
 * @date 2023/8/22 10:48
 **/
@Service
public class MatSysFloorServiceImpl implements IMatSysFloorService {

    @Autowired
    private MatSysFloorMapper matSysFloorMapper;

    /**
     * 查询备品楼层
     *
     * @param floorId 备品楼层主键
     * @return 备品楼层
     */
    @Override
    public MatSysFloor selectMatSysFloorByFloorId(Long floorId) {
        return matSysFloorMapper.selectMatSysFloorByFloorId(floorId);
    }

    /**
     * 查询备品楼层列表
     *
     * @param matSysFloor 备品楼层
     * @return 备品楼层
     */
    @Override
    public List<MatSysFloor> selectMatSysFloorList(MatSysFloor matSysFloor) {
        return matSysFloorMapper.selectMatSysFloorList(matSysFloor);
    }

    /**
     * 查询所有备品楼层列表
     *
     * @return
     */
    @Override
    public List<String> selectMatSysAllFloorList(){
        return matSysFloorMapper.selectMatSysAllFloorList();
    }

    /**
     * 新增备品楼层
     *
     * @param matSysFloor 备品楼层
     * @return 结果
     */
    @Override
    public int insertMatSysFloor(MatSysFloor matSysFloor) {
        return matSysFloorMapper.insertMatSysFloor(matSysFloor);
    }

    /**
     * 修改备品楼层
     *
     * @param matSysFloor 备品楼层
     * @return 结果
     */
    @Override
    public int updateMatSysFloor(MatSysFloor matSysFloor) {
        return matSysFloorMapper.updateMatSysFloor(matSysFloor);
    }

    /**
     * 批量删除备品楼层
     *
     * @param floorIds 需要删除的备品楼层主键
     * @return 结果
     */
    @Override
    public int deleteMatSysFloorByFloorIds(Long[] floorIds) {
        return matSysFloorMapper.deleteMatSysFloorByFloorIds(floorIds);
    }

    /**
     * 删除备品楼层信息
     *
     * @param floorId 备品楼层主键
     * @return 结果
     */
    @Override
    public int deleteMatSysFloorByFloorId(Long floorId) {
        return matSysFloorMapper.deleteMatSysFloorByFloorId(floorId);
    }

    /**
     * 查询所有楼层编号
     *
     * @return 结果
     */
    @Override
    public List<MatSysFloor> selectAllFloorCode(){
        return matSysFloorMapper.selectAllFloorCode();
    }

}
