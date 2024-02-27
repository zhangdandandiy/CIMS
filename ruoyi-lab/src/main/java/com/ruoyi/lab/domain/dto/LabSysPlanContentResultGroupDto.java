package com.ruoyi.lab.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * Group测试项目结果实体类
 *
 * @author Dandan
 * @date 2023/8/8 16:25
 **/
public class LabSysPlanContentResultGroupDto {

    /**
     * 测试项目group ID
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
     * Group
     */
    private String groups;

    /**
     * 测试计划完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date testPlanTime;

    /**
     * 完成状态(0：未完成 1：已完成)
     */
    private String status;

    /**
     * 测试项有无上传(0：未上传 1：有上传)
     */
    private String result;

    /**
     * Group测试项的补正文件路径
     */
    private String groupMergeBzFile;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setGroupMergeBzFile(String groupMergeBzFile) {
        this.groupMergeBzFile = groupMergeBzFile;
    }

    public String getGroupMergeBzFile() {
        return groupMergeBzFile;
    }

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

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getGroups() {
        return groups;
    }

    public void setTestPlanTime(Date testPlanTime) {
        this.testPlanTime = testPlanTime;
    }

    public Date getTestPlanTime() {
        return testPlanTime;
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
                .append("testGroupId", getTestGroupId())
                .append("product", getProduct())
                .append("stage", getStage())
                .append("mileage", getMileage())
                .append("groups", getGroups())
                .append("testPlanTime", getTestPlanTime())
                .append("status", getStatus())
                .toString();
    }

}
