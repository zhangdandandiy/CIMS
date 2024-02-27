package com.ruoyi.packing.service;

import com.ruoyi.packing.domain.PackSysModel;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/9/8 20:54
 **/
public interface IPackSysModelService {

    /**
     * 新增包装校验机种信息
     *
     * @param packSysModel 包装校验机种信息
     * @return 结果
     */
    public int insertPackSysModel(PackSysModel packSysModel);

    /**
     * 查询所有机种
     *
     * @return
     */
    public List<String> selectAllPackSysModel();

    /**
     * 查询包装校验机种信息
     *
     * @param modelId 包装校验机种信息主键
     * @return 包装校验机种信息
     */
    public PackSysModel selectPackSysModelByModelName(String modelName);

}
