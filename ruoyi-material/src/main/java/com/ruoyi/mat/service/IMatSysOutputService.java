package com.ruoyi.mat.service;

import com.ruoyi.mat.domain.MatSysOutput;
import com.ruoyi.mat.domain.dto.MatSysOutputSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysOutputSearchListDto;
import com.ruoyi.mat.domain.dto.MatSysOutputTotalInfoDto;

import java.util.List;

/**
 * 备品出库信息 Service接口
 *
 * @author Dandan
 * @date 2023/8/21 20:06
 **/
public interface IMatSysOutputService {

    /**
     * 查询备品出库信息
     *
     * @param matSysOutput 备品出库信息主键
     * @return 备品出库信息
     */
    public MatSysOutput selectMatSysOutputByMatOutputId(MatSysOutputSearchInfoDto matSysOutput);

    /**
     * 查询备品出库信息列表
     *
     * @param matSysOutput 备品出库信息
     * @return 备品出库信息集合
     */
    public List<MatSysOutput> selectMatSysOutputList(MatSysOutputSearchListDto matSysOutput);

    /**
     * 查询备品总的出库信息列表
     *
     * @param matSysOutput 备品出库信息
     * @return 备品出库信息集合
     */
    public List<MatSysOutputTotalInfoDto> selectMatSysOutputTotalList(MatSysOutputSearchListDto matSysOutput);

    /**
     * 新增备品出库信息
     *
     * @param matSysOutput 备品出库信息
     * @return 结果
     */
    public int insertMatSysOutput(MatSysOutput matSysOutput);

    /**
     * 批量删除备品出库信息
     *
     * @param matOutputIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMatSysOutputByMatOutputIds(Long[] matOutputIds);

}
