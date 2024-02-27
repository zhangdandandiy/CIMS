package com.ruoyi.mat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 备品入库信息实体类 mat_sys_input
 *
 * @author Dandan
 * @date 2023/8/21 11:22
 **/
public class MatSysInput extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备品入库信息Id
     */
    private Long matInputId;

    /**
     * 备品编号
     */
    @Excel(name = "备品编号")
    private String matCode;

    /**
     * 备品入库数量
     */
    @Excel(name = "备品入库数量")
    private Long matInputNumber;

    /**
     * 备品入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "备品入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date matInputTime;

    /**
     * 备品入库人
     */
    @Excel(name = "备品入库人")
    private String matInputUser;

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

    /**
     * 备品数量
     */
    @Excel(name = "备品数量")
    private Long matNumber;

    public void setMatNumber(Long matNumber) {
        this.matNumber = matNumber;
    }

    public Long getMatNumber() {
        return matNumber;
    }

    public void setMatInputId(Long matInputId) {
        this.matInputId = matInputId;
    }

    public Long getMatInputId() {
        return matInputId;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatInputNumber(Long matInputNumber) {
        this.matInputNumber = matInputNumber;
    }

    public Long getMatInputNumber() {
        return matInputNumber;
    }

    public void setMatInputTime(Date matInputTime) {
        this.matInputTime = matInputTime;
    }

    public Date getMatInputTime() {
        return matInputTime;
    }

    public void setMatInputUser(String matInputUser) {
        this.matInputUser = matInputUser;
    }

    public String getMatInputUser() {
        return matInputUser;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("matInputId", getMatInputId())
                .append("matCode", getMatCode())
                .append("matInputNumber", getMatInputNumber())
                .append("matInputTime", getMatInputTime())
                .append("matInputUser", getMatInputUser())
                .append("matShelfCode", getMatShelfCode())
                .append("matNumber", getMatNumber())
                .toString();
    }

}
