package com.ruoyi.lab.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 测试项目要求--里程信息
 *
 * @author Dandan
 * @date 2023/8/7 14:11
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabSysPlanContentMileageDto {

    /**
     * 里程
     */
    private String mileage;

    /**
     * 供前端树状结构使用的唯一id
     */
    private Long id;

    /**
     * group信息
     */
    List<LabSysPlanContentGroupDto> children;

}
