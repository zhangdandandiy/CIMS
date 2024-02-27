package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.LabSysMileage;
import com.ruoyi.lab.domain.dto.LabSysMileageInsertDto;

import java.util.List;

/**
 * 里程 Mapper接口
 *
 * @author Dandan
 * @date 2023/8/4 14:10
 **/
public interface LabSysMileageMapper {

    /**
     * 通过里程ID查询里程
     *
     * @param mileageId 里程
     * @return 里程
     */
    public LabSysMileage selectLabSysMileageByMileageId(Long mileageId);

    /**
     * 通过阶段ID查询里程列表
     *
     * @param stageId
     * @return
     */
    public List<LabSysMileage> selectLabSysMileageByStageId(Long stageId);

    /**
     * 查询里程列表
     *
     * @param labSysMileage 里程
     * @return 里程集合
     */
    public List<LabSysMileage> selectLabSysMileageList(LabSysMileage labSysMileage);

    /**
     * 新增里程及关联数据信息
     *
     * @param labSysMileage 里程
     * @return 结果
     */
    public int insertLabSysMileage(LabSysMileageInsertDto labSysMileage);

    /**
     * 修改里程
     *
     * @param labSysMileage 里程
     * @return 结果
     */
    public int updateLabSysMileage(LabSysMileage labSysMileage);

    /**
     * 删除里程
     *
     * @param mileageId 里程主键
     * @return 结果
     */
    public int deleteLabSysMileageByMileageId(Long mileageId);

    /**
     * 通过里程id批量删除里程
     *
     * @param mileageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysMileageByMileageIds(Long[] mileageIds);

    /**
     * 通过阶段id批量删除里程信息
     *
     * @param stageIds
     * @return
     */
    public int deleteLabSysMileageByStageIds(Long[] stageIds);

    /**
     * 批量新增里程
     *
     * @param labSysMileageList 里程列表
     * @return 结果
     */
    public int batchLabSysMileage(List<LabSysMileage> labSysMileageList);

    /**
     * 获取 lab_sys_mileage表中最大的主键ID
     *
     * @return
     */
    public Integer getLabSysMileageMaxMileageId();

    /**
     * 通过阶段ID删除里程列表
     *
     * @param stageId
     * @return
     */
    public int deleteLabSysMileageByStageId(Long stageId);

    /**
     * 通过stage查询关联下的里程
     *
     * @param stage
     * @return
     */
    public List<String> searchAllMileage(String stage);

}
