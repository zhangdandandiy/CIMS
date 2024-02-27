package com.ruoyi.mat.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @author Dandan
 * @date 2023/8/21 19:56
 **/
public class MatSysOutputSearchListDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备品出库信息Id
     */
    private Long matOutputId;

    /**
     * 备品编号
     */
    private String matCode;

    /**
     * 备品名称
     */
    private String matName;

    /**
     * 备品领取部门Id
     */
    private Long matReceiveDeptId;

    /**
     * 备品领取人
     */
    private String matReceiveUser;

    /**
     * 备品领取时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date matOutputTime;

    /**
     * 备品出库人
     */
    private String matOutputUser;

    /**
     * 用户Id
     */
    private Long userId;

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatOutputId(Long matOutputId) {
        this.matOutputId = matOutputId;
    }

    public Long getMatOutputId() {
        return matOutputId;
    }

    public void setMatCode(String matCode) {
        this.matCode = matCode;
    }

    public String getMatCode() {
        return matCode;
    }

    public void setMatReceiveDeptId(Long matReceiveDeptId) {
        this.matReceiveDeptId = matReceiveDeptId;
    }

    public Long getMatReceiveDeptId() {
        return matReceiveDeptId;
    }

    public void setMatReceiveUser(String matReceiveUser) {
        this.matReceiveUser = matReceiveUser;
    }

    public String getMatReceiveUser() {
        return matReceiveUser;
    }

    public void setMatOutputTime(Date matOutputTime) {
        this.matOutputTime = matOutputTime;
    }

    public Date getMatOutputTime() {
        return matOutputTime;
    }

    public void setMatOutputUser(String matOutputUser) {
        this.matOutputUser = matOutputUser;
    }

    public String getMatOutputUser() {
        return matOutputUser;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
