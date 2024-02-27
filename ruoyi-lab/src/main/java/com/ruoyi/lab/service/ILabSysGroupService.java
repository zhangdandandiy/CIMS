package com.ruoyi.lab.service;

import com.ruoyi.lab.domain.LabSysGroup;

import java.util.List;

/**
 * Group Service接口
 *
 * @author Dandan
 * @date 2023-08-04
 */

/**
 * GroupService接口
 *
 * @author ruoyi
 * @date 2023-08-05
 */
public interface ILabSysGroupService {
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
     * 批量删除Group
     *
     * @param ids 需要删除的Group主键集合
     * @return 结果
     */
    public int deleteLabSysGroupByIds(Long[] ids);

    /**
     * 删除Group信息
     *
     * @param id Group主键
     * @return 结果
     */
    public int deleteLabSysGroupById(Long id);

    /**
     * 查询所有group
     *
     * @return
     */
    public List<String> searchAllGroups();

    /**
     * 校验group唯一性
     *
     * @param labSysGroup
     * @return
     */
    public boolean checkLabSysGroupUnique(LabSysGroup labSysGroup);
}
