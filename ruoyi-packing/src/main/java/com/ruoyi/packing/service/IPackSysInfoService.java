package com.ruoyi.packing.service;

import com.ruoyi.packing.domain.PackSysInfo;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/9/8 21:07
 **/
public interface IPackSysInfoService {

    /**
     * 查询包装校验信息
     *
     * @param processId 包装校验信息主键
     * @return 包装校验信息
     */
    public PackSysInfo selectPackSysInfoByProcessId(Long processId);

    /**
     * 查询包装校验信息列表
     *
     * @param packSysInfo 包装校验信息
     * @return 包装校验信息集合
     */
    public List<PackSysInfo> selectPackSysInfoList(PackSysInfo packSysInfo);

    /**
     * 新增包装校验信息
     *
     * @param packSysInfo 包装校验信息
     * @return 结果
     */
    public int insertPackSysInfo(PackSysInfo packSysInfo);

    /**
     * 修改包装校验信息
     *
     * @param packSysInfo 包装校验信息
     * @return 结果
     */
    public int updatePackSysInfo(PackSysInfo packSysInfo);

    /**
     * 批量删除包装校验信息
     *
     * @param processIds 需要删除的包装校验信息主键集合
     * @return 结果
     */
    public int deletePackSysInfoByProcessIds(Long[] processIds);

    /**
     * 删除包装校验信息信息
     *
     * @param processId 包装校验信息主键
     * @return 结果
     */
    public int deletePackSysInfoByProcessId(Long processId);

    /**
     * 获取最大主键Id
     *
     * @return
     */
    public int getMaxProcessId();

}
