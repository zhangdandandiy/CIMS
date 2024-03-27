package com.ruoyi.mat.mapper;

import com.ruoyi.mat.domain.MatSysInput;
import com.ruoyi.mat.domain.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 备品入库信息 Mapper接口
 *
 * @author Dandan
 * @date 2023/8/21 11:27
 **/

@Mapper
public interface MatSysInputMapper {

    /**
     * 查询备品入库信息列表
     *
     * @param matSysInput 备品入库信息
     * @return 备品入库信息集合
     */
    public List<MatSysInput> selectMatSysInputList(MatSysInputSearchListDto matSysInput);

    /**
     * 查询备品总的入库信息列表
     *
     * @param matSysInput 备品入库信息
     * @return 备品入库信息集合
     */
    public List<MatSysInputTotalInfoDto> selectMatSysInputTotalList(MatSysInputSearchListDto matSysInput);

    /**
     * 查询备品入库信息
     *
     * @param matInputId 备品入库信息主键
     * @return 备品入库信息
     */
    public MatSysInput selectMatSysInputByMatInputId(MatSysInputSearchInfoDto matSysInput);

    /**
     * 新增备品入库信息
     *
     * @param matSysInput 备品入库信息
     * @return 结果
     */
    public int insertMatSysInput(MatSysInput matSysInput);

    /**
     * 修改备品入库信息
     *
     * @param matSysInput 备品入库信息
     * @return 结果
     */
    public int updateMatSysInput(MatSysInputEditDto matSysInput);

    /**
     * 根据Id删除备品入库信息
     *
     * @param matInputId 备品入库信息主键
     * @return 结果
     */
    public int deleteMatSysInputByMatInputId(Long matInputId);

    /**
     * 根据Id批量删除备品入库信息
     *
     * @param matInputIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMatSysInputByMatInputIds(Long[] matInputIds);

}
