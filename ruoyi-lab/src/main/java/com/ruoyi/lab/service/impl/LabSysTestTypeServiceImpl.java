package com.ruoyi.lab.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.lab.domain.LabSysOperator;
import com.ruoyi.lab.domain.LabSysTestType;
import com.ruoyi.lab.domain.dto.LabSysTestOperSearchDto;
import com.ruoyi.lab.mapper.LabSysTestTypeMapper;
import com.ruoyi.lab.service.ILabSysTestTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类型 Service业务层处理
 *
 * @author Dandan
 * @date 2023-08-03
 */
@Service
public class LabSysTestTypeServiceImpl implements ILabSysTestTypeService {
    @Autowired
    private LabSysTestTypeMapper labSysTestTypeMapper;

    /**
     * 查询测试类型
     *
     * @param testTypeId 测试类型主键
     * @return 测试类型
     */
    @Override
    public LabSysTestType selectLabSysTestTypeByTestTypeId(Long testTypeId) {
        return labSysTestTypeMapper.selectLabSysTestTypeByTestTypeId(testTypeId);
    }

    /**
     * 查询测试类型列表
     *
     * @param labSysTestType 测试类型
     * @return 测试类型
     */
    @Override
    public List<LabSysTestType> selectLabSysTestTypeList(LabSysTestType labSysTestType) {
        return labSysTestTypeMapper.selectLabSysTestTypeList(labSysTestType);
    }

    /**
     * 新增测试类型
     *
     * @param labSysTestType 测试类型
     * @return 结果
     */
    @Transactional
    @Override
    public int insertLabSysTestType(LabSysTestType labSysTestType) {
        labSysTestType.setCreateTime(DateUtils.getNowDate());
        int rows = labSysTestTypeMapper.insertLabSysTestType(labSysTestType);
        insertLabSysOperator(labSysTestType);
        return rows;
    }

    /**
     * 修改测试类型
     *
     * @param labSysTestType 测试类型
     * @return 结果
     */
    @Transactional
    @Override
    public int updateLabSysTestType(LabSysTestType labSysTestType) {
        labSysTestType.setUpdateTime(DateUtils.getNowDate());
        labSysTestTypeMapper.deleteLabSysOperatorByTestTypeId(labSysTestType.getTestTypeId())
        ;
        insertLabSysOperator(labSysTestType);
        return labSysTestTypeMapper.updateLabSysTestType(labSysTestType);
    }

    /**
     * 批量删除测试类型
     *
     * @param testTypeIds 需要删除的测试类型主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLabSysTestTypeByTestTypeIds(Long[] testTypeIds) {
        labSysTestTypeMapper.deleteLabSysOperatorByTestTypeIds(testTypeIds);
        return labSysTestTypeMapper.deleteLabSysTestTypeByTestTypeIds(testTypeIds);
    }

    /**
     * 删除测试类型信息
     *
     * @param testTypeId 测试类型主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteLabSysTestTypeByTestTypeId(Long testTypeId) {
        labSysTestTypeMapper.deleteLabSysOperatorByTestTypeId(testTypeId);
        return labSysTestTypeMapper.deleteLabSysTestTypeByTestTypeId(testTypeId);
    }

    /**
     * 获取 lab_sys_test_type表中最大的自增主键ID
     *
     * @return
     */
    @Override
    public int getLabSysTestMaxTestTypeId() {
        Integer maxId = labSysTestTypeMapper.getLabSysTestMaxTestTypeId();
        if (maxId == null) {
            maxId = 0;
        }
        return maxId;
    }

    /**
     * 查询测试类型及其对应的责任人
     *
     * @param labSysTestType
     * @return
     */
    @Override
    public List<LabSysTestType> selectLabSysTestTypeOperatorList(LabSysTestOperSearchDto labSysTestType) {
        return labSysTestTypeMapper.selectLabSysTestTypeOperatorList(labSysTestType);
    }

    @Override
    public List<String> selectAllTestType(String testType) {
        return labSysTestTypeMapper.selectAllTestType(testType);
    }

    /**
     * 新增责任人信息
     *
     * @param labSysTestType 测试类型对象
     */
    public void insertLabSysOperator(LabSysTestType labSysTestType) {
        List<LabSysOperator> labSysOperatorList = labSysTestType.getLabSysOperatorList();
        Long testTypeId = labSysTestType.getTestTypeId();
        if (StringUtils.isNotNull(labSysOperatorList)) {
            List<LabSysOperator> list = new ArrayList<LabSysOperator>();
            for (LabSysOperator labSysOperator : labSysOperatorList) {
                labSysOperator.setTestTypeId(testTypeId);
                list.add(labSysOperator);
            }
            if (list.size() > 0) {
                labSysTestTypeMapper.batchLabSysOperator(list);
            }
        }
    }
}
