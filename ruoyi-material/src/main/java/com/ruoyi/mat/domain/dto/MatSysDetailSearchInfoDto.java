package com.ruoyi.mat.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 查询备品信息数据列表(数据过滤)
 *
 * @author Dandan
 * @date 2023/8/21 15:52
 **/
public class MatSysDetailSearchInfoDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备品信息Id
     */
    private Long matId;

    /**
     * 备品编号
     */
    private String matCode;

    /**
     * 用户Id
     */
    private Long userId;

    public void setMatId(Long matId) {
        this.matId = matId;
    }

    public Long getMatId() {
        return matId;
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
