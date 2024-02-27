package com.ruoyi.mat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 备品出库信息实体类 mat_sys_output
 *
 * @author Dandan
 * @date 2023/8/21 19:40
 **/
public class MatSysOutput {

    /**
     * 备品出库信息Id
     */
    private Long matOutputId;

    /**
     * 备品编号
     */
    @Excel(name = "备品编号")
    private String matCode;

    /**
     * 备品出库数量
     */
    @Excel(name = "备品出库数量")
    private Long matOutputNumber;

    /**
     * 备品领取部门Id
     */
    @Excel(name = "备品领取部门Id")
    private Long matReceiveDeptId;

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
     * 备品领取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "备品领取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date matOutputTime;

    /**
     * 备品出库人
     */
    @Excel(name = "备品出库人")
    private String matOutputUser;

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

    public void setMatReceiveDeptId(Long matReceiveDeptId) {
        this.matReceiveDeptId = matReceiveDeptId;
    }

    public Long getMatReceiveDeptId() {
        return matReceiveDeptId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("matOutputId", getMatOutputId())
                .append("matCode", getMatCode())
                .append("matOutputNumber", getMatOutputNumber())
                .append("matReceiveDeptId", getMatReceiveDeptId())
                .append("matReceiveUser", getMatReceiveUser())
                .append("matOutputTime", getMatOutputTime())
                .append("matOutputUser", getMatOutputUser())
                .append("matShelfCode", getMatShelfCode())
                .append("deptName", getDeptName())
                .toString();
    }

}
