package com.ruoyi.lab.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 查询阶段和里程条件实体类
 *
 * @author Dandan
 * @date 2023/8/4 15:15
 **/
public class LabSysStageMilSearchDto extends BaseEntity {

    /**
     * 阶段 ID
     */
    @Excel(name = "阶段ID")
    private Long stageId;

    /**
     * 阶段
     */
    @Excel(name = "阶段")
    private String stage;

    /**
     * 里程
     */
    @Excel(name = "里程")
    private String mileage;

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStage() {
        return stage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("stageId", getStageId())
                .append("stage", getStage())
                .append("mileage", getMileage())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }

}
