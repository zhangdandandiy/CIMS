package com.ruoyi.packing.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 包装校验信息对象 pack_sys_info
 *
 * @author Dandan
 * @date 2023/9/8 21:02
 **/
public class PackSysInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 流程ID
     */
    private Long processId;

    /**
     * 车间名称
     */
    @Excel(name = "车间名称")
    private String workshop;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String customerName;

    /**
     * 机种信息
     */
    @Excel(name = "机种信息")
    private String model;

    /**
     * 产品信息编码
     */
    @Excel(name = "产品信息编码")
    private String productInfoCode;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String productName;

    /**
     * 标签数量
     */
    @Excel(name = "标签数量")
    private Long labelNumber;

    /**
     * 外箱码
     */
    @Excel(name = "外箱码")
    private String outerCode;

    /**
     * 内部卷盘码
     */
    @Excel(name = "内部卷盘码")
    private String spoolCode;

    /**
     * 产品ID码
     */
    @Excel(name = "产品ID码")
    private String productCode;

    /**
     * 铝箔标签码
     */
    @Excel(name = "铝箔标签码")
    private String foilCode;

    /**
     * 扫描结果(OK/NG)
     */
    @Excel(name = "扫描结果(OK/NG)")
    private String scanResult;

    public void setProcessId(Long processId) {
        this.processId = processId;
    }

    public Long getProcessId() {
        return processId;
    }

    public void setWorkshop(String workshop) {
        this.workshop = workshop;
    }

    public String getWorkshop() {
        return workshop;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setProductInfoCode(String productInfoCode) {
        this.productInfoCode = productInfoCode;
    }

    public String getProductInfoCode() {
        return productInfoCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setLabelNumber(Long labelNumber) {
        this.labelNumber = labelNumber;
    }

    public Long getLabelNumber() {
        return labelNumber;
    }

    public void setOuterCode(String outerCode) {
        this.outerCode = outerCode;
    }

    public String getOuterCode() {
        return outerCode;
    }

    public void setSpoolCode(String spoolCode) {
        this.spoolCode = spoolCode;
    }

    public String getSpoolCode() {
        return spoolCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setFoilCode(String foilCode) {
        this.foilCode = foilCode;
    }

    public String getFoilCode() {
        return foilCode;
    }

    public void setScanResult(String scanResult) {
        this.scanResult = scanResult;
    }

    public String getScanResult() {
        return scanResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("processId", getProcessId())
                .append("workshop", getWorkshop())
                .append("customerName", getCustomerName())
                .append("model", getModel())
                .append("productInfoCode", getProductInfoCode())
                .append("productName", getProductName())
                .append("labelNumber", getLabelNumber())
                .append("outerCode", getOuterCode())
                .append("spoolCode", getSpoolCode())
                .append("productCode", getProductCode())
                .append("foilCode", getFoilCode())
                .append("scanResult", getScanResult())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
