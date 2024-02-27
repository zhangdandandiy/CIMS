package com.ruoyi.lab.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 查询测试项目相关负责人条件实体类
 *
 * @author Dandan
 * @date 2023/8/4 13:22
 **/
public class LabSysTestOperSearchDto extends BaseEntity {

    /**
     * 测试类型
     */
    @Excel(name = "测试类型")
    private String testType;

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

    public void setTestType(String testType) {
        this.testType = testType;
    }

    public String getTestType() {
        return testType;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("testType", getTestType())
                .append("operatorName", getOperatorName())
                .append("operatorFullName", getOperatorFullName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }

}
