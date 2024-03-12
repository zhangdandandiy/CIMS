package com.ruoyi.system.domain;

/**
 * @author Dandan
 * @date 2024/3/9 9:55
 **/
public class SysSyncDeptPermission {

    /**
     * 部门id
     */
    private String departId;

    /**
     * 权限id字符串
     */
    private String permissionIds;

    public String getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

}
