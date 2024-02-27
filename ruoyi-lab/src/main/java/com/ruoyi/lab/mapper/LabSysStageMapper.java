package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.LabSysStage;
import com.ruoyi.lab.domain.dto.LabSysStageMilSearchDto;

import java.util.List;

/**
 * 阶段 Mapper接口
 *
 * @author ruoyi
 * @date 2023-08-04
 */
public interface LabSysStageMapper {

    /**
     * 查询阶段及其相对应的里程
     *
     * @param stageMilDto
     * @return
     */
    public List<LabSysStage> selectLabSysStageMileageList(LabSysStageMilSearchDto stageMilDto);

    /**
     * 根据条件分页查询阶段列表
     *
     * @param labSysStage 阶段
     * @return 阶段集合
     */
    public List<LabSysStage> selectLabSysStageList(LabSysStage labSysStage);

    /**
     * 根据里程id查询阶段列表
     *
     * @param mileageId
     * @return
     */
    public List<LabSysStage> selectLabSysStageByMileageId(Long mileageId);

    /**
     * 查询所有阶段列表
     *
     * @return
     */
    public List<LabSysStage> selectStageAll();

    /**
     * 根据里程ID获取阶段选择框列表
     *
     * @param mileageId
     * @return
     */
    public List<Long> selectStageListByMileageId(Long mileageId);

    /**
     * 根据阶段id查询阶段
     *
     * @param stageId 阶段主键
     * @return 阶段
     */
    public LabSysStage selectLabSysStageById(Long stageId);

    /**
     * 校验阶段是否唯一
     *
     * @param stage
     * @return
     */
    public LabSysStage checkStageNameUnique(String stage);

    /**
     * 修改阶段
     *
     * @param labSysStage 阶段
     * @return 结果
     */
    public int updateLabSysStage(LabSysStage labSysStage);

    /**
     * 新增阶段
     *
     * @param labSysStage 阶段
     * @return 结果
     */
    public int insertLabSysStage(LabSysStage labSysStage);

    /**
     * 通过阶段Id删除阶段
     *
     * @param stageId 阶段Id
     * @return 结果
     */
    public int deleteLabSysStageById(Long stageId);

    /**
     * 批量删除阶段
     *
     * @param stageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysStageByIds(Long[] stageIds);

    /**
     * 获取 lab_sys_stage表中最大的主键ID
     *
     * @return
     */
    public Integer getLabSysStageMaxStageId();

    /**
     * 查询所有阶段
     *
     * @return
     */
    public List<String> searchAllStage();
}
