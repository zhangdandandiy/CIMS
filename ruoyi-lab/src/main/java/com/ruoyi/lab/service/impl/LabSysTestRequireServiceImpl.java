package com.ruoyi.lab.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.lab.domain.LabSysTestItem;
import com.ruoyi.lab.domain.LabSysTestRequire;
import com.ruoyi.lab.domain.dto.*;
import com.ruoyi.lab.mapper.LabSysTestRequireMapper;
import com.ruoyi.lab.service.ILabSysTestRequireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * group Service业务层处理
 *
 * @author Dandan
 * @date 2023-08-07
 */
@Service
public class LabSysTestRequireServiceImpl implements ILabSysTestRequireService {
    @Autowired
    private LabSysTestRequireMapper labSysTestRequireMapper;

    /**
     * 查询group
     *
     * @param testGroupId group主键
     * @return group
     */
    @Override
    public LabSysTestRequire selectLabSysTestGroupByTestGroupId(Long testGroupId) {
        return labSysTestRequireMapper.selectLabSysTestGroupByTestGroupId(testGroupId);
    }

    /**
     * 通过testItemId查询group
     *
     * @param testItemId
     * @return
     */
    @Override
    public LabSysTestRequire selectLabSysTestGroupByTestItemId(Long testItemId) {
        // 通过testItemId查询testItem
        LabSysTestItem labSysTestItem = labSysTestRequireMapper.selectLabSysTestItemByTestItemId(testItemId);
        return labSysTestRequireMapper.selectLabSysTestGroupByTestGroupId(labSysTestItem.getTestGroupId());
    }

    @Override
    public List<LabSysPlanContentProductDto> selectLabSysTestGroupList(LabSysPlanContentSearchDto contentSearch) {
        return labSysTestRequireMapper.selectLabSysTestGroupList(contentSearch);
    }

    @Override
    public List<LabSysTestRequire> selectLabSysTestGroupListByName(LabSysPlanContentSearchDto contentSearch) {
        return labSysTestRequireMapper.selectLabSysTestGroupListByName(contentSearch);
    }

