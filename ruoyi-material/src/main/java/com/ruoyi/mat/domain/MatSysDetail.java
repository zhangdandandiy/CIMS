package com.ruoyi.mat.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 备品详细信息实体类 mat_sys_detail
 *
 * @author Dandan
 * @date 2023/8/21 8:45
 **/
public class MatSysDetail extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 备品信息Id
     */
    private Long matId;

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
     * 备品类型
     */
    @Excel(name = "备品类型")
    private String matType;

    /**
     * 备品型号
     */
    @Excel(name = "备品型号")
    private String matModel;

    /**
     * 备品规格
     */
    @Excel(name = "备品规格")
    private String matSpecification;

    /**
     * 备品描述
     */
    @Excel(name = "备品描述")
    private String matDescribe;

    /**
     * 备品分配时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "备品分配时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date matAssignTime;

    /**
     * 备品储存位置
     */
    @Excel(name = "备品储存位置")
    private String matShelfCode;

    /**
     * 备品楼层位置
     */
    @Excel(name = "备品楼层位置")
    private String matFloorCode;

    /**
     * 备品数量
     */
    @Excel(name = "备品数量")
    private Long matNumber;

    /**
     * 备品单价
     */
    @Excel(name = "备品单价")
    private BigDecimal matPrice;

    /**
     * 备品单位
     */
    @Excel(name = "备品单位")
    private String matUnit;

    /**
     * 备品下水准
     */
    @Excel(name = "备品下水准")
    private Long matLowerLevel;

    /**
     * 备品上水准
     */
    @Excel(name = "备品上水准")
    private Long matUpperLevel;

    /**
     * 备品供应商
     */
    @Excel(name = "备品供应商")
    private String matVendor;

    /**
     * 备品应用专案
     */
    @Excel(name = "备品应用专案")
    private String matProject;

    /**
     * 备品状态
     */
    @Excel(name = "备品状态")
    private String matStatus;

    public void setMatId(Long matId) {
        this.matId = matId;
    }

    public Long getMatId() {
        return matId;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatType(String matType) {
        this.matType = matType;
    }

    public String getMatType() {
        return matType;
    }

    public void setMatModel(String matModel) {
        this.matModel = matModel;
    }

    public String getMatModel() {
        return matModel;
    }

    public void setMatSpecification(String matSpecification) {
        this.matSpecification = matSpecification;
    }

    public String getMatSpecification() {
        return matSpecification;
    }

    public void setMatDescribe(String matDescribe) {
        this.matDescribe = matDescribe;
    }

    public String getMatDescribe() {
        return matDescribe;
    }

    public void setMatAssignTime(Date matAssignTime) {
        this.matAssignTime = matAssignTime;
    }

    public Date getMatAssignTime() {
        return matAssignTime;
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

    public void setMatNumber(Long matNumber) {
        this.matNumber = matNumber;
    }

    public Long getMatNumber() {
        return matNumber;
    }

    public void setMatPrice(BigDecimal matPrice) {
        this.matPrice = matPrice;
    }

    public BigDecimal getMatPrice() {
        return matPrice;
    }

    public void setMatUnit(String matUnit) {
        this.matUnit = matUnit;
    }

    public String getMatUnit() {
        return matUnit;
    }

    public void setMatLowerLevel(Long matLowerLevel) {
        this.matLowerLevel = matLowerLevel;
    }

    public Long getMatLowerLevel() {
        return matLowerLevel;
    }

    public void setMatUpperLevel(Long matUpperLevel) {
        this.matUpperLevel = matUpperLevel;
    }

    public Long getMatUpperLevel() {
        return matUpperLevel;
    }

    public void setMatVendor(String matVendor) {
        this.matVendor = matVendor;
    }

    public String getMatVendor() {
        return matVendor;
    }

    public void setMatProject(String matProject) {
        this.matProject = matProject;
    }

    public String getMatProject() {
        return matProject;
    }

    public void setMatStatus(String matStatus) {
        this.matStatus = matStatus;
    }

    public String getMatStatus() {
        return matStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("matId", getMatId())
                .append("matCode", getMatCode())
                .append("matName", getMatName())
                .append("matType", getMatType())
                .append("matModel", getMatModel())
                .append("matSpecification", getMatSpecification())
                .append("matDescribe", getMatDescribe())
                .append("matAssignTime", getMatAssignTime())
                .append("matShelfCode", getMatShelfCode())
                .append("matFloorCode", getMatFloorCode())
                .append("matNumber", getMatNumber())
                .append("matPrice", getMatPrice())
                .append("matUnit", getMatUnit())
                .append("matLowerLevel", getMatLowerLevel())
                .append("matUpperLevel", getMatUpperLevel())
                .append("matVendor", getMatVendor())
                .append("matProject", getMatProject())
                .append("matStatus", getMatStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
