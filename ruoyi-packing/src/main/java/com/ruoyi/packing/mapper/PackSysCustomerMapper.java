package com.ruoyi.packing.mapper;

import com.ruoyi.packing.domain.PackSysCustomer;

import java.util.List;

/**
 * 包装校验客户信息Mapper接口
 *
 * @author Dandan
 * @date 2023/9/8 19:37
 **/
public interface PackSysCustomerMapper {

    /**
     * 新增包装校验客户信息
     *
     * @param packSysCustomer 包装校验客户信息
     * @return 结果
     */
    public int insertPackSysCustomer(PackSysCustomer packSysCustomer);

    /**
     * 查询所有客户信息
     *
     * @return
     */
    public List<String> selectAllPackSysCustomer();

}
