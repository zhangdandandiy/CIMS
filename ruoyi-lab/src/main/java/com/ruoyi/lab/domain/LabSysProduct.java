package com.ruoyi.lab.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 专案对象 lab_sys_product
 *
 * @author Dandan
 * @date 2023-08-04
 */
public class LabSysProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 专案ID
     */
    @Excel(name = "专案ID")
    private Long id;

    /**
     * 专案
     */
    @Excel(name = "专案")
    private String product;

    /**
     * 专案测试项目是否完成(OK/NG)
     */
    @Excel(name = "专案测试项目是否完成(OK/NG)")
    private String completeStatus;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }

    public void setCompleteStatus(String completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getCompleteStatus() {
        return completeStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("product", getProduct())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("completeStatus", getCompleteStatus())
                .toString();
    }
}
