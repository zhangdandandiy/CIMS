package com.ruoyi.lab.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 里程对象 lab_sys_mileage
 *
 * @author Dandan
 * @date 2023-08-04
 */
public class LabSysMileage extends BaseEntity {
    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("mileageId", getMileageId())
                .append("mileage", getMileage())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
