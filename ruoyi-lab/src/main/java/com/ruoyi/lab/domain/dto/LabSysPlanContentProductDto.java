package com.ruoyi.lab.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 测试项目要求--专案信息
 *
 * @author Dandan
 * @date 2023/8/7 14:03
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabSysPlanContentProductDto {

    /**
     * 专案
     */
    private String product;

    /**
     * testGroup ID
     */
    private Long testGroupId;

    /**
     * 供前端树状结构使用的唯一id
     */
    private Long id;

    /**
     * 阶段信息
     */
    List<LabSysPlanContentStageDto> children;

}
