package com.ruoyi.packing.service.impl;

import com.ruoyi.packing.domain.PackSysInfo;
import com.ruoyi.packing.mapper.PackSysInfoMapper;
import com.ruoyi.packing.service.IPackSysInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/9/8 21:07
 **/
@Service
public class PackSysInfoServiceImpl implements IPackSysInfoService {
    @Autowired
    private PackSysInfoMapper packSysInfoMapper;

    /**
     * 查询包装校验信息
     *
     * @param processId 包装校验信息主键
     * @return 包装校验信息
     */
    @Override
    public PackSysInfo selectPackSysInfoByProcessId(Long processId) {
        return packSysInfoMapper.selectPackSysInfoByProcessId(processId);
    }

    /**
     * 查询包装校验信息列表
     *
     * @param packSysInfo 包装校验信息
     * @return 包装校验信息
     */
    @Override
    public List<PackSysInfo> selectPackSysInfoList(PackSysInfo packSysInfo) {
        return packSysInfoMapper.selectPackSysInfoList(packSysInfo);
    }

    /**
     * 新增包装校验信息
     *
     * @param packSysInfo 包装校验信息
     * @return 结果
     */
    @Override
    public int insertPackSysInfo(PackSysInfo packSysInfo) {
        return packSysInfoMapper.insertPackSysInfo(packSysInfo);
    }

    /**
     * 修改包装校验信息
     *
     * @param packSysInfo 包装校验信息
     * @return 结果
     */
    @Override
    public int updatePackSysInfo(PackSysInfo packSysInfo) {
        return packSysInfoMapper.updatePackSysInfo(packSysInfo);
    }

    /**
     * 批量删除包装校验信息
     *
     * @param processIds 需要删除的包装校验信息主键
     * @return 结果
     */
    @Override
    public int deletePackSysInfoByProcessIds(Long[] processIds) {
        return packSysInfoMapper.deletePackSysInfoByProcessIds(processIds);
    }

    /**
     * 删除包装校验信息信息
     *
     * @param processId 包装校验信息主键
     * @return 结果
     */
    @Override
    public int deletePackSysInfoByProcessId(Long processId) {
        return packSysInfoMapper.deletePackSysInfoByProcessId(processId);
    }

    @Override
    public int getMaxProcessId() {
        Integer maxId = packSysInfoMapper.getMaxProcessId();
        if (maxId == null) {
            maxId = 0;
        }
        return maxId;
    }
}
