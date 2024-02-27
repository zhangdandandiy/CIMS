package com.ruoyi.mat.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 备品用户和楼层关联实体类 mat_sys_user_floor
 *
 * @author Dandan
 * @date 2023/8/21 13:22
 **/
public class MatSysUserFloor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 楼层Id
     */
    private Long floorId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setFloorId(Long floorId) {
        this.floorId = floorId;
    }

    public Long getFloorId() {
        return floorId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("userId", getUserId())
                .append("floorId", getFloorId())
                .toString();
    }
}
