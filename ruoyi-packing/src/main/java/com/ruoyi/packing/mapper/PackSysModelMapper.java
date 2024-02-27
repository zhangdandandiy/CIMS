package com.ruoyi.packing.mapper;

import com.ruoyi.packing.domain.PackSysModel;

import java.util.List;

/**
 * 包装校验机种信息 Mapper接口
 *
 * @author Dandan
 * @date 2023/9/8 19:55
 **/
public interface PackSysModelMapper {

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
