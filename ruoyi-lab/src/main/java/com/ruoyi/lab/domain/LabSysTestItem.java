package com.ruoyi.lab.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 测试项目详细信息数据对象 lab_sys_test_item
 *
 * @author Dandan
 * @date 2023-08-07
 */
public class LabSysTestItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 测试项目详细信息ID
     */
    private Long testItemId;

    /**
     * 测试项目group ID
     */
    @Excel(name = "测试项目group ID")
    private Long testGroupId;

    /**
     * Test#
     */
    @Excel(name = "Test#")
    private String test;

    /**
     * 循环次数
     */
    @Excel(name = "循环次数")
    private String cycles;

    /**
     * 测试项目描述
     */
    @Excel(name = "测试项目描述")
    private String testItemDescription;

    /**
     * 测试条件
     */
    @Excel(name = "测试条件")
    private String testCondition;

    /**
     * 测试判定标准
     */
    @Excel(name = "测试判定标准")
    private String testStandard;

    /**
     * 测试类型
     */
    @Excel(name = "测试类型")
    private String testType;

    /**
     * 责任人
     */
    @Excel(name = "责任人")
    private String operatorName;

    /**
     * 测试计划完成时间
     */
    @Excel(name = "测试计划完成时间")
    private Date testPlanTime;

    /**
     * 是否需要上传报告 1需要 0不需要 默认为1
     */
    private Long uploadFlag;

    /**
     * description/condition/standard/image
     */
    private String testImage;

    /**
     * 上传测试报告路径
     */
    @Excel(name = "上传测试报告路径")
    private String filePath;

    /**
     * 测试报告上传人
     */
    @Excel(name = "测试报告上传人")
    private String uploadBy;

    /**
     * 测试报告上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "测试报告上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /**
     * 测试项目测试结果(OK/NG)
     */
    @Excel(name = "测试项目测试结果(OK/NG)")
    private String testItemResult;

    /**
     * 单项测试项目是否完成(0:未完成 1:按时完成 2:delay完成)
     */
    @Excel(name = "单项测试项目是否完成(0:未完成 1:按时完成 2:delay完成)")
    private String completeStatus;

    /**
     * 供前端树状结构使用的唯一id
     */
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTestImage(String testImage) {
        this.testImage = testImage;
    }

    public String getTestImage() {
        return testImage;
    }

    public void setUploadFlag(Long uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

    public Long getUploadFlag() {
        return uploadFlag;
    }

    public void setCycles(String cycles) {
        this.cycles = cycles;
    }

    public String getCycles() {
        return cycles;
    }

    public void setTestItemId(Long testItemId) {
        this.testItemId = testItemId;
    }

    public Long getTestItemId() {
        return testItemId;
    }

    public void setTestGroupId(Long testGroupId) {
        this.testGroupId = testGroupId;
    }

    public Long getTestGroupId() {
        return testGroupId;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setTestItemDescription(String testItemDescription) {
        this.testItemDescription = testItemDescription;
    }

    public String getTestItemDescription() {
        return testItemDescription;
    }

    public void setTestCondition(String testCondition) {
        this.testCondition = testCondition;
    }

    public String getTestCondition() {
        return testCondition;
    }

    public void setTestStandard(String testStandard) {
        this.testStandard = testStandard;
    }

    public String getTestStandard() {
        return testStandard;
    }

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

    public void setTestPlanTime(Date testPlanTime) {
        this.testPlanTime = testPlanTime;
    }

    public Date getTestPlanTime() {
        return testPlanTime;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public String getUploadBy() {
        return uploadBy;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setTestItemResult(String testItemResult) {
        this.testItemResult = testItemResult;
    }

    public String getTestItemResult() {
        return testItemResult;
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
                .append("testItemId", getTestItemId())
                .append("testGroupId", getTestGroupId())
                .append("test#", getTest())
                .append("cycles", getCycles())
                .append("testItemDescription", getTestItemDescription())
                .append("testCondition", getTestCondition())
                .append("testStandard", getTestStandard())
                .append("testType", getTestType())
                .append("operatorName", getOperatorName())
                .append("testPlanTime", getTestPlanTime())
                .append("filePath", getFilePath())
                .append("uploadBy", getUploadBy())
                .append("uploadTime", getUploadTime())
                .append("uploadFlag", getUploadFlag())
                .append("testItemResult", getTestItemResult())
                .append("completeStatus", getCompleteStatus())
                .append("testImage", getTestImage())
                .toString();
    }
}
