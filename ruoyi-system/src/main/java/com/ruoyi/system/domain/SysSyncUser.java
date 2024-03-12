package com.ruoyi.system.domain;

/**
 * @author Dandan
 * @date 2024/3/9 9:56
 **/
public class SysSyncUser {

    /**
     * 登录用户名
     */
    private String username;

    /**
     * 实际姓名
     */
    private String realname;

    /**
     * 电话
     */
    private String phone;

    /**
     * 明文密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 所属部门id，多个用半角逗号隔开
     */
    private String selecteddeparts;

    /**
     * 所属角色id，多个用半角逗号隔开
     */
    private String selectedroles;

    /**
     * 工号
     */
    private String workNo;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getRealname() {
        return realname;
    }

    public String getSelecteddeparts() {
        return selecteddeparts;
    }

    public String getSelectedroles() {
        return selectedroles;
    }

    public String getUsername() {
        return username;
    }

    public String getWorkNo() {
        return workNo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setSelecteddeparts(String selecteddeparts) {
        this.selecteddeparts = selecteddeparts;
    }

    public void setSelectedroles(String selectedroles) {
        this.selectedroles = selectedroles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
    }
}
