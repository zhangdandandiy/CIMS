package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.LabSysMileage;
import com.ruoyi.lab.domain.entity.LabSysStageMileage;

import java.util.List;

/**
 * 阶段和里程关联表 数据接口层
 *
 * @author Dandan
 * @date 2023/8/4 14:25
 **/
public interface LabSysStageMileageMapper {

    /**
     * 通过阶段id删除阶段与里程关联信息
     *
     * @param stageId
     * @return
     */
    public int deleteStageMileageByStageId(Long stageId);

    /**
     * 通过阶段id批量删除阶段与里程关联信息
     *
     * @param stageIds
     * @return
     */
    public int deleteStageMileageByStageIds(Long[] stageIds);

    /**
     * 通过里程id批量删除阶段与里程关联信息
     *
     * @param mileageIds
     * @return
     */
    public int deleteStageMileageByMileageIds(Long[] mileageIds);

    /**
     * 通过里程id查询里程使用数量
     *
     * @param mileageId
     * @return
     */
    public int countStageMileageByMileageId(Long mileageId);

    /**
     * 批量新增阶段与里程关联信息
     *
     * @param stageMileageList
     * @return
     */
    public int batchStageMileage(List<LabSysStageMileage> stageMileageList);

    /**
     * 新增阶段和里程关联信息
     *
     * @param labSysStageMileage
     * @return
     */
    public int insertStageMileage(LabSysStageMileage labSysStageMileage);

    /**
     * 删除里程与阶段关联信息
     *
     * @param sysStageMileage
     * @return
     */
    public int deleteStageMileageInfo(LabSysStageMileage sysStageMileage);

}
