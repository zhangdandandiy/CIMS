package com.ruoyi.lab.service;

import com.ruoyi.lab.domain.LabSysTestItem;
import com.ruoyi.lab.domain.LabSysTestRequire;
import com.ruoyi.lab.domain.dto.LabSysPlanContentProductDto;
import com.ruoyi.lab.domain.dto.LabSysPlanContentResultGroupDto;
import com.ruoyi.lab.domain.dto.LabSysPlanContentResultItemDto;
import com.ruoyi.lab.domain.dto.LabSysPlanContentSearchDto;

import java.util.List;

/**
 * group Service接口
 *
 * @author Dandan
 * @date 2023-08-07
 */
public interface ILabSysTestRequireService {
    /**
     * 通过testGroupId查询group
     *
     * @param testGroupId group主键
     * @return group
     */
    public LabSysTestRequire selectLabSysTestGroupByTestGroupId(Long testGroupId);

    /**
     * 通过testItemId查询group
     *
     * @param testItemId
     * @return
     */
    public LabSysTestRequire selectLabSysTestGroupByTestItemId(Long testItemId);

    /**
     * 查询group列表
     *
     * @param contentSearch
     * @return
     */
    public List<LabSysPlanContentProductDto> selectLabSysTestGroupList(LabSysPlanContentSearchDto contentSearch);

    /**
     * 查询group列表
     */
    public List<LabSysTestRequire> selectLabSysTestGroupListByName(LabSysPlanContentSearchDto contentSearch);

    /**
     * 新增group
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    public String insertLabSysTestGroup(LabSysTestRequire labSysTestGroup);

    /**
     * 修改group
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    public String updateLabSysTestGroup(LabSysTestRequire labSysTestGroup);

    /**
     * 置空文件路径
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    public int updateLabSysTestGroupFilePath(Long testGroupId);

    /**
     * 设置group合并文件地址
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    public int updateLabSysTestGroupStatus(LabSysTestRequire labSysTestGroup);

    /**
     * 修改item
     *
     * @param labSysTestItem
     * @return
     */
    public int updateLabSysTestItem(LabSysTestItem labSysTestItem);

    /**
     * 批量删除group
     *
     * @param testGroupIds 需要删除的group主键集合
     * @return 结果
     */
    public int deleteLabSysTestGroupByTestGroupIds(Long[] testGroupIds);

    /**
     * 删除group信息
     *
     * @param testGroupId group主键
     * @return 结果
     */
    public int deleteLabSysTestGroupByTestGroupId(Long testGroupId);

    /**
     * 上传测试文件
     *
     * @param testItemId     测试项目Id
     * @param uploadBy       上传人
     * @param filePath       文件路径
     * @param testItemResult 测试项目结果(OK/NG)
     * @return
     */
    public boolean updateTestPlanFile(Long testItemId, String uploadBy, String filePath, String testItemResult);

    /**
     * 上传Group测试文件
     *
     * @param testGroupId
     * @param groupFile
     * @param uploadBy
     * @return
     */
    public boolean updateTestGroupFile(Long testGroupId, String groupFile, String uploadBy);

    /**
     * 保存group拼接后的文件
     *
     * @return
     */
    public boolean updateTestMergeGroupFile(Long testGroupId, String groupMergeFile, String uploadBy);

    /**
     * 上传补正测试文件
     *
     * @param fileUpload
     * @return
     */
    public boolean updateTestMergeBzGroupFile(Long testGroupId, String groupMergeBzFile, String uploadBy);

    /**
     * 获取group测试项的完成状态(0：未完成 1：已完成)
     *
     * @param groupId
     * @return
     */
    public boolean getLabSysTestGroupResult(Long testGroupId);

    /**
     * 通过testItemId查询测试详细信息
     *
     * @param testItemId group主键
     * @return group
     */
    public LabSysTestItem selectLabSysTestItemByTestItemId(Long testItemId);

    /**
     * 查询详细测试结果
     *
     * @param search
     * @return
     */
    public List<LabSysPlanContentResultItemDto> searchLabSysTestItemResult(LabSysPlanContentSearchDto search);

    /**
     * 查询group测试结果
     *
     * @param search
     * @return
     */
    public List<LabSysPlanContentResultGroupDto> searchLabSysTestGroupResult(LabSysPlanContentSearchDto search);

    /**
     * 通过testGroupId获取文件路径列表
     *
     * @param testGroupId
     * @return
     */
    public List<String> getFilePathByGroupId(Long testGroupId);

    /**
     * 获取 lab_sys_test_group表中最大的主键ID
     *
     * @return
     */
    public Integer getLabSysTestGroupMaxGroupId();

    /**
     * 通过testItemId删除测试项目
     *
     * @param testItemIds
     * @return
     */
    public int deleteLabSysTestItemByTestItemId(Long[] testItemIds);

    /**
     * 通过 product、stage、mileage、testOrderNumber、groups 删除 testGroup
     *
     * @param labSysPlanContentSearchDto
     * @return
     */
    public int deleteLabSysTestGroup(LabSysPlanContentSearchDto labSysPlanContentSearchDto);

    /**
     * 修改测试项目详细信息数据
     *
     * @param labSysTestItem 测试项目详细信息数据
     * @return 结果
     */
    public int updateLabSysTestItemResult(LabSysTestItem labSysTestItem);

    /**
     * 根据testId获取是否全部完成测试项
     *
     * @param testItemId
     * @return
     */
    public boolean allCompleted(Long testItemId);

    /**
     * 获取文件名 专案-阶段-里程-group
     *
     * @param labSysTestRequire
     * @return
     */
    public String getFileNameByTestRequire(LabSysTestRequire labSysTestRequire);

}
