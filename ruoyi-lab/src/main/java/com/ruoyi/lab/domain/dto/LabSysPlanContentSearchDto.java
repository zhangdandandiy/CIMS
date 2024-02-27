package com.ruoyi.lab.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 测试项目要求--查询条件
 *
 * @author Dandan
 * @date 2023/8/7 14:26
 **/
public class LabSysPlanContentSearchDto extends BaseEntity {

    /**
     * id
     */
    private Long testGroupId;

    /**
     * 专案
     */
    private String product;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 里程
     */
    private String mileage;

    /**
     * Lab系统测试单号
     */
    private String testOrderNumber;

    /**
     * Group
     */
    private String groups;

    /**
     * Group测试项目是否完成(OK/NG)
     */
    private String completeStatus;

    public void setTestGroupId(Long testGroupId) {
        this.testGroupId = testGroupId;
    }

    public Long getTestGroupId() {
        return testGroupId;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
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

    public void setTestOrderNumber(String testOrderNumber) {
        this.testOrderNumber = testOrderNumber;
    }

    public String getTestOrderNumber() {
        return testOrderNumber;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getGroups() {
        return groups;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("product", getProduct())
                .append("stage", getStage())
                .append("mileage", getMileage())
                .append("testOrderNumber", getTestOrderNumber())
                .append("groups", getGroups())
                .toString();
    }

}
