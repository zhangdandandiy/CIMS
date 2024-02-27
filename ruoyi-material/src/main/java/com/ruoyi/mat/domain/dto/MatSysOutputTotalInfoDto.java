package com.ruoyi.mat.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Dandan
 * @date 2023/9/5 7:36
 **/
public class MatSysOutputTotalInfoDto {

    /**
     * 备品出库信息Id
     */
    @Excel(name = "备品出库ID")
    private Long matOutputId;

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
     * 备品出库数量
     */
    @Excel(name = "备品出库数量")
    private Long matOutputNumber;

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
     * 备品领取部门名称
     */
    @Excel(name = "备品领取部门名称")
    private String deptName;

    /**
     * 备品领取人
     */
    @Excel(name = "备品领取人")
    private String matReceiveUser;

    /**
     * 备品出库人
     */
    @Excel(name = "备品出库人")
    private String matOutputUser;

    /**
     * 备品储位
     */
    @Excel(name = "备品储位")
    private String matShelfCode;

    /**
     * 备品备品仓位
     */
    @Excel(name = "备品仓位")
    private String matFloorCode;

    /**
     * 备品领取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "备品出库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date matOutputTime;

    public void setMatOutputUser(String matOutputUser) {
        this.matOutputUser = matOutputUser;
    }

    public String getMatOutputUser() {
        return matOutputUser;
    }

    public void setMatShelfCode(String matShelfCode) {
        this.matShelfCode = matShelfCode;
    }

    public String getMatShelfCode() {
        return matShelfCode;
    }

    public void setMatFloorCode(String matFloorCode) {
        this.matFloorCode = matFloorCode;
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

    public void setMatOutputId(Long matOutputId) {
        this.matOutputId = matOutputId;
    }

    public Long getMatOutputId() {
        return matOutputId;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatOutputNumber(Long matOutputNumber) {
        this.matOutputNumber = matOutputNumber;
    }

    public Long getMatOutputNumber() {
        return matOutputNumber;
    }

    public void setMatReceiveUser(String matReceiveUser) {
        this.matReceiveUser = matReceiveUser;
    }

    public String getMatReceiveUser() {
        return matReceiveUser;
    }

    public void setMatOutputTime(Date matOutputTime) {
        this.matOutputTime = matOutputTime;
    }

    public Date getMatOutputTime() {
        return matOutputTime;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

}
