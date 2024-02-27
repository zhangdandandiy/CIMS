package com.ruoyi.packing.service;

import com.ruoyi.packing.domain.PackSysIdentify;

import java.util.List;

/**
 * 包装校验认证Service接口
 *
 * @author Dandan
 * @date 2023/9/9 21:21
 **/
public interface IPackSysIdentifyService {

    /**
     * 查询包装校验认证
     *
     * @param identifyId 包装校验认证主键
     * @return 包装校验认证
     */
    public PackSysIdentify selectPackSysIdentifyByIdentifyId(Long identifyId);

    /**
     * 查询包装校验认证列表
     *
     * @param packSysIdentify 包装校验认证
     * @return 包装校验认证集合
     */
    public List<PackSysIdentify> selectPackSysIdentifyList(PackSysIdentify packSysIdentify);

    /**
     * 新增包装校验认证
     *
     * @param packSysIdentify 包装校验认证
     * @return 结果
     */
    public int insertPackSysIdentify(PackSysIdentify packSysIdentify);

    /**
     * 修改包装校验认证
     *
     * @param packSysIdentify 包装校验认证
     * @return 结果
     */
    public int updatePackSysIdentify(PackSysIdentify packSysIdentify);

    /**
     * 批量删除包装校验认证
     *
     * @param identifyIds 需要删除的包装校验认证主键集合
     * @return 结果
     */
    public int deletePackSysIdentifyByIdentifyIds(Long[] identifyIds);

    /**
     * 删除包装校验认证信息
     *
     * @param identifyId 包装校验认证主键
     * @return 结果
     */
    public int deletePackSysIdentifyByIdentifyId(Long identifyId);

}
