package com.ruoyi.mat.service;

import com.ruoyi.mat.domain.MatSysCancel;
import com.ruoyi.mat.domain.dto.MatSysCancelSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysCancelSearchListDto;
import com.ruoyi.mat.domain.dto.MatSysCancelTotalInfoDto;

import java.util.List;

/**
 * 备品退库信息 Service接口
 *
 * @author Dandan
 * @date 2023/8/22 8:39
 **/
public interface IMatSysCancelService {

    /**
     * 查询备品退库信息列表
     *
     * @param matSysCancel 备品退库信息
     * @return 备品退库信息集合
     */
    public List<MatSysCancel> selectMatSysCancelList(MatSysCancelSearchListDto matSysCancel);

    /**
     * 查询备品总的退库信息列表
     *
     * @param matSysCancel 备品退库信息
     * @return 备品退库信息集合
     */
    public List<MatSysCancelTotalInfoDto> selectMatSysCancelTotalList(MatSysCancelTotalInfoDto matSysCancel);

    /**
     * 查询备品退库信息
     *
     * @param matSysCancel 备品退库信息
     * @return 备品退库信息
     */
    public MatSysCancel selectMatSysCancelByMatCancelId(MatSysCancelSearchInfoDto matSysCancel);

    /**
     * 新增备品退库信息
     *
     * @param matSysCancel 备品退库信息
     * @return 结果
     */
    public int insertMatSysCancel(MatSysCancel matSysCancel);

    /**
     * 根据Id删除备品退库信息
     *
     * @param matCancelId 备品退库信息主键
     * @return 结果
     */
    public int deleteMatSysCancelByMatCancelId(Long matCancelId);

    /**
     * 根据Id批量删除备品退库信息
     *
     * @param matCancelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMatSysCancelByMatCancelIds(Long[] matCancelIds);

}
