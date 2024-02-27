package com.ruoyi.mat.mapper;

import com.ruoyi.mat.domain.MatSysShelf;
import com.ruoyi.mat.domain.dto.MatSysShelfSearchInfoDto;

import java.util.List;

/**
 * 备品储位 Mapper接口
 *
 * @author Dandan
 * @date 2023/8/22 11:10
 **/
public interface MatSysShelfMapper {

    /**
     * 查询备品储位
     *
     * @param matSysShelf 备品储位信息
     * @return 备品储位
     */
    public MatSysShelf selectMatSysShelfByShelfId(MatSysShelfSearchInfoDto matSysShelf);

    /**
     * 校验储位编号唯一性
     *
     * @param matSysShelf 备品储位信息
     * @return 结果
     */
    public MatSysShelf checkMatSysShelfUnique(MatSysShelfSearchInfoDto matSysShelf);

    /**
     * 查询备品储位列表
     *
     * @param matSysShelf 备品储位信息
     * @return 备品储位集合
     */
    public List<MatSysShelf> selectMatSysShelfList(MatSysShelfSearchInfoDto matSysShelf);

    /**
     * 查询所有储位编号
     *
     * @param matSysShelf
     * @return
     */
    public List<String> selectAllShelfCode(Long userId);

    /**
     * 根据用户名查询储位编号
     *
     * @param userName
     * @return
     */
    public List<String> selectShelfCodeByUserName(String userName);

    /**
     * 新增备品储位
     *
     * @param matSysShelf 备品储位
     * @return 结果
     */
    public int insertMatSysShelf(MatSysShelf matSysShelf);

    /**
     * 修改备品储位
     *
     * @param matSysShelf 备品储位
     * @return 结果
     */
    public int updateMatSysShelf(MatSysShelf matSysShelf);

    /**
     * 删除备品储位
     *
     * @param shelfId 备品储位主键
     * @return 结果
     */
    public int deleteMatSysShelfByShelfId(Long shelfId);

    /**
     * 批量删除备品储位
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
    public Integer getMatSysShelfMaxShelfId();

}
