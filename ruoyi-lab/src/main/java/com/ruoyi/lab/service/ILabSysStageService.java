package com.ruoyi.lab.service;

import com.ruoyi.lab.domain.LabSysStage;
import com.ruoyi.lab.domain.dto.LabSysStageMilSearchDto;

import java.util.List;

/**
 * 阶段 service接口
 *
 * @author Dandan
 * @date 2023/8/4 15:45
 **/
public interface ILabSysStageService {

    /**
     * 新增阶段及其相对应的里程
     *
     * @param labSysStage
     * @return
     */
    public int insertLabSysStageMileage(LabSysStage labSysStage);

    /**
     * 查询阶段及其相对应的里程
     *
     * @param stageMilDto
     * @return
     */
    public List<LabSysStage> selectLabSysStageMileageList(LabSysStageMilSearchDto stageMilDto);

    /**
     * lab_sys_stage表中最大的主键ID
     *
     * @return
     */
    public int getLabSysStageMaxStageId();

    /**
     * 修改阶段
     *
     * @param labSysStage 阶段
     * @return 结果
     */
    public int updateLabSysStage(LabSysStage labSysStage);

    /**
     * 根据stageId删除阶段及其里程信息
     *
     * @param stageId
     * @return
     */
    public int deleteLabSysStage(Long stageId);

    /**
     * 批量删除阶段及其里程信息
     *
     * @param ids
     * @return
     */
    public int deleteLabSysStageByIds(Long[] ids);

    /**
     * 查询所有阶段
     *
     * @return
     */
    public List<String> searchAllStage();

}
