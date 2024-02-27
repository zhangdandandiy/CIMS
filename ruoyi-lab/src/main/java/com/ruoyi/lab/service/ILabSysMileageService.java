package com.ruoyi.lab.service;

import com.ruoyi.lab.domain.LabSysMileage;
import com.ruoyi.lab.domain.dto.LabSysMileageInsertDto;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/4 18:45
 **/
public interface ILabSysMileageService {

    /**
     * 通过里程ID查询里程
     *
     * @param mileageId 里程
     * @return 里程
     */
    public LabSysMileage selectLabSysMileageByMileageId(Long mileageId);

    /**
     * 获取 lab_sys_mileage表中最大的主键ID
     *
     * @return
     */
    public int getLabSysMileageMaxMileageId();

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

    /**
     * 通过里程id批量删除里程
     *
     * @param mileageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysMileageByMileageIds(Long[] mileageIds);

    /**
     * 修改里程
     *
     * @param labSysMileage 里程
     * @return 结果
     */
    public int updateLabSysMileage(LabSysMileage labSysMileage);

    /**
     * 新增里程及关联数据信息
     *
     * @param labSysMileage 里程
     * @return 结果
     */
    public int insertLabSysMileage(LabSysMileageInsertDto labSysMileage);

    /**
     * 查询所有里程
     *
     * @param stage
     * @return
     */
    public List<String> selectAllMileage(String stage);

}
