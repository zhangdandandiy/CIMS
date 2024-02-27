package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.LabSysOperator;
import com.ruoyi.lab.domain.LabSysTestType;
import com.ruoyi.lab.domain.dto.LabSysTestOperSearchDto;

import java.util.List;

/**
 * 测试类型 Mapper接口
 *
 * @author Dandan
 * @date 2023-08-03
 */
public interface LabSysTestTypeMapper {
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
     * 删除测试类型
     *
     * @param testTypeId 测试类型主键
     * @return 结果
     */
    public int deleteLabSysTestTypeByTestTypeId(Long testTypeId);

    /**
     * 批量删除测试类型
     *
     * @param testTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysTestTypeByTestTypeIds(Long[] testTypeIds);

    /**
     * 批量删除责任人
     *
     * @param testTypeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysOperatorByTestTypeIds(Long[] testTypeIds);

    /**
     * 批量新增责任人
     *
     * @param labSysOperatorList 责任人列表
     * @return 结果
     */
    public int batchLabSysOperator(List<LabSysOperator> labSysOperatorList);


    /**
     * 通过测试类型主键删除责任人信息
     *
     * @param testTypeId 测试类型ID
     * @return 结果
     */
    public int deleteLabSysOperatorByTestTypeId(Long testTypeId);

    /**
     * 获取 lab_sys_test_type表中最大的自增主键ID
     *
     * @return
     */
    public Integer getLabSysTestMaxTestTypeId();

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
