package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;

import java.util.Date;

/**
 * 用户实体对象
 *
 * @author Dandan
 * @date 2023/10/13 12:59
 **/
public class SysUserBU14 {

    /**
     * 员工序号
     */
    @Excel(name = "员工序号")
    private String PERNR;

    /**
     * 员工编号
     */
    @Excel(name = "员工编号")
    private String CPF01;

    /**
     * 员工姓名
     */
    @Excel(name = "员工姓名")
    private String CPF02;

    /**
     * 部门编号(11003313-Conn MES课)
     */
    @Excel(name = "部门编号")
    private String ORGEH;

    /**
     * 部门名称
     */
    @Excel(name = "部门名称")
    private String O_STEXT;

    /**
     * 离职日期
     */
    @Excel(name = "离职日期")
    private Date CPF35;

    /**
     * BU归属
     */
    @Excel(name = "BU归属")
    private String LXBU;

    /**
     * 在职时间
     */
    @Excel(name = "在职时间")
    private String NY;

    public void setPERNR(String PERNR) {
        this.PERNR = PERNR;
    }

    public String getPERNR() {
        return PERNR;
    }

    public void setCPF01(String CPF01) {
        this.CPF01 = CPF01;
    }

    public String getCPF01() {
        return CPF01;
    }

    public void setCPF02(String CPF02) {
        this.CPF02 = CPF02;
    }

    public String getCPF02() {
        return CPF02;
    }

    public void setORGEH(String ORGEH) {
        this.ORGEH = ORGEH;
    }

    public String getORGEH() {
        return ORGEH;
    }

    public void setO_STEXT(String O_STEXT) {
        O_STEXT = O_STEXT;
    }

    public String getO_STEXT() {
        return O_STEXT;
    }

    public void setCPF35(Date CPF35) {
        this.CPF35 = CPF35;
    }

    public Date getCPF35() {
        return CPF35;
    }

    public String getLXBU() {
        return LXBU;
    }

    public void setLXBU(String LXBU) {
        this.LXBU = LXBU;
    }

    public String getNY() {
        return NY;
    }

    public void setNY(String NY) {
        this.NY = NY;
    }
}
