package com.ruoyi.mat.domain.dto;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 查询备品信息数据列表(数据过滤)
 *
 * @author Dandan
 * @date 2023/8/21 14:06
 **/
public class MatSysDetailSearchListDto extends BaseEntity {

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
     * 备品名称
     */
    private String matName;

    /**
     * 备品类型
     */
    private String matType;

    /**
     * 备品储存位置
     */
    private String matShelfCode;

    /**
     * 备品楼层位置
     */
    private String matFloorCode;

    /**
     * 备品应用专案
     */
    private String matProject;

    /**
     * 备品状态(Idle、In Use)
     */
    private String matStatus;

    /**
     * 用户Id
     */
    private Long userId;

    public void setMatStatus(String matStatus) {
        this.matStatus = matStatus;
    }

    public String getMatStatus() {
        return matStatus;
    }

    public void setMatProject(String matProject) {
        this.matProject = matProject;
    }

    public String getMatProject() {
        return matProject;
    }

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

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatType(String matType) {
        this.matType = matType;
    }

    public String getMatType() {
        return matType;
    }

    public void setMatShelfCode(String matShelfCode) {
        this.matShelfCode = matShelfCode;
    }

    public String getMatShelfCode() {
        return matShelfCode;
    }

    public void setMatFloorCode(String matFloorCode) {
        this.matFloorCode = matFloorCode;
    }

    public String getMatFloorCode() {
        return matFloorCode;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

}
