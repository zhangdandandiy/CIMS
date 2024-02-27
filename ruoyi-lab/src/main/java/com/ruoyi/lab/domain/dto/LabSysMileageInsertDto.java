package com.ruoyi.lab.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 新增里程及关联数据实体类
 *
 * @author Dandan
 * @date 2023/8/7 9:01
 **/
public class LabSysMileageInsertDto extends BaseEntity {

    /**
     * 里程ID
     */
    @Excel(name = "里程ID")
    private Long mileageId;

    /**
     * 里程
     */
    @Excel(name = "里程")
    private String mileage;

    /**
     * 阶段ID
     */
    private Long stageId;

    public void setMileageId(Long mileageId) {
        this.mileageId = mileageId;
    }

    public Long getMileageId() {
        return mileageId;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getMileage() {
        return mileage;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getStageId() {
        return stageId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("mileageId", getMileageId())
                .append("mileage", getMileage())
                .append("stageId", getStageId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }

}
