package com.ruoyi.lab.domain.dto;

import com.ruoyi.lab.domain.LabSysTestItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 测试项目要求--group信息
 *
 * @author Dandan
 * @date 2023/8/7 14:13
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabSysPlanContentGroupDto {

    /**
     * group
     */
    private String groups;

    /**
     * testGroup ID
     */
    private Long testGroupId;

    /**
     * Lab系统测试单号
     */
    private String testOrderNumber;

    /**
     * group文件
     */
    private String groupFile;

    /**
     * Group未全部完成的测试项拼接后的文件路径
     */
    private String groupNoCompletedMergeFile;

    /**
     * Group测试项拼接后的文件地址
     */
    private String groupMergeFile;

    /**
     * Group测试项的补正文件路径
     */
    private String groupMergeBzFile;

    /**
     * 供前端树状结构使用的唯一id
     */
    private Long id;

    /**
     * 测试项目内容信息
     */
    List<LabSysTestItem> children;

}
