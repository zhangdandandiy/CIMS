package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Dandan
 * @date 2024/1/6 16:35
 **/
public class SysDeptRole {

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 角色ID
     */
    private Long roleId;

    public SysDeptRole(Long deptId, Long roleId) {
        this.deptId = deptId;
        this.roleId = roleId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("roleId", getRoleId())
                .toString();
    }

}
