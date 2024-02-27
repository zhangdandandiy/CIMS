package com.ruoyi.mat.domain.dto;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @author Dandan
 * @date 2023/8/22 20:09
 **/
public class MatSysUserFloorResultDto extends BaseEntity {

    /**
     * 楼层Id
     */
    @Excel(name = "楼层Id")
    private Long floorId;

    /**
     * 楼层编号
     */
    @Excel(name = "楼层编号")
    private String floorCode;

    /**
     * 用户账号
     */
    private String userName;

    /**
     * 用户昵称
     */
    private String nickName;

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getFloorId() {
        return floorId;
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
}
