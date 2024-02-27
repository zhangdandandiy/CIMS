package com.ruoyi.lab.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @author Dandan
 * @date 2023/9/19 13:59
 **/
public class LabSysPlanContentResultDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * Test#
     */
    private String test;

    /**
     * 循环次数
     */
    private String cycles;

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
    private Date testPlanTime;

    private String testImage;

    private Long uploadFlag;

    private String completeStatus;

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    public Long getUploadFlag() {
        return uploadFlag;
    }

    public void setUploadFlag(Long uploadFlag) {
        this.uploadFlag = uploadFlag;
    }

    public void setTestImage(String testImage) {
        this.testImage = testImage;
    }

    public String getTestImage() {
        return testImage;
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

    public void setCycles(String cycles) {
        this.cycles = cycles;
    }

    public String getCycles() {
        return cycles;
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

}
