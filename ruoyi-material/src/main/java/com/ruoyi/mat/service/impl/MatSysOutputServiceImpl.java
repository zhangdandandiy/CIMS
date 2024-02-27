package com.ruoyi.mat.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.mat.domain.MatSysDetail;
import com.ruoyi.mat.domain.MatSysOutput;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysOutputSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysOutputSearchListDto;
import com.ruoyi.mat.domain.dto.MatSysOutputTotalInfoDto;
import com.ruoyi.mat.mapper.MatSysDetailMapper;
import com.ruoyi.mat.mapper.MatSysOutputMapper;
import com.ruoyi.mat.service.IMatSysOutputService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/21 20:08
 **/

@Service
public class MatSysOutputServiceImpl implements IMatSysOutputService {

    @Autowired
    private MatSysOutputMapper matSysOutputMapper;

    @Autowired
    private MatSysDetailMapper matSysDetailMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询备品出库信息
     *
     * @param matSysOutput 备品出库信息主键
     * @return 备品出库信息
     */
    @Override
    public MatSysOutput selectMatSysOutputByMatOutputId(MatSysOutputSearchInfoDto matSysOutput) {
        return matSysOutputMapper.selectMatSysOutputByMatOutputId(matSysOutput);
    }

    /**
     * 查询备品出库信息列表
     *
     * @param matSysOutput 备品出库信息
     * @return 备品出库信息集合
     */
    @Override
    public List<MatSysOutput> selectMatSysOutputList(MatSysOutputSearchListDto matSysOutput) {
        return matSysOutputMapper.selectMatSysOutputList(matSysOutput);
    }

    /**
     * 查询备品总的出库信息列表
     *
     * @param matSysOutput 备品出库信息
     * @return 备品出库信息集合
     */
    @Override
    public List<MatSysOutputTotalInfoDto> selectMatSysOutputTotalList(MatSysOutputSearchListDto matSysOutput){
        return  matSysOutputMapper.selectMatSysOutputTotalList(matSysOutput);
    }

    /**
     * 新增备品出库信息
     *
     * @param matSysOutput 备品出库信息
     * @return 结果
     */
    @Override
    public int insertMatSysOutput(MatSysOutput matSysOutput) {
        // 获取出库数量
        Long matOutputNumber = matSysOutput.getMatOutputNumber();
        // 获取出库备品编号
        String matCode = matSysOutput.getMatCode();
        // 获取用户名
        String userName = matSysOutput.getMatOutputUser();
        // 根据用户名查询用户Id
        SysUser sysUser = sysUserMapper.selectUserByUserName(userName);
        // 获取用户Id
        Long userId = sysUser.getUserId();
        // 获取库存数量
        MatSysDetailSearchInfoDto matSysDetailInfo = new MatSysDetailSearchInfoDto();
        matSysDetailInfo.setUserId(userId);
        matSysDetailInfo.setMatCode(matCode);
        MatSysDetail matSysDetailOld = matSysDetailMapper.selectDetailByMatCode(matSysDetailInfo);
        Long stockNumber = matSysDetailOld.getMatNumber();
        // 更新库存数量
        MatSysDetail matSysDetail = new MatSysDetail();
        matSysDetail.setMatId(matSysDetailOld.getMatId());
        matSysDetail.setMatNumber(stockNumber - matOutputNumber);
        matSysDetailMapper.updateMatSysDetail(matSysDetail);
        return matSysOutputMapper.insertMatSysOutput(matSysOutput);
    }

    @Override
    public int deleteMatSysOutputByMatOutputIds(Long[] matOutputIds) {
        return 0;
    }
}
