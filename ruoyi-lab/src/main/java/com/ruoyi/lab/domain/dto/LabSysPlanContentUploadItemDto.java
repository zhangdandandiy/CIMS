package com.ruoyi.lab.domain.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 测试项目文件上传要更新的字段
 *
 * @author Dandan
 * @date 2023/8/7 15:57
 **/
public class LabSysPlanContentUploadItemDto {

    /**
     * 测试项目 ID
     */
    private Long testItemId;

    /**
     * 上传测试报告路径
     */
    private String filePath;

    /**
     * 测试报告上传人
     */
    private String uploadBy;

    /**
     * 测试项目测试结果(OK/NG)
     */
    private String testItemResult;

    /**
     * 单项测试项目是否完成(0:未完成 1:按时完成 2:delay完成)
     */
    private String completeStatus;

    public void setTestItemId(Long testItemId) {
        this.testItemId = testItemId;
    }

    public Long getTestItemId() {
        return testItemId;
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
                .append("filePath", getFilePath())
                .append("uploadBy", getUploadBy())
                .append("testItemResult", getTestItemResult())
                .append("completeStatus", getCompleteStatus())
                .toString();
    }

}
