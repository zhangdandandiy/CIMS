package com.ruoyi.mat.domain.dto;

import com.ruoyi.mat.domain.MatSysInput;

/**
 * @author Dandan
 * @date 2024/3/26 8:09
 **/
public class MatSysInputEditDto extends MatSysInput {

    /**
     * 修改后的备品入库数量
     */
    private Long matInputNumberEdit;

    public Long getMatInputNumberEdit() {
        return matInputNumberEdit;
    }

    public void setMatInputNumberEdit(Long matInputNumberEdit) {
        this.matInputNumberEdit = matInputNumberEdit;
    }

}
