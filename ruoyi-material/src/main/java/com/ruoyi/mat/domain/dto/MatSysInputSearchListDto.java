package com.ruoyi.mat.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 查询备品入库列表数据(数据过滤)
 *
 * @author Dandan
 * @date 2023/8/21 15:17
 **/
public class MatSysInputSearchListDto extends BaseEntity {

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
     * 备品名称
     */
    private String matName;

    /**
     * 备品入库时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date matInputTime;

    /**
     * 备品入库人
     */
    private String matInputUser;

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

    public void setMatInputTime(Date matInputTime) {
        this.matInputTime = matInputTime;
    }

    public Date getMatInputTime() {
        return matInputTime;
    }

    public void setMatInputUser(String matInputUser) {
        this.matInputUser = matInputUser;
    }

    public String getMatInputUser() {
        return matInputUser;
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
