package com.ruoyi.lab.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 测试类型对象 lab_sys_test_type
 *
 * @author Dandan
 * @date 2023-08-03
 */
public class LabSysTestType extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 测试类型ID
     */
    @Excel(name = "测试类型ID")
    private Long testTypeId;

    /**
     * 测试类型
     */
    @Excel(name = "测试类型")
    private String testType;

    /**
     * 责任人信息
     */
    private List<LabSysOperator> labSysOperatorList;

    public void setTestTypeId(Long testTypeId) {
        this.testTypeId = testTypeId;
    }

    public Long getTestTypeId() {
        return testTypeId;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestType() {
        return testType;
    }

    public List<LabSysOperator> getLabSysOperatorList() {
        return labSysOperatorList;
    }

    public void setLabSysOperatorList(List<LabSysOperator> labSysOperatorList) {
        this.labSysOperatorList = labSysOperatorList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("testTypeId", getTestTypeId())
                .append("testType", getTestType())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("labSysOperatorList", getLabSysOperatorList())
                .toString();
    }
}
