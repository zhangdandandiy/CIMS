package com.ruoyi.mat.domain.dto;

/**
 * @author Dandan
 * @date 2023/8/21 19:54
 **/
public class MatSysOutputSearchInfoDto {

    /**
     * 备品出库信息Id
     */
    private Long matOutputId;

    /**
     * 备品编号
     */
    private String matCode;

    /**
     * 用户Id
     */
    private Long userId;

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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
