package com.ruoyi.mat.service;

import com.ruoyi.mat.domain.MatSysInput;
import com.ruoyi.mat.domain.dto.MatSysInputSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysInputSearchListDto;
import com.ruoyi.mat.domain.dto.MatSysInputTotalInfoDto;

import java.util.List;

/**
 * 备品入库信息 Service接口
 *
 * @author Dandan
 * @date 2023/8/21 15:29
 **/
public interface IMatSysInputService {

    /**
     * 查询备品入库信息
     *
     * @param matInputId 备品入库信息主键
     * @return 备品入库信息
     */
    public MatSysInput selectMatSysInputByMatInputId(MatSysInputSearchInfoDto matSysInput);

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
     * 新增备品入库信息
     *
     * @param matSysInput 备品入库信息
     * @return 结果
     */
    public int insertMatSysInput(MatSysInput matSysInput);

    /**
     * 批量删除备品入库信息
     *
     * @param matInputIds 需要删除的备品入库信息主键集合
     * @return 结果
     */
    public int deleteMatSysInputByMatInputIds(Long[] matInputIds);

    /**
     * 删除备品入库信息信息
     *
     * @param matInputId 备品入库信息主键
     * @return 结果
     */
    public int deleteMatSysInputByMatInputId(Long matInputId);

}