    /**
     * 新增group
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    @Transactional
    @Override
    public String insertLabSysTestGroup(LabSysTestRequire labSysTestGroup) {
        labSysTestRequireMapper.insertLabSysTestGroup(labSysTestGroup);
        return insertLabSysTestItem(labSysTestGroup);
    }

    /**
     * 新增测试项目详细信息数据信息
     *
     * @param labSysTestGroup group对象
     */
    public String insertLabSysTestItem(LabSysTestRequire labSysTestGroup) {
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        LabSysPlanContentResultDto planContentResult = new LabSysPlanContentResultDto();
        planContentResult.setProduct(labSysTestGroup.getProduct());
        planContentResult.setStage(labSysTestGroup.getStage());
        planContentResult.setMileage(labSysTestGroup.getMileage());
        planContentResult.setTestOrderNumber(labSysTestGroup.getTestOrderNumber());
        planContentResult.setGroups(labSysTestGroup.getGroups());

        List<LabSysTestItem> labSysTestItemList = labSysTestGroup.getLabSysTestItemList();

        Long testGroupId = labSysTestGroup.getTestGroupId();
        if (StringUtils.isNotNull(labSysTestItemList)) {
            List<LabSysTestItem> list = new ArrayList<>();
            for (LabSysTestItem labSysTestItem : labSysTestItemList) {
                planContentResult.setTest(labSysTestItem.getTest());
                planContentResult.setCycles(labSysTestItem.getCycles());
                planContentResult.setTestItemDescription(labSysTestItem.getTestItemDescription());
                planContentResult.setTestCondition(labSysTestItem.getTestCondition());
                planContentResult.setTestStandard(labSysTestItem.getTestStandard());
                planContentResult.setTestType(labSysTestItem.getTestType());
                planContentResult.setOperatorName(labSysTestItem.getOperatorName());
                planContentResult.setTestPlanTime(labSysTestItem.getTestPlanTime());
                planContentResult.setUploadFlag(labSysTestItem.getUploadFlag());
                if (labSysTestItem.getUploadFlag() == 1) {
                    planContentResult.setCompleteStatus("0");
                } else {
                    planContentResult.setCompleteStatus("1");
                }
                planContentResult.setTestImage(labSysTestItem.getTestImage());

                if (!checkPlanContentResultUnique(planContentResult)) {
                    failureNum++;
                    failureMsg.append("<br/>" + planContentResult.getTest() + " 已存在，请勿重复添加");
                } else {
                    successNum++;
                    labSysTestItem.setTestGroupId(testGroupId);
                    list.add(labSysTestItem);
                }
            }

            // 由于数据库对于插入字段的限制，在这里对批量插入的数据进行分批处理
            if (list.size() > 0) {
                // 每批次的大小
                int batchSize = 5;
                int totalSize = list.size();
                for (int i = 0; i < totalSize; i += batchSize) {
                    // 防止最后一批不足 batchSize 个
                    int endIndex = Math.min(i + batchSize, totalSize);
                    List<LabSysTestItem> batch = list.subList(i, endIndex);
                    labSysTestRequireMapper.batchLabSysTestItem(batch);
                }
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，共" + failureNum + " 条数据新增失败！，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，共 " + successNum + " 条数据新增成功！");
        }
        return successMsg.toString();
    }

    /**
     * 校验测试项唯一性
     *
     * @param planContentResult
     * @return 结果
     */
    public boolean checkPlanContentResultUnique(LabSysPlanContentResultDto planContentResult) {
        LabSysPlanContentResultDto planContentResultInfo = labSysTestRequireMapper.checkPlanContentResultUnique(planContentResult);
        if (StringUtils.isNotNull(planContentResultInfo)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 修改group
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    @Transactional
    @Override
    public String updateLabSysTestGroup(LabSysTestRequire labSysTestGroup) {
        // 先删除再新增
        labSysTestRequireMapper.deleteLabSysTestItemByTestGroupId(labSysTestGroup.getTestGroupId());
        labSysTestRequireMapper.updateLabSysTestGroup(labSysTestGroup);
        return insertLabSysTestItem(labSysTestGroup);
    }

    /**
     * 置空文件路径
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    @Override
    public int updateLabSysTestGroupFilePath(Long testGroupId) {
        return labSysTestRequireMapper.updateLabSysTestGroupFilePath(testGroupId);
    }

    /**
     * 设置group合并文件地址
     *
     * @param labSysTestGroup group
     * @return 结果
     */
    @Override
    public int updateLabSysTestGroupStatus(LabSysTestRequire labSysTestGroup) {
        return labSysTestRequireMapper.updateLabSysTestGroup(labSysTestGroup);
    }

    /**
     * 修改item
     *
     * @param labSysTestItem
     * @return
     */
    @Override
    public int updateLabSysTestItem(LabSysTestItem labSysTestItem) {
        return labSysTestRequireMapper.updateLabSysTestItem(labSysTestItem);
    }

    /**
     * 批量删除group
     *
     * @param testGroupIds 需要删除的group主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLabSysTestGroupByTestGroupIds(Long[] testGroupIds) {
        labSysTestRequireMapper.deleteLabSysTestItemByTestGroupIds(testGroupIds);
        return labSysTestRequireMapper.deleteLabSysTestGroupByTestGroupIds(testGroupIds);
    }

    /**
     * 删除group信息
     *
     * @param testGroupId group主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLabSysTestGroupByTestGroupId(Long testGroupId) {
        labSysTestRequireMapper.deleteLabSysTestItemByTestGroupId(testGroupId);
        return labSysTestRequireMapper.deleteLabSysTestGroupByTestGroupId(testGroupId);
    }

    /**
     * 上传测试文件
     *
     * @param testItemId     测试项目Id
     * @param uploadBy       上传人
     * @param filePath       文件路径
     * @param testItemResult 测试项目结果(OK/NG)
     * @return
     */
    @Override
    public boolean updateTestPlanFile(Long testItemId, String uploadBy, String filePath, String testItemResult) {
        LabSysPlanContentUploadItemDto fileUpload = new LabSysPlanContentUploadItemDto();
        fileUpload.setTestItemId(testItemId);
        fileUpload.setUploadBy(uploadBy);
        fileUpload.setFilePath(filePath);
        fileUpload.setTestItemResult(testItemResult);
        // 通过testGroupId查询详细测试信息
        LabSysTestItem testItem = labSysTestRequireMapper.selectLabSysTestItemByTestItemId(testItemId);
        if (testItem.getTestPlanTime().compareTo(new Date()) < 0) {
            // 超出计划时间完成(delay 完成)
            fileUpload.setCompleteStatus("2");
        } else {
            // 按时完成
            fileUpload.setCompleteStatus("1");
        }
        return labSysTestRequireMapper.updateTestPlanFile(fileUpload) > 0;
    }

    /**
     * 上传Group测试文件
     *
     * @param testGroupId
     * @param groupFile
     * @param uploadBy
     * @return
     */
    @Override
    public boolean updateTestGroupFile(Long testGroupId, String groupFile, String uploadBy) {
        LabSysPlanContentUploadGroupDto fileUpload = new LabSysPlanContentUploadGroupDto();
        fileUpload.setTestGroupId(testGroupId);
        fileUpload.setGroupFile(groupFile);
        fileUpload.setUploadBy(uploadBy);
        return labSysTestRequireMapper.updateTestGroupFile(fileUpload) > 0;
    }

    /**
     * 保存group拼接后的文件
     *
     * @return
     */
    @Override
    public boolean updateTestMergeGroupFile(Long testGroupId, String groupMergeFile, String uploadBy) {
        LabSysPlanContentUploadGroupDto fileUpload = new LabSysPlanContentUploadGroupDto();
        fileUpload.setTestGroupId(testGroupId);
        fileUpload.setGroupMergeFile(groupMergeFile);
        fileUpload.setUploadBy(uploadBy);
        return labSysTestRequireMapper.updateTestMergeGroupFile(fileUpload) > 0;
    }

    /**
     * 上传补正测试文件
     *
     * @param fileUpload
     * @return
     */
    @Override
    public boolean updateTestMergeBzGroupFile(Long testGroupId, String groupMergeBzFile, String uploadBy) {
        LabSysPlanContentUploadGroupDto fileUpload = new LabSysPlanContentUploadGroupDto();
        fileUpload.setTestGroupId(testGroupId);
        fileUpload.setGroupMergeBzFile(groupMergeBzFile);
        fileUpload.setUploadBy(uploadBy);
        return labSysTestRequireMapper.updateTestMergeBzGroupFile(fileUpload) > 0;
    }

    /**
     * 获取group测试项的完成状态(0：未完成 1：已完成)
     *
     * @param groupId
     * @return
     */
    @Override
    public boolean getLabSysTestGroupResult(Long testGroupId) {
        int result = labSysTestRequireMapper.getLabSysTestGroupResult(testGroupId);
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 通过testItemId查询测试详细信息
     *
     * @param testItemId group主键
     * @return group
     */
    @Override
    public LabSysTestItem selectLabSysTestItemByTestItemId(Long testItemId) {
        return labSysTestRequireMapper.selectLabSysTestItemByTestItemId(testItemId);
    }

    /**
     * 查询详细测试结果
     *
     * @param search
     * @return
     */
    @Override
    public List<LabSysPlanContentResultItemDto> searchLabSysTestItemResult(LabSysPlanContentSearchDto search) {
        return labSysTestRequireMapper.searchLabSysTestItemResult(search);
    }

    /**
     * 查询group测试结果
     *
     * @param search
     * @return
     */
    @Override
    public List<LabSysPlanContentResultGroupDto> searchLabSysTestGroupResult(LabSysPlanContentSearchDto search) {
        return labSysTestRequireMapper.searchLabSysTestGroupResultList(search);
    }

    /**
     * 通过testGroupId获取文件路径列表
     *
     * @param testGroupId
     * @return
     */
    @Override
    public List<String> getFilePathByGroupId(Long testGroupId) {
        return labSysTestRequireMapper.getFilePathByGroupId(testGroupId);
    }

    /**
     * 获取 lab_sys_test_group表中最大的主键ID
     *
     * @return
     */
    @Override
    public Integer getLabSysTestGroupMaxGroupId() {
        Integer maxId = labSysTestRequireMapper.getLabSysTestGroupMaxGroupId();
        if (maxId == null) {
            maxId = 0;
        }
        return maxId;
    }

    /**
     * 通过testItemId删除测试项目
     *
     * @param testItemIds
     * @return
     */
    @Override
    public int deleteLabSysTestItemByTestItemId(Long[] testItemIds) {
        return labSysTestRequireMapper.deleteLabSysTestItemByTestItemIds(testItemIds);
    }

    @Transactional
    @Override
    public int deleteLabSysTestGroup(LabSysPlanContentSearchDto labSysPlanContentSearchDto) {
        // 获取Id
        List<Long> idList = labSysTestRequireMapper.selectLabSysTestGroupAllId(labSysPlanContentSearchDto);
        Long[] testGroupIds = idList.toArray(new Long[0]);
        // 根据 testGroupId 查询测试项列表
        List<LabSysTestItem> labSysTestItemList = labSysTestRequireMapper.LabSysTestItemByTestGroupIds(testGroupIds);
        if (labSysTestItemList.size() > 0) {
            // 批量删除 testItem
            labSysTestRequireMapper.deleteLabSysTestItemByTestGroupIds(testGroupIds);
        }
        // 批量删除 testGroup
        return labSysTestRequireMapper.deleteLabSysTestGroupByTestGroupIds(testGroupIds);
    }

    /**
     * 修改测试项目详细信息数据
     *
     * @param labSysTestItem 测试项目详细信息数据
     * @return 结果
     */
    @Override
    public int updateLabSysTestItemResult(LabSysTestItem labSysTestItem) {
        return labSysTestRequireMapper.updateLabSysTestItemResult(labSysTestItem);
    }

    @Override
    public boolean allCompleted(Long testItemId) {
        // 通过testItemId查询testItem
        LabSysTestItem labSysTestItem = labSysTestRequireMapper.selectLabSysTestItemByTestItemId(testItemId);
        LabSysPlanContentSearchDto search = new LabSysPlanContentSearchDto();
        search.setTestGroupId(labSysTestItem.getTestGroupId());
        List<LabSysPlanContentResultGroupDto> resultGroup = labSysTestRequireMapper.searchLabSysTestGroupResultList(search);
        String status = resultGroup.get(0).getStatus();
        return Objects.equals(status, "1");
    }

    @Override
    public String getFileNameByTestRequire(LabSysTestRequire labSysTestRequire) {
        // 合并文件
        String product = labSysTestRequire.getProduct();
        String stage = labSysTestRequire.getStage();
        String mileage = labSysTestRequire.getMileage();
        String groups = labSysTestRequire.getGroups();

        // 设置文件名及文件下载路径
        String downloadFileName = product + " " + stage + " " + mileage + " " + groups + ".xlsx";
        return downloadFileName;
    }

}
