package com.ruoyi.mat.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 备品楼层和储位关联实体类 mat_sys_floor_shelf
 *
 * @author Dandan
 * @date 2023/8/22 9:48
 **/
public class MatSysFloorShelf extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 楼层Id
     */
    @Excel(name = "楼层Id")
    private Long floorId;

    /**
     * 储位Id
     */
    @Excel(name = "储位Id")
    private Long shelfId;

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getFloorId() {
        return floorId;
    }

    public void setShelfId(Long shelfId) {
        this.shelfId = shelfId;
    }

    public Long getShelfId() {
        return shelfId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("floorId", getFloorId())
                .append("shelfId", getShelfId())
                .toString();
    }
}
