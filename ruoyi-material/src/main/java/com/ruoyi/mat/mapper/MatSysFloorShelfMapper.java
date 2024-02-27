package com.ruoyi.mat.mapper;

import com.ruoyi.mat.domain.MatSysFloorShelf;

/**
 * 备品楼层和储位关联 Mapper接口
 *
 * @author Dandan
 * @date 2023/8/22 14:09
 **/
public interface MatSysFloorShelfMapper {

    /**
     * 新增备品楼层和储位关联
     *
     * @param matSysFloorShelf 备品楼层和储位关联
     * @return 结果
     */
    public int insertMatSysFloorShelf(MatSysFloorShelf matSysFloorShelf);

    /**
     * 通过楼层Id批量删除备品楼层和储位关联
     *
     * @param floorIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMatSysFloorShelfByFloorIds(Long[] floorIds);

    /**
     * 通过储位Id批量删除备品楼层和储位关联
     *
     * @param shelfIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMatSysFloorShelfByShelfIds(Long[] shelfIds);

}
