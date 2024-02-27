package com.ruoyi.packing.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 包装校验机种信息对象 pack_sys_model
 *
 * @author Dandan
 * @date 2023/9/8 19:54
 **/
public class PackSysModel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 机种ID
     */
    private Long modelId;

    /**
     * 机种名称
     */
    @Excel(name = "机种名称")
    private String modelName;

    /**
     * P/N
     */
    @Excel(name = "P/N")
    private String PN;

    /**
     * 产品名称
     */
    @Excel(name = "产品名称")
    private String productName;

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setPN(String PN) {
        this.PN = PN;
    }

    public String getPN() {
        return PN;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("modelId", getModelId())
                .append("modelName", getModelName())
                .append("PN", getPN())
                .append("productName", getProductName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
