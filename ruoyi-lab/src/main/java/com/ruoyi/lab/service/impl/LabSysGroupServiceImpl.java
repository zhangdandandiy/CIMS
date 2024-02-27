package com.ruoyi.lab.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.lab.domain.LabSysGroup;
import com.ruoyi.lab.mapper.LabSysGroupMapper;
import com.ruoyi.lab.service.ILabSysGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Group Service业务层处理
 *
 * @author Dandan
 * @date 2023-08-04
 */

@Service
public class LabSysGroupServiceImpl implements ILabSysGroupService {
    @Autowired
    private LabSysGroupMapper labSysGroupMapper;

    /**
     * 查询Group
     *
     * @param id Group主键
     * @return Group
     */
    @Override
    public LabSysGroup selectLabSysGroupById(Long id) {
        return labSysGroupMapper.selectLabSysGroupById(id);
    }

    /**
     * 查询Group列表
     *
     * @param labSysGroup Group
     * @return Group
     */
    @Override
    public List<LabSysGroup> selectLabSysGroupList(LabSysGroup labSysGroup) {
        return labSysGroupMapper.selectLabSysGroupList(labSysGroup);
    }

    /**
     * 新增Group
     *
     * @param labSysGroup Group
     * @return 结果
     */
    @Override
    public int insertLabSysGroup(LabSysGroup labSysGroup) {
        labSysGroup.setCreateTime(DateUtils.getNowDate());
        return labSysGroupMapper.insertLabSysGroup(labSysGroup);
    }

    /**
     * 修改Group
     *
     * @param labSysGroup Group
     * @return 结果
     */
    @Override
    public int updateLabSysGroup(LabSysGroup labSysGroup) {
        labSysGroup.setUpdateTime(DateUtils.getNowDate());
        return labSysGroupMapper.updateLabSysGroup(labSysGroup);
    }

    /**
     * 批量删除Group
     *
     * @param ids 需要删除的Group主键
     * @return 结果
     */
    @Override
    public int deleteLabSysGroupByIds(Long[] ids) {
        return labSysGroupMapper.deleteLabSysGroupByIds(ids);
    }

    /**
     * 删除Group信息
     *
     * @param id Group主键
     * @return 结果
     */
    @Override
    public int deleteLabSysGroupById(Long id) {
        return labSysGroupMapper.deleteLabSysGroupById(id);
    }

    /**
     * 查询所有group
     *
     * @return
     */
    @Override
    public List<String> searchAllGroups() {
        return labSysGroupMapper.searchAllGroups();
    }

    /**
     * 校验group唯一性
     *
     * @param labSysGroup
     * @return
     */
    @Override
    public boolean checkLabSysGroupUnique(LabSysGroup labSysGroup) {
        LabSysGroup labSysGroupInfo = labSysGroupMapper.checkLabSysGroupUnique(labSysGroup);
        if (StringUtils.isNotNull(labSysGroupInfo)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }
}
