package com.ruoyi.lab.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 详细测试项目结果实体类
 *
 * @author Dandan
 * @date 2023/8/8 13:38
 **/
public class LabSysPlanContentResultItemDto {

    /**
     * 测试项目详细信息ID
     */
    private Long testItemId;

    /**
     * Lab系统测试单号
     */
    private String testOrderNumber;

    /**
     * Group
     */
    private String groups;

    /**
     * Test#
     */
    private String test;

    /**
     * 测试项目描述
     */
    private String testItemDescription;

    /**
     * 测试条件
     */
    private String testCondition;

    /**
     * 测试判定标准
     */
    private String testStandard;

    /**
     * 测试类型
     */
    private String testType;

    /**
     * 责任人
     */
    private String operatorName;

    /**
     * 测试计划完成时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date testPlanTime;

    /**
     * 测试项目测试结果(OK/NG)
     */
    private String testItemResult;

    /**
     * 单项测试项目是否完成(0:未完成 1:按时完成 2:delay完成)
     */
    private String completeStatus;

    /**
     * 测试报告上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date uploadTime;

    /**
     * 上传测试报告路径
     */
    private String filePath;

    /**
     * 是否需要上传标志
     */
    private Long uploadFlag;

    /**
     * 循环次数
     */
    private String cycles;

    public void setUploadFlag(Long uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

    public Long getUploadFlag() {
        return uploadFlag;
    }

    public String getCycles() {
        return cycles;
    }

    public void setCycles(String cycles) {
        this.cycles = cycles;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getGroups() {
        return groups;
    }

    public void setTestItemId(Long testItemId) {
        this.testItemId = testItemId;
    }

    public Long getTestItemId() {
        return testItemId;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public void setTestOrderNumber(String testOrderNumber) {
        this.testOrderNumber = testOrderNumber;
    }

    public String getTestOrderNumber() {
        return testOrderNumber;
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
                .append("test#", getTest())
                .append("testItemDescription", getTestItemDescription())
                .append("testCondition", getTestCondition())
                .append("testStandard", getTestStandard())
                .append("testType", getTestType())
                .append("operatorName", getOperatorName())
                .append("testPlanTime", getTestPlanTime())
                .append("filePath", getFilePath())
                .append("uploadTime", getUploadTime())
                .append("testItemResult", getTestItemResult())
                .append("completeStatus", getCompleteStatus())
                .append("testOrderNumber", getTestOrderNumber())
                .append("groups", getGroups())
                .toString();
    }

}
