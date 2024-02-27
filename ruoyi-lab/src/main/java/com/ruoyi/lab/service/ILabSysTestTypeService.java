package com.ruoyi.lab.service;

import com.ruoyi.lab.domain.LabSysTestType;
import com.ruoyi.lab.domain.dto.LabSysTestOperSearchDto;

import java.util.List;

/**
 * 测试类型 Service接口
 *
 * @author Dandan
 * @date 2023-08-03
 */
public interface ILabSysTestTypeService {
    /**
     * 查询测试类型
     *
     * @param testTypeId 测试类型主键
     * @return 测试类型
     */
    public LabSysTestType selectLabSysTestTypeByTestTypeId(Long testTypeId);

    /**
     * 查询测试类型列表
     *
     * @param labSysTestType 测试类型
     * @return 测试类型集合
     */
    public List<LabSysTestType> selectLabSysTestTypeList(LabSysTestType labSysTestType);

    /**
     * 新增测试类型
     *
     * @param labSysTestType 测试类型
     * @return 结果
     */
    public int insertLabSysTestType(LabSysTestType labSysTestType);

    /**
     * 修改测试类型
     *
     * @param labSysTestType 测试类型
     * @return 结果
     */
    public int updateLabSysTestType(LabSysTestType labSysTestType);

    /**
     * 批量删除测试类型
     *
     * @param testTypeIds 需要删除的测试类型主键集合
     * @return 结果
     */
    public int deleteLabSysTestTypeByTestTypeIds(Long[] testTypeIds);

    /**
     * 删除测试类型信息
     *
     * @param testTypeId 测试类型主键
     * @return 结果
     */
    public int deleteLabSysTestTypeByTestTypeId(Long testTypeId);

    /**
     * 获取 lab_sys_test_type表中最大的自增主键ID
     *
     * @return
     */
    public int getLabSysTestMaxTestTypeId();

    /**
     * 查询测试类型及其对应的责任人
     *
     * @param labSysTestType
     * @return
     */
    public List<LabSysTestType> selectLabSysTestTypeOperatorList(LabSysTestOperSearchDto labSysTestType);

    /**
     * 查询所有测试类型
     *
     * @return
     */
    public List<String> selectAllTestType(String testType);

}
