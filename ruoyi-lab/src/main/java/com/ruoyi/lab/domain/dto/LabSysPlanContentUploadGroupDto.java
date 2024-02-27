package com.ruoyi.lab.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 测试项目Group文件上传要更新的字段
 *
 * @author Dandan
 * @date 2023/8/7 15:57
 **/
public class LabSysPlanContentUploadGroupDto {

    /**
     * 测试项目Group ID
     */
    private Long testGroupId;

    /**
     * 上传测试报告Group路径
     */
    private String groupFile;

    /**
     * Group测试项拼接后的文件地址
     */
    private String groupMergeFile;

    /**
     * Group测试项的补正文件路径
     */
    private String groupMergeBzFile;

    /**
     * 测试报告上传人
     */
    private String uploadBy;

    public void setTestGroupId(Long testGroupId) {
        this.testGroupId = testGroupId;
    }

    public Long getTestGroupId() {
        return testGroupId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("testGroupId", getTestGroupId())
                .append("groupFile", getGroupFile())
                .append("groupMergeFile", getGroupMergeFile())
                .append("groupMergeBzFile", getGroupMergeBzFile())
                .append("uploadBy", getUploadBy())
                .toString();
    }

}
