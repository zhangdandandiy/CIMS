package com.ruoyi.lab.domain.entity;

import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 阶段和里程关联表 lab_sys_stage_mileage
 *
 * @author Dandan
 * @date 2023/8/4 10:45
 **/
public class LabSysStageMileage {
    private static final long serialVersionUID = 1L;

    /**
     * 阶段ID
     */
    @Excel(name = "阶段ID")
    private Long stageId;

    /**
     * 里程ID
     */
    @Excel(name = "里程ID")
    private Long mileageId;

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setMileageId(Long mileageId) {
        this.mileageId = mileageId;
    }

    public Long getMileageId() {
        return mileageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("stageId", getStageId())
                .append("mileageId", getMileageId())
                .toString();
    }
}
