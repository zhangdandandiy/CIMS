package com.ruoyi.lab.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 阶段对象 lab_sys_stage
 *
 * @author Dandan
 * @date 2023-08-04
 */
public class LabSysStage extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 阶段ID
     */
    private Long stageId;

    /**
     * 阶段
     */
    @Excel(name = "阶段")
    private String stage;

    /**
     * 里程信息
     */
    private List<LabSysMileage> labSysMileageList;

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStage() {
        return stage;
    }

    public List<LabSysMileage> getLabSysMileageList() {
        return labSysMileageList;
    }

    public void setLabSysMileageList(List<LabSysMileage> labSysMileageList) {
        this.labSysMileageList = labSysMileageList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("stageId", getStageId())
                .append("stage", getStage())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("labSysMileageList", getLabSysMileageList())
                .toString();
    }
}
