package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Dandan
 * @date 2024/1/6 16:59
 **/
public class SysCheckUser {

    private String Code;

    private String Password;

    public SysCheckUser(String Code, String Password) {
        this.Code = Code;
        this.Password = Password;
    }

    public String getCode() {
        return Code;
    }

    public String getPassword() {
        return Password;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("Code", getCode())
                .append("Password", getPassword())
                .toString();
    }
}
