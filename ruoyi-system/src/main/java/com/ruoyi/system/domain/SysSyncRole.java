package com.ruoyi.system.domain;

/**
 * 同步角色
 *
 * @author Dandan
 * @date 2024/3/9 9:21
 **/

public class SysSyncRole {

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色名称
     */
    private String description;

    public String getDescription() {
        return description;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
