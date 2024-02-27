package com.ruoyi.lab.service;

import com.ruoyi.lab.domain.LabSysOperator;

import java.util.List;

/**
 * 责任人 Service接口
 *
 * @author Dandan
 * @date 2023-08-04
 */
public interface ILabSysOperatorService {
    /**
     * 查询责任人
     *
     * @param operatorId 责任人主键
     * @return 责任人
     */
    public LabSysOperator selectLabSysOperatorByOperatorId(Long operatorId);

    /**
     * 查询责任人列表
     *
     * @param labSysOperator 责任人
     * @return 责任人集合
     */
    public List<LabSysOperator> selectLabSysOperatorList(LabSysOperator labSysOperator);

    /**
     * 新增责任人
     *
     * @param labSysOperator 责任人
     * @return 结果
     */
    public int insertLabSysOperator(LabSysOperator labSysOperator);

    /**
     * 修改责任人
     *
     * @param labSysOperator 责任人
     * @return 结果
     */
    public int updateLabSysOperator(LabSysOperator labSysOperator);

    /**
     * 批量删除责任人
     *
     * @param operatorIds 需要删除的责任人主键集合
     * @return 结果
     */
    public int deleteLabSysOperatorByOperatorIds(Long[] operatorIds);

    /**
     * 删除责任人信息
     *
     * @param operatorId 责任人主键
     * @return 结果
     */
    public int deleteLabSysOperatorByOperatorId(Long operatorId);

    /**
     * 根据测试类型查询所有相关联的责任人
     *
     * @return
     */
    public List<String> searchAllOperator(String testType);
}
