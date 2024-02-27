package com.ruoyi.lab.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.lab.domain.LabSysOperator;
import com.ruoyi.lab.mapper.LabSysOperatorMapper;
import com.ruoyi.lab.service.ILabSysOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 责任人 Service业务层处理
 *
 * @author Dandan
 * @date 2023-08-04
 */
@Service
public class LabSysOperatorServiceImpl implements ILabSysOperatorService {
    @Autowired
    private LabSysOperatorMapper labSysOperatorMapper;

    /**
     * 查询责任人
     *
     * @param operatorId 责任人主键
     * @return 责任人
     */
    @Override
    public LabSysOperator selectLabSysOperatorByOperatorId(Long operatorId) {
        return labSysOperatorMapper.selectLabSysOperatorByOperatorId(operatorId);
    }

    /**
     * 查询责任人列表
     *
     * @param labSysOperator 责任人
     * @return 责任人
     */
    @Override
    public List<LabSysOperator> selectLabSysOperatorList(LabSysOperator labSysOperator) {
        return labSysOperatorMapper.selectLabSysOperatorList(labSysOperator);
    }

    /**
     * 新增责任人
     *
     * @param labSysOperator 责任人
     * @return 结果
     */
    @Override
    public int insertLabSysOperator(LabSysOperator labSysOperator) {
        return labSysOperatorMapper.insertLabSysOperator(labSysOperator);
    }

    /**
     * 修改责任人
     *
     * @param labSysOperator 责任人
     * @return 结果
     */
    @Override
    public int updateLabSysOperator(LabSysOperator labSysOperator) {
        return labSysOperatorMapper.updateLabSysOperator(labSysOperator);
    }

    /**
     * 批量删除责任人
     *
     * @param operatorIds 需要删除的责任人主键
     * @return 结果
     */
    @Override
    public int deleteLabSysOperatorByOperatorIds(Long[] operatorIds) {
        return labSysOperatorMapper.deleteLabSysOperatorByOperatorIds(operatorIds);
    }

    /**
     * 删除责任人信息
     *
     * @param operatorId 责任人主键
     * @return 结果
     */
    @Override
    public int deleteLabSysOperatorByOperatorId(Long operatorId) {
        return labSysOperatorMapper.deleteLabSysOperatorByOperatorId(operatorId);
    }

    /**
     * 根据测试类型查询所有相关联的责任人
     *
     * @return
     */
    @Override
    public List<String> searchAllOperator(String testType) {
        return labSysOperatorMapper.searchAllOperator(testType);
    }
}
