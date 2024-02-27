package com.ruoyi.mat.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @author Dandan
 * @date 2023/8/22 13:09
 **/
public class MatSysShelfSearchInfoDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 储位Id
     */
    private Long shelfId;

    /**
     * 储位编号
     */
    private String shelfCode;

    private Long userId;

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    public void setShelfCode(String shelfCode) {
        this.shelfCode = shelfCode;
    }

    public String getShelfCode() {
        return shelfCode;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
