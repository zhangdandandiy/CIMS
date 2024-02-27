package com.ruoyi.lab.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 测试项目group表实体类 lab_sys_test_group
 *
 * @author Dandan
 * @date 2023/8/7 9:48
 **/
public class LabSysTestRequire extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 测试项目group ID
     */
    private Long testGroupId;

    /**
     * 专案
     */
    @Excel(name = "专案")
    private String product;

    /**
     * 阶段
     */
    @Excel(name = "阶段")
    private String stage;

    /**
     * 里程
     */
    @Excel(name = "里程")
    private String mileage;

    /**
     * Lab系统测试单号
     */
    @Excel(name = "Lab系统测试单号")
    private String testOrderNumber;

    /**
     * Group
     */
    @Excel(name = "Group")
    private String groups;

    /**
     * Group文件地址
     */
    @Excel(name = "Group文件地址")
    private String groupFile;

    /**
     * Group未全部完成的测试项拼接后的文件路径
     */
    @Excel(name = "Group未全部完成的测试项拼接后的文件路径")
    private String groupNoCompletedMergeFile;

    /**
     * Group测试项拼接后的文件地址
     */
    @Excel(name = "Group测试项拼接后的文件路径")
    private String groupMergeFile;

    /**
     * Group测试项的补正文件路径
     */
    @Excel(name = "Group测试项的补正文件路径")
    private String groupMergeBzFile;

    /**
     * 测试报告Group上传人
     */
    @Excel(name = "测试报告Group上传人")
    private String uploadBy;

    /**
     * 测试报告Group上传时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "测试报告Group上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /**
     * 测试项目详细信息数据信息
     */
    private List<LabSysTestItem> labSysTestItemList;

    public void setGroupNoCompletedMergeFile(String groupNoCompletedMergeFile) {
        this.groupNoCompletedMergeFile = groupNoCompletedMergeFile;
    }

    public String getGroupNoCompletedMergeFile() {
        return groupNoCompletedMergeFile;
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

    public void setGroupFile(String groupFile) {
        this.groupFile = groupFile;
    }

    public String getGroupFile() {
        return groupFile;
    }

    public void setGroupMergeFile(String groupMergeFile) {
        this.groupMergeFile = groupMergeFile;
    }

    public String getGroupMergeFile() {
        return groupMergeFile;
    }

    public void setGroupMergeBzFile(String groupMergeBzFile) {
        this.groupMergeBzFile = groupMergeBzFile;
    }

    public String getGroupMergeBzFile() {
        return groupMergeBzFile;
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

    public List<LabSysTestItem> getLabSysTestItemList() {
        return labSysTestItemList;
    }

    public void setLabSysTestItemList(List<LabSysTestItem> labSysTestItemList) {
        this.labSysTestItemList = labSysTestItemList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("testGroupId", getTestGroupId())
                .append("product", getProduct())
                .append("stage", getStage())
                .append("mileage", getMileage())
                .append("testOrderNumber", getTestOrderNumber())
                .append("groups", getGroups())
                .append("groupFile", getGroupFile())
                .append("groupNoCompletedMergeFile", getGroupNoCompletedMergeFile())
                .append("groupMergeFile", getGroupMergeFile())
                .append("groupMergeBzFile", getGroupMergeBzFile())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("uploadBy", getUploadBy())
                .append("uploadTime", getUploadTime())
                .append("labSysTestItemList", getLabSysTestItemList())
                .toString();
    }

}
