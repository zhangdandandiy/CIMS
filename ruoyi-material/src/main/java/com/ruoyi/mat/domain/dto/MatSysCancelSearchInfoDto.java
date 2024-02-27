package com.ruoyi.mat.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * @author Dandan
 * @date 2023/8/22 8:19
 **/
public class MatSysCancelSearchInfoDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备品退库信息Id
     */
    private Long matCancelId;

    /**
     * 备品编号
     */
    private String matCode;

    /**
     * 用户Id
     */
    private Long userId;

    public void setMatCancelId(Long matCancelId) {
        this.matCancelId = matCancelId;
    }

    public Long getMatCancelId() {
        return matCancelId;
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
