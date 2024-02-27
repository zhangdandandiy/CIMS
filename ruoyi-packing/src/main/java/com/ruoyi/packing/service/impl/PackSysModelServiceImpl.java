package com.ruoyi.packing.service.impl;

import com.ruoyi.packing.domain.PackSysModel;
import com.ruoyi.packing.mapper.PackSysModelMapper;
import com.ruoyi.packing.service.IPackSysModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/9/8 20:55
 **/
@Service
public class PackSysModelServiceImpl implements IPackSysModelService {
    @Autowired
    private PackSysModelMapper packSysModelMapper;

    /**
     * 新增包装校验机种信息
     *
     * @param packSysModel 包装校验机种信息
     * @return 结果
     */
    @Override
    public int insertPackSysModel(PackSysModel packSysModel) {
        return packSysModelMapper.insertPackSysModel(packSysModel);
    }

    /**
     * 查询所有机种
     *
     * @return
     */
    @Override
    public List<String> selectAllPackSysModel() {
        return packSysModelMapper.selectAllPackSysModel();
    }

    /**
     * 查询包装校验机种信息
     *
     * @param modelId 包装校验机种信息主键
     * @return 包装校验机种信息
     */
    @Override
    public PackSysModel selectPackSysModelByModelName(String modelName) {
        return packSysModelMapper.selectPackSysModelByModelName(modelName);
    }

}
