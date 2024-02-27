package com.ruoyi.mat.service;

import com.ruoyi.mat.domain.MatSysShelf;
import com.ruoyi.mat.domain.dto.MatSysShelfSearchInfoDto;

import java.util.List;

/**
 * 备品储位 Service接口
 *
 * @author Dandan
 * @date 2023/8/22 13:14
 **/
public interface IMatSysShelfService {

    /**
     * 查询备品储位列表
     *
     * @param matSysShelf 备品储位
     * @return 备品储位集合
     */
    public List<MatSysShelf> selectMatSysShelfList(MatSysShelfSearchInfoDto matSysShelf);

    /**
     * 查询所有未被占用储位编号
     *
     * @param matSysShelf
     * @return
     */
    public List<MatSysShelf> selectAllNoOccupyShelfCode(Long userId);

    /**
     * 查询备品储位
     *
     * @param matSysShelf 备品储位信息
     * @return 备品储位
     */
    public MatSysShelf selectMatSysShelfByShelfId(MatSysShelfSearchInfoDto matSysShelf);

    /**
     * 新增备品储位及关联信息
     *
     * @param matSysShelf 备品储位
     * @return 结果
     */
    public int insertMatSysShelf(MatSysShelf matSysShelf, Long userId);

    /**
     * 修改备品储位
     *
     * @param matSysShelf 备品储位
     * @return 结果
     */
    public int updateMatSysShelf(MatSysShelf matSysShelf);

    /**
     * 批量删除备品储位及关联信息
     *
     * @param shelfIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMatSysShelfByShelfIds(Long[] shelfIds);

    /**
     * 获取 mat_sys_shelf表中最大的主键ID
     *
     * @return
     */
    public int getMatSysShelfMaxShelfId();

    /**
     * 校验储位编号唯一性
     *
     * @param matSysShelf 备品储位信息
     * @return 结果
     */
    public boolean checkMatSysShelfUnique(MatSysShelfSearchInfoDto matSysShelf);

}
