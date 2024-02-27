package com.ruoyi.mat.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * @author Dandan
 * @date 2023/8/22 8:20
 **/
public class MatSysCancelSearchListDto extends BaseEntity {

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
     * 备品名称
     */
    private String matName;

    /**
     * 备品退库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date matCancelTime;

    /**
     * 备品退库人
     */
    private String matCancelUser;

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

    public void setMatCancelTime(Date matCancelTime) {
        this.matCancelTime = matCancelTime;
    }

    public Date getMatCancelTime() {
        return matCancelTime;
    }

    public void setMatCancelUser(String matCancelUser) {
        this.matCancelUser = matCancelUser;
    }

    public String getMatCancelUser() {
        return matCancelUser;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

}
