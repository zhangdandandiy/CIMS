package com.ruoyi.lab.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 测试项目要求--阶段信息
 *
 * @author Dandan
 * @date 2023/8/7 14:09
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabSysPlanContentStageDto {

    /**
     * 阶段
     */
    private String stage;

    /**
     * 供前端树状结构使用的唯一id
     */
    private Long id;

    /**
     * 里程信息
     */
    List<LabSysPlanContentMileageDto> children;

}
