package com.ruoyi.mat.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 备品楼层实体类 mat_sys_floor
 *
 * @author Dandan
 * @date 2023/8/22 9:33
 **/
public class MatSysFloor extends BaseEntity {

    private static final long serialVersionUID = 1L;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("floorId", getFloorId())
                .append("floorCode", getFloorCode())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }

}
