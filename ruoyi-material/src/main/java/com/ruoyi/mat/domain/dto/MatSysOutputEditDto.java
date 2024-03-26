package com.ruoyi.mat.domain.dto;

import com.ruoyi.mat.domain.MatSysOutput;

/**
 * @author Dandan
 * @date 2024/3/25 16:24
 **/
public class MatSysOutputEditDto extends MatSysOutput {

    /**
     * 修改后的备品出库数量
     */
    private Long matOutputNumberEdit;

    public Long getMatOutputNumberEdit() {
        return matOutputNumberEdit;
    }

    public void setMatOutputNumberEdit(Long matOutputNumberEdit) {
        this.matOutputNumberEdit = matOutputNumberEdit;
    }

}
