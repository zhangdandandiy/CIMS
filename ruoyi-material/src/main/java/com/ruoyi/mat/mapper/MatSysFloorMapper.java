package com.ruoyi.mat.mapper;

import com.ruoyi.mat.domain.MatSysFloor;

import java.util.List;

/**
 * 备品楼层 Mapper接口
 *
 * @author Dandan
 * @date 2023/8/22 10:04
 **/
public interface MatSysFloorMapper {

    /**
     * 查询备品楼层
     *
     * @param floorId 备品楼层主键
     * @return 备品楼层
     */
    public MatSysFloor selectMatSysFloorByFloorId(Long floorId);

    /**
     * 查询备品楼层列表
     *
     * @param matSysFloor 备品楼层
     * @return 备品楼层集合
     */
    public List<MatSysFloor> selectMatSysFloorList(MatSysFloor matSysFloor);

    /**
     * 查询所有备品楼层列表
     *
     * @return
     */
    public List<String> selectMatSysAllFloorList();

    /**
     * 新增备品楼层
     *
     * @param matSysFloor 备品楼层
     * @return 结果
     */
    public int insertMatSysFloor(MatSysFloor matSysFloor);

    /**
     * 修改备品楼层
     *
     * @param matSysFloor 备品楼层
     * @return 结果
     */
    public int updateMatSysFloor(MatSysFloor matSysFloor);

    /**
     * 删除备品楼层
     *
     * @param floorId 备品楼层主键
     * @return 结果
     */
    public int deleteMatSysFloorByFloorId(Long floorId);

    /**
     * 批量删除备品楼层
     *
     * @param floorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMatSysFloorByFloorIds(Long[] floorIds);

    /**
     * 查询所有楼层编号
     *
     * @return 结果
     */
    public List<MatSysFloor> selectAllFloorCode();

    /**
     * 根据用户名查询用户所在楼层
     *
     * @param userName
     * @return
     */
    public String selectFloorCodeByUserName(String userName);

}
