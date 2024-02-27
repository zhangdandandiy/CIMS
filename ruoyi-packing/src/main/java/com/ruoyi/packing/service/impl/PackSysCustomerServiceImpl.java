package com.ruoyi.packing.service.impl;

import com.ruoyi.packing.domain.PackSysCustomer;
import com.ruoyi.packing.mapper.PackSysCustomerMapper;
import com.ruoyi.packing.service.IPackSysCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 包装校验客户信息Service业务层处理
 *
 * @author Dandan
 * @date 2023/9/8 19:40
 **/
@Service
public class PackSysCustomerServiceImpl implements IPackSysCustomerService {

    @Autowired
    private PackSysCustomerMapper packSysCustomerMapper;

    /**
     * 新增包装校验客户信息
     *
     * @param packSysCustomer 包装校验客户信息
     * @return 结果
     */
    @Override
    public int insertPackSysCustomer(PackSysCustomer packSysCustomer) {
        return packSysCustomerMapper.insertPackSysCustomer(packSysCustomer);
    }

    /**
     * 查询所有客户信息
     *
     * @return
     */
    @Override
    public List<String> selectAllPackSysCustomer(){
        return packSysCustomerMapper.selectAllPackSysCustomer();
    }

}
