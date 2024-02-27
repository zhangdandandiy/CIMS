package com.ruoyi.mat.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 查询备品入库列表数据(数据过滤)
 *
 * @author Dandan
 * @date 2023/8/21 16:16
 **/
public class MatSysInputSearchInfoDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备品入库信息Id
     */
    private Long matInputId;

    /**
     * 备品编号
     */
    private String matCode;

    /**
     * 用户Id
     */
    private Long userId;

    public void setMatInputId(Long matInputId) {
        this.matInputId = matInputId;
    }

    public Long getMatInputId() {
        return matInputId;
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
