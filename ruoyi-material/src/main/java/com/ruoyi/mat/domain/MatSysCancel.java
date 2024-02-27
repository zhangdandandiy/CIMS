package com.ruoyi.mat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 备品退库信息实体类 mat_sys_cancel
 *
 * @author Dandan
 * @date 2023/8/22 8:17
 **/
public class MatSysCancel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 备品退库信息Id
     */
    @Excel(name = "备品退库信息Id")
    private Long matCancelId;

    /**
     * 备品编号
     */
    @Excel(name = "备品编号")
    private String matCode;

    /**
     * 备品退库数量
     */
    @Excel(name = "备品退库数量")
    private Long matCancelNumber;

    /**
     * 备品退库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "备品退库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date matCancelTime;

    /**
     * 备品退库人
     */
    @Excel(name = "备品退库人")
    private String matCancelUser;

    /**
     * 备品储位编号
     */
    @Excel(name = "备品储位编号")
    private String matShelfCode;

    /**
     * 备品名称
     */
    @Excel(name = "备品名称")
    private String matName;

    @Excel(name = "备品退库原因")
    private String matCancelReason;

    public void setMatCancelId(Long matCancelId) {
        this.matCancelId = matCancelId;
    }

    public Long getMatCancelId() {
        return matCancelId;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatCancelNumber(Long matCancelNumber) {
        this.matCancelNumber = matCancelNumber;
    }

    public Long getMatCancelNumber() {
        return matCancelNumber;
    }

    public void setMatCancelTime(Date matCancelTime) {
        this.matCancelTime = matCancelTime;
    }

    public Date getMatCancelTime() {
        return matCancelTime;
    }

    public void setMatCancelUser(String matCancelUser) {
        this.matCancelUser = matCancelUser;
    }

    public String getMatCancelUser() {
        return matCancelUser;
    }

    public void setMatShelfCode(String matShelfCode) {
        this.matShelfCode = matShelfCode;
    }

    public String getMatShelfCode() {
        return matShelfCode;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatCancelReason(String matCancelReason) {
        this.matCancelReason = matCancelReason;
    }

    public String getMatCancelReason() {
        return matCancelReason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("matCancelId", getMatCancelId())
                .append("matCode", getMatCode())
                .append("matCancelNumber", getMatCancelNumber())
                .append("matCancelTime", getMatCancelTime())
                .append("matCancelUser", getMatCancelUser())
                .append("matShelfCode", getMatShelfCode())
                .append("matCancelReason", getMatCancelReason())
                .toString();
    }
}
