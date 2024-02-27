package com.ruoyi.mat.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Dandan
 * @date 2023/9/6 8:33
 **/
public class MatSysCancelTotalInfoDto extends BaseEntity {
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
     * 备品名称
     */
    @Excel(name = "备品名称")
    private String matName;

    /**
     * 备品退库数量
     */
    @Excel(name = "备品退库数量")
    private Long matCancelNumber;

    /**
     * 备品单价
     */
    @Excel(name = "备品单价")
    private BigDecimal matPrice;

    /**
     * 备品总价
     */
    @Excel(name = "备品总价")
    private BigDecimal matTotalPrice;

    /**
     * 备品数量
     */
    @Excel(name = "备品数量")
    private Long matNumber;

    /**
     * 备品退库人
     */
    @Excel(name = "备品退库人")
    private String matCancelUser;

    /**
     * 备品退库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "备品退库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date matCancelTime;

    /**
     * 备品储位编号
     */
    @Excel(name = "备品储位编号")
    private String matShelfCode;

    /**
     * 备品仓位
     */
    @Excel(name = "备品仓位")
    private String matFloorCode;

    /**
     * 备品退库原因
     */
    @Excel(name = "备品退库原因")
    private String matCancelReason;

    /**
     * 备品供应商
     */
    @Excel(name = "备品供应商")
    private String matVendor;

    /**
     * 备品基准
     */
    @Excel(name = "备品基准")
    private String matProject;

    /**
     * 用户ID
     */
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setMatVendor(String matVendor) {
        this.matVendor = matVendor;
    }

    public String getMatVendor() {
        return matVendor;
    }

    public String getMatProject() {
        return matProject;
    }

    public void setMatProject(String matProject) {
        this.matProject = matProject;
    }

    public void setMatFloorCode(String matFloorCode) {
        this.matFloorCode = matFloorCode;
    }

    public void setMatNumber(Long matNumber) {
        this.matNumber = matNumber;
    }

    public Long getMatNumber() {
        return matNumber;
    }

    public String getMatFloorCode() {
        return matFloorCode;
    }

    public void setMatPrice(BigDecimal matPrice) {
        this.matPrice = matPrice;
    }

    public BigDecimal getMatPrice() {
        return matPrice;
    }

    public void setMatTotalPrice(BigDecimal matTotalPrice) {
        this.matTotalPrice = matTotalPrice;
    }

    public BigDecimal getMatTotalPrice() {
        return matTotalPrice;
    }

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
                .append("matNumber", getMatNumber())
                .append("matFloorCode", getMatFloorCode())
                .append("matPrice", getMatPrice())
                .append("matTotalPrice", getMatTotalPrice())
                .append("matVendor", getMatVendor())
                .append("matProject", getMatProject())
                .append("matCode", getMatCode())
                .append("matCancelNumber", getMatCancelNumber())
                .append("matCancelTime", getMatCancelTime())
                .append("matCancelUser", getMatCancelUser())
                .append("matShelfCode", getMatShelfCode())
                .append("matCancelReason", getMatCancelReason())
                .toString();
    }

}
