package com.ruoyi.lab.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.lab.domain.LabSysMileage;
import com.ruoyi.lab.domain.LabSysStage;
import com.ruoyi.lab.domain.dto.LabSysStageMilSearchDto;
import com.ruoyi.lab.domain.entity.LabSysStageMileage;
import com.ruoyi.lab.mapper.LabSysMileageMapper;
import com.ruoyi.lab.mapper.LabSysStageMapper;
import com.ruoyi.lab.mapper.LabSysStageMileageMapper;
import com.ruoyi.lab.service.ILabSysStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/4 15:47
 **/
@Service
public class LabSysStageServiceImpl implements ILabSysStageService {

    @Autowired
    private LabSysStageMapper labSysStageMapper;

    @Autowired
    private LabSysMileageMapper labSysMileageMapper;

    @Autowired
    private LabSysStageMileageMapper labSysStageMileageMapper;

    /**
     * 新增阶段及其相对应的里程
     *
     * @param labSysStage
     * @return
     */
    @Transactional
    @Override
    public int insertLabSysStageMileage(LabSysStage labSysStage) {
        int rows = labSysStageMapper.insertLabSysStage(labSysStage);
        insertLabSysMileage(labSysStage);
        insertLabSysStageMileageConnect(labSysStage);
        return rows;
    }

    /**
     * 查询阶段及其相对应的里程
     *
     * @param stageMilDto
     * @return
     */
    @Override
    public List<LabSysStage> selectLabSysStageMileageList(LabSysStageMilSearchDto stageMilDto) {
        return labSysStageMapper.selectLabSysStageMileageList(stageMilDto);
    }

    /**
     * lab_sys_stage表中最大的主键ID
     *
     * @return
     */
    @Override
    public int getLabSysStageMaxStageId() {
        Integer maxId = labSysStageMapper.getLabSysStageMaxStageId();
        if (maxId == null) {
            maxId = 0;
        }
        return maxId;
    }

    /**
     * 修改阶段
     *
     * @param labSysStage 阶段
     * @return 结果
     */
    @Transactional
    @Override
    public int updateLabSysStage(LabSysStage labSysStage) {
        // 获取stageId
        Long stageId = labSysStage.getStageId();
        // 根据stageId删除里程信息
        deleteLabSysMileageByStageId(stageId);
        // 根据stageId删除阶段与里程关联数据
        deleteStageMileageByStageId(stageId);
        // 更新阶段数据
        int rows = labSysStageMapper.updateLabSysStage(labSysStage);
        // 新增里程信息
        insertLabSysMileage(labSysStage);
        // 新增阶段与里程关联数据
        insertLabSysStageMileageConnect(labSysStage);
        return rows;
    }

    /**
     * 根据stageId删除阶段及其里程信息
     *
     * @param stageId
     * @return
     */
    @Transactional
    @Override
    public int deleteLabSysStage(Long stageId) {
        // 根据stageId删除里程信息
        deleteLabSysMileageByStageId(stageId);
        // 根据stageId删除阶段与里程关联数据
        deleteStageMileageByStageId(stageId);
        int rows = labSysStageMapper.deleteLabSysStageById(stageId);
        return rows;
    }

    /**
     * 批量删除阶段及其里程信息
     *
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public int deleteLabSysStageByIds(Long[] ids) {
        // 批量删除里程信息
        labSysMileageMapper.deleteLabSysMileageByStageIds(ids);
        // 批量删除阶段里程关联信息
        labSysStageMileageMapper.deleteStageMileageByStageIds(ids);
        // 批量删除阶段信息
        int rows = labSysStageMapper.deleteLabSysStageByIds(ids);
        return rows;
    }

    /**
     * 查询所有阶段
     *
     * @return
     */
    @Override
    public List<String> searchAllStage() {
        return labSysStageMapper.searchAllStage();
    }

    /**
     * 新增里程信息
     *
     * @param labSysStage 测试类型对象
     */
    public void insertLabSysMileage(LabSysStage labSysStage) {
        List<LabSysMileage> labSysMileageList = labSysStage.getLabSysMileageList();
        if (StringUtils.isNotNull(labSysMileageList)) {
            List<LabSysMileage> list = new ArrayList<LabSysMileage>();
            for (LabSysMileage labSysMileage : labSysMileageList) {
                list.add(labSysMileage);
            }
            if (list.size() > 0) {
                labSysMileageMapper.batchLabSysMileage(list);
            }
        }
    }


    /**
     * 新增阶段和里程关系表数据
     *
     * @param labSysStage
     */
    public void insertLabSysStageMileageConnect(LabSysStage labSysStage) {
        // 获取 stageId
        Long stageId = labSysStage.getStageId();
        // 获取 MileageList
        List<LabSysMileage> labSysMileageList = labSysStage.getLabSysMileageList();
        if (labSysMileageList != null) {
            // 创建要插入的数据列表
            List<LabSysStageMileage> stageMileageList = new ArrayList<>();
            // 遍历 labSysMileageList 数组，将需要的数据组装到 LabSysStageMileage 对象中
            for (LabSysMileage labSysMileage : labSysMileageList) {
                Long mileageId = labSysMileage.getMileageId();
                LabSysStageMileage sysStageMileage = new LabSysStageMileage();
                sysStageMileage.setStageId(stageId);
                sysStageMileage.setMileageId(mileageId);
                stageMileageList.add(sysStageMileage);
            }
            if (stageMileageList.size() > 0) {
                labSysStageMileageMapper.batchStageMileage(stageMileageList);
            }
        }

    }

    /**
     * 通过阶段id删除里程数据
     *
     * @param stageId
     */
    public void deleteLabSysMileageByStageId(Long stageId) {
        labSysMileageMapper.deleteLabSysMileageByStageId(stageId);
    }

    /**
     * 通过阶段id删除阶段与里程关联信息
     *
     * @param stageId
     * @return
     */
    public void deleteStageMileageByStageId(Long stageId) {
        labSysStageMileageMapper.deleteStageMileageByStageId(stageId);
    }

}
