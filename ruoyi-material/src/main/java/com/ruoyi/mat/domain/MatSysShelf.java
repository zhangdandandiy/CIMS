package com.ruoyi.mat.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 备品储位实体类 mat_sys_shelf
 *
 * @author Dandan
 * @date 2023/8/22 9:47
 **/
public class MatSysShelf extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 储位Id
     */
    private Long shelfId;

    /**
     * 储位编号
     */
    @Excel(name = "储位编号")
    private String shelfCode;

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getShelfCode() {
        return shelfCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("shelfId", getShelfId())
                .append("shelfCode", getShelfCode())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }

}
