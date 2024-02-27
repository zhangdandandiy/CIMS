package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.domain.LabSysMileage;
import com.ruoyi.lab.domain.dto.LabSysMileageInsertDto;
import com.ruoyi.lab.domain.entity.LabSysStageMileage;
import com.ruoyi.lab.mapper.LabSysMileageMapper;
import com.ruoyi.lab.mapper.LabSysStageMileageMapper;
import com.ruoyi.lab.service.ILabSysMileageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/4 18:47
 **/
@Service
public class LabSysMileageServiceImpl implements ILabSysMileageService {

    @Autowired
    private LabSysMileageMapper labSysMileageMapper;

    @Autowired
    private LabSysStageMileageMapper labSysStageMileageMapper;

    /**
     * 通过里程ID查询里程
     *
     * @param mileageId 里程
     * @return 里程
     */
    @Override
    public LabSysMileage selectLabSysMileageByMileageId(Long mileageId) {
        return labSysMileageMapper.selectLabSysMileageByMileageId(mileageId);
    }

    /**
     * 获取 lab_sys_mileage表中最大的主键ID
     *
     * @return
     */
    @Override
    public int getLabSysMileageMaxMileageId() {
        Integer maxId = labSysMileageMapper.getLabSysMileageMaxMileageId();
        if (maxId == null) {
            maxId = 0;
        }
        return maxId;
    }

    /**
     * 通过阶段ID删除里程列表
     *
     * @param stageId
     * @return
     */
    @Override
    public int deleteLabSysMileageByStageId(Long stageId) {
        return labSysMileageMapper.deleteLabSysMileageByStageId(stageId);
    }

    /**
     * 通过stage查询关联下的里程
     *
     * @param stage
     * @return
     */
    @Override
    public List<String> searchAllMileage(String stage) {
        return labSysMileageMapper.searchAllMileage(stage);
    }

    /**
     * 通过里程id批量删除里程
     *
     * @param mileageIds 需要删除的数据主键集合
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLabSysMileageByMileageIds(Long[] mileageIds) {
        // 通过里程id批量删除阶段和里程关联信息
        labSysStageMileageMapper.deleteStageMileageByMileageIds(mileageIds);
        // 通过里程id批量删除阶段
        int rows = labSysMileageMapper.deleteLabSysMileageByMileageIds(mileageIds);
        return rows;
    }

    /**
     * 修改里程
     *
     * @param labSysMileage 里程
     * @return 结果
     */
    @Override
    public int updateLabSysMileage(LabSysMileage labSysMileage) {
        return labSysMileageMapper.updateLabSysMileage(labSysMileage);
    }

    /**
     * 新增里程及关联数据信息
     *
     * @param labSysMileage 里程
     * @return 结果
     */
    @Transactional
    @Override
    public int insertLabSysMileage(LabSysMileageInsertDto labSysMileage) {
        // 新增关联表的信息
        LabSysStageMileage labSysStageMileage = new LabSysStageMileage();
        labSysStageMileage.setStageId(labSysMileage.getStageId());
        labSysStageMileage.setMileageId(labSysMileage.getMileageId());
        labSysStageMileageMapper.insertStageMileage(labSysStageMileage);
        // 新增里程表信息
        int rows = labSysMileageMapper.insertLabSysMileage(labSysMileage);
        return rows;
    }

    @Override
    public List<String> selectAllMileage(String stage) {
        return labSysMileageMapper.searchAllMileage(stage);
    }

}
