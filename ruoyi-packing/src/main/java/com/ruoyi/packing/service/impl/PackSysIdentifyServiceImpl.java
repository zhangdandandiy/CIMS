package com.ruoyi.packing.service.impl;

import com.ruoyi.packing.domain.PackSysIdentify;
import com.ruoyi.packing.mapper.PackSysIdentifyMapper;
import com.ruoyi.packing.service.IPackSysIdentifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/9/9 21:22
 **/
@Service
public class PackSysIdentifyServiceImpl implements IPackSysIdentifyService {
    @Autowired
    private PackSysIdentifyMapper packSysIdentifyMapper;

    /**
     * 查询包装校验认证
     *
     * @param identifyId 包装校验认证主键
     * @return 包装校验认证
     */
    @Override
    public PackSysIdentify selectPackSysIdentifyByIdentifyId(Long identifyId) {
        return packSysIdentifyMapper.selectPackSysIdentifyByIdentifyId(identifyId);
    }

    /**
     * 查询包装校验认证列表
     *
     * @param packSysIdentify 包装校验认证
     * @return 包装校验认证
     */
    @Override
    public List<PackSysIdentify> selectPackSysIdentifyList(PackSysIdentify packSysIdentify) {
        return packSysIdentifyMapper.selectPackSysIdentifyList(packSysIdentify);
    }

    /**
     * 新增包装校验认证
     *
     * @param packSysIdentify 包装校验认证
     * @return 结果
     */
    @Override
    public int insertPackSysIdentify(PackSysIdentify packSysIdentify) {
        return packSysIdentifyMapper.insertPackSysIdentify(packSysIdentify);
    }

    /**
     * 修改包装校验认证
     *
     * @param packSysIdentify 包装校验认证
     * @return 结果
     */
    @Override
    public int updatePackSysIdentify(PackSysIdentify packSysIdentify) {
        return packSysIdentifyMapper.updatePackSysIdentify(packSysIdentify);
    }

    /**
     * 批量删除包装校验认证
     *
     * @param identifyIds 需要删除的包装校验认证主键
     * @return 结果
     */
    @Override
    public int deletePackSysIdentifyByIdentifyIds(Long[] identifyIds) {
        return packSysIdentifyMapper.deletePackSysIdentifyByIdentifyIds(identifyIds);
    }

    /**
     * 删除包装校验认证信息
     *
     * @param identifyId 包装校验认证主键
     * @return 结果
     */
    @Override
    public int deletePackSysIdentifyByIdentifyId(Long identifyId) {
        return packSysIdentifyMapper.deletePackSysIdentifyByIdentifyId(identifyId);
    }
}
