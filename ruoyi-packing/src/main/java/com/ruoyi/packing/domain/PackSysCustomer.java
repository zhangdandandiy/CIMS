package com.ruoyi.packing.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 包装校验客户信息对象 pack_sys_customer
 *
 * @author Dandan
 * @date 2023/9/8 19:36
 **/
public class PackSysCustomer extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 客户ID
     */
    @Excel(name = "客户ID")
    private Long customerId;

    /**
     * 客户名称
     */
    @Excel(name = "客户名称")
    private String customerName;

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("customerId", getCustomerId())
                .append("customerName", getCustomerName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
