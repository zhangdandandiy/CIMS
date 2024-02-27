package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.LabSysTestItem;
import com.ruoyi.lab.domain.LabSysTestRequire;
import com.ruoyi.lab.domain.dto.*;

import java.util.List;

/**
 * Testgroup Mapper接口
 *
 * @author Dandan
 * @date 2023-08-07
 */
public interface LabSysTestRequireMapper {

    /**
     * 查询 testGroup 列表
     *
     * @param labSysTestRequire
     * @return
     */
    List<LabSysTestRequire> selectLabSysTestRequire(LabSysTestRequire labSysTestRequire);

    /**
     * 通过groupId查询group
     *
     * @param testGroupId group主键
     * @return group
     */
    public LabSysTestRequire selectLabSysTestGroupByTestGroupId(Long testGroupId);

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
    public List<LabSysPlanContentResultGroupDto> searchLabSysTestGroupResultList(LabSysPlanContentSearchDto search);

    /**
     * 获取group测试项的完成状态(0：未完成 1：已完成)
     *
     * @param groupId
     * @return
     */
    public int getLabSysTestGroupResult(Long testGroupId);

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
    public int insertLabSysTestGroup(LabSysTestRequire labSysTestGroup);

    /**
     * 修改group
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    public int updateLabSysTestGroup(LabSysTestRequire labSysTestGroup);

    /**
     * 置空文件路径
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    public int updateLabSysTestGroupFilePath(Long testGroupId);

    /**
     * 修改item
     *
     * @param labSysTestItem
     * @return
     */
    public int updateLabSysTestItem(LabSysTestItem labSysTestItem);

    /**
     * 通过groupId删除group
     *
     * @param testGroupId group主键
     * @return 结果
     */
    public int deleteLabSysTestGroupByTestGroupId(Long testGroupId);

    /**
     * 通过testItemId删除测试项目
     *
     * @param testItemIds
     * @return
     */
    public int deleteLabSysTestItemByTestItemIds(Long[] testItemIds);

    /**
     * 批量删除group
     *
     * @param testGroupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysTestGroupByTestGroupIds(Long[] testGroupIds);

    /**
     * 根据testGroupIds查询测试项目列表
     *
     * @param testGroupIds
     * @return
     */
    public List<LabSysTestItem> LabSysTestItemByTestGroupIds(Long[] testGroupIds);

    /**
     * 批量删除测试项目详细信息数据
     *
     * @param testGroupIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysTestItemByTestGroupIds(Long[] testGroupIds);

    /**
     * 批量新增测试项目详细信息数据
     *
     * @param labSysTestItemList 测试项目详细信息数据列表
     * @return 结果
     */
    public int batchLabSysTestItem(List<LabSysTestItem> labSysTestItemList);


    /**
     * 通过group主键删除测试项目详细信息数据信息
     *
     * @param testGroupId groupID
     * @return 结果
     */
    public int deleteLabSysTestItemByTestGroupId(Long testGroupId);

    /**
     * 上传测试文件
     *
     * @param fileUpload
     * @return
     */
    public int updateTestPlanFile(LabSysPlanContentUploadItemDto fileUpload);

    /**
     * 上传group测试文件
     *
     * @param fileUpload
     * @return
     */
    public int updateTestGroupFile(LabSysPlanContentUploadGroupDto fileUpload);

    /**
     * 保存group拼接后的文件
     *
     * @return
     */
    public int updateTestMergeGroupFile(LabSysPlanContentUploadGroupDto fileUpload);

    /**
     * 上传补正测试文件
     *
     * @param fileUpload
     * @return
     */
    public int updateTestMergeBzGroupFile(LabSysPlanContentUploadGroupDto fileUpload);

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
     * 通过 product、stage、mileage、testOrderNumber、groups 删除 testGroup
     *
     * @param labSysPlanContentSearchDto
     * @return
     */
    public int deleteLabSysTestGroup(LabSysPlanContentSearchDto labSysPlanContentSearchDto);

    /**
     * 通过 product、stage、mileage、testOrderNumber、groups 查到所有 Id
     *
     * @param labSysPlanContentSearchDto
     * @return
     */
    public List<Long> selectLabSysTestGroupAllId(LabSysPlanContentSearchDto labSysPlanContentSearchDto);

    /**
     * 修改测试项目详细信息数据
     *
     * @param labSysTestItem 测试项目详细信息数据
     * @return 结果
     */
    public int updateLabSysTestItemResult(LabSysTestItem labSysTestItem);

    /**
     * 校验测试项唯一性
     *
     * @param planContentResult
     * @return 结果
     */
    public LabSysPlanContentResultDto checkPlanContentResultUnique(LabSysPlanContentResultDto planContentResult);


}
