package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.LabSysGroup;

import java.util.List;

/**
 * Group Mapper接口
 *
 * @author Dandan
 * @date 2023-08-04
 */

/**
 * GroupMapper接口
 *
 * @author ruoyi
 * @date 2023-08-05
 */
public interface LabSysGroupMapper {
    /**
     * 查询Group
     *
     * @param id Group主键
     * @return Group
     */
    public LabSysGroup selectLabSysGroupById(Long id);

    /**
     * 查询Group列表
     *
     * @param labSysGroup Group
     * @return Group集合
     */
    public List<LabSysGroup> selectLabSysGroupList(LabSysGroup labSysGroup);

    /**
     * 校验Group唯一性
     *
     * @param labSysGroup
     * @return
     */
    public LabSysGroup checkLabSysGroupUnique(LabSysGroup labSysGroup);

    /**
     * 新增Group
     *
     * @param labSysGroup Group
     * @return 结果
     */
    public int insertLabSysGroup(LabSysGroup labSysGroup);

    /**
     * 修改Group
     *
     * @param labSysGroup Group
     * @return 结果
     */
    public int updateLabSysGroup(LabSysGroup labSysGroup);

    /**
     * 删除Group
     *
     * @param id Group主键
     * @return 结果
     */
    public int deleteLabSysGroupById(Long id);

    /**
     * 批量删除Group
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysGroupByIds(Long[] ids);

    /**
     * 查询所有group
     *
     * @return
     */
    public List<String> searchAllGroups();
}
