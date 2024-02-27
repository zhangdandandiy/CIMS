package com.ruoyi.lab.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Group对象 lab_sys_group
 *
 * @author Dandan
 * @date 2023-08-04
 */
public class LabSysGroup extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * Group ID
     */
    @Excel(name = "Group ID")
    private Long id;

    /**
     * Group
     */
    @Excel(name = "Group")
    private String groups;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getGroups() {
        return groups;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("groups", getGroups())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
