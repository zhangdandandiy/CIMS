package com.ruoyi.mat.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @author Dandan
 * @date 2023/9/19 13:13
 **/
public class MatSysUserFloorResultInfoDto extends BaseEntity {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 楼层编号
     */
    private String floorCode;

    /**
     * 所属部门
     */
    private String deptName;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setFloorCode(String floorCode) {
        this.floorCode = floorCode;
    }

    public String getFloorCode() {
        return floorCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptName() {
        return deptName;
    }
}

