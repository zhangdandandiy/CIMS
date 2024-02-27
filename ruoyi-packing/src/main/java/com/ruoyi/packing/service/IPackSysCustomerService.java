package com.ruoyi.packing.service;

import com.ruoyi.packing.domain.PackSysCustomer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包装校验客户信息Service接口
 * @author Dandan
 * @date 2023/9/8 19:40
 **/
@Service
public interface IPackSysCustomerService {

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
