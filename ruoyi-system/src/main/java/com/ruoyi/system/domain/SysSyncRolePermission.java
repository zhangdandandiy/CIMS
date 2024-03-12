package com.ruoyi.system.domain;

/**
 * 同步角色权限
 *
 * @author Dandan
 * @date 2024/3/9 9:41
 **/
public class SysSyncRolePermission {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id字符串
     */
    private String permissionIds;

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleId() {
        return roleId;
    }
}
