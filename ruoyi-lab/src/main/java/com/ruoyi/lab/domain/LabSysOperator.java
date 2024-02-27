package com.ruoyi.lab.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 责任人对象 lab_sys_operator
 *
 * @author Dandan
 * @date 2023-08-03
 */
public class LabSysOperator extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 责任人 ID
     */
    @Excel(name = "责任人 ID")
    private Long operatorId;

    /**
     * 测试类型编号
     */
    @Excel(name = "测试类型编号")
    private Long testTypeId;

    /**
     * 责任人用户名
     */
    @Excel(name = "责任人用户名")
    private String operatorName;

    /**
     * 责任人姓名
     */
    @Excel(name = "责任人姓名")
    private String operatorFullName;

    /**
     * 责任人状态(0：正常 1：离职)
     */
    @Excel(name = "责任人状态")
    private String status;

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setTestTypeId(Long testTypeId) {
        this.testTypeId = testTypeId;
    }

    public Long getTestTypeId() {
        return testTypeId;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorFullName(String operatorFullName) {
        this.operatorFullName = operatorFullName;
    }

    public String getOperatorFullName() {
        return operatorFullName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("operatorId", getOperatorId())
                .append("testTypeId", getTestTypeId())
                .append("operatorName", getOperatorName())
                .append("operatorFullName", getOperatorFullName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("status", getStatus())
                .toString();
    }
}
