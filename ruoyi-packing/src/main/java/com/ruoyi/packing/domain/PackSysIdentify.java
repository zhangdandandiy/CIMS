package com.ruoyi.packing.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 包装校验认证对象 pack_sys_identify
 *
 * @author Dandan
 * @date 2023/9/9 21:17
 **/
public class PackSysIdentify extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 认证日志ID
     */
    private Long identifyId;

    /**
     * 用户账号
     */
    @Excel(name = "用户账号")
    private String userName;

    /**
     * 用户输入密码
     */
    @Excel(name = "用户输入密码")
    private String password;

    /**
     * 认证结果(OK/NG)
     */
    @Excel(name = "认证结果(OK/NG)")
    private String identifyResult;

    public void setIdentifyId(Long identifyId) {
        this.identifyId = identifyId;
    }

    public Long getIdentifyId() {
        return identifyId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setIdentifyResult(String identifyResult) {
        this.identifyResult = identifyResult;
    }

    public String getIdentifyResult() {
        return identifyResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("identifyId", getIdentifyId())
                .append("userName", getUserName())
                .append("password", getPassword())
                .append("identifyResult", getIdentifyResult())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
