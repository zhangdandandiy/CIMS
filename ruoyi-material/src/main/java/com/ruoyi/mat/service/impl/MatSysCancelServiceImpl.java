package com.ruoyi.mat.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.mat.domain.MatSysCancel;
import com.ruoyi.mat.domain.MatSysDetail;
import com.ruoyi.mat.domain.dto.MatSysCancelSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysCancelSearchListDto;
import com.ruoyi.mat.domain.dto.MatSysCancelTotalInfoDto;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchInfoDto;
import com.ruoyi.mat.mapper.MatSysCancelMapper;
import com.ruoyi.mat.mapper.MatSysDetailMapper;
import com.ruoyi.mat.service.IMatSysCancelService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 备品退库信息 Service业务层处理
 *
 * @author Dandan
 * @date 2023/8/22 8:41
 **/
@Service
public class MatSysCancelServiceImpl implements IMatSysCancelService {

    @Autowired
    private MatSysCancelMapper matSysCancelMapper;

    @Autowired
    private MatSysDetailMapper matSysDetailMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询备品退库信息列表
     *
     * @param matSysCancel 备品退库信息
     * @return 备品退库信息集合
     */
    @Override
    public List<MatSysCancel> selectMatSysCancelList(MatSysCancelSearchListDto matSysCancel) {
        return matSysCancelMapper.selectMatSysCancelList(matSysCancel);
    }

    /**
     * 查询备品总的退库信息列表
     *
     * @param matSysCancel 备品退库信息
     * @return 备品退库信息集合
     */
    @Override
    public List<MatSysCancelTotalInfoDto> selectMatSysCancelTotalList(MatSysCancelTotalInfoDto matSysCancel){
        return matSysCancelMapper.selectMatSysCancelTotalList(matSysCancel);
    }

    /**
     * 查询备品退库信息
     *
     * @param matSysCancel 备品退库信息
     * @return 备品退库信息
     */
    @Override
    public MatSysCancel selectMatSysCancelByMatCancelId(MatSysCancelSearchInfoDto matSysCancel) {
        return matSysCancelMapper.selectMatSysCancelByMatCancelId(matSysCancel);
    }

    /**
     * 新增备品退库信息
     *
     * @param matSysCancel 备品退库信息
     * @return 结果
     */
    @Override
    public int insertMatSysCancel(MatSysCancel matSysCancel) {
        // 获取退库数量
        Long matCancelNumber = matSysCancel.getMatCancelNumber();
        // 获取退库备品编号
        String matCode = matSysCancel.getMatCode();
        // 获取用户名
        String userName = matSysCancel.getMatCancelUser();
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
        matSysDetail.setMatNumber(stockNumber - matCancelNumber);
        matSysDetailMapper.updateMatSysDetail(matSysDetail);
        return matSysCancelMapper.insertMatSysCancel(matSysCancel);
    }

    /**
     * 根据Id删除备品退库信息
     *
     * @param matCancelId 备品退库信息主键
     * @return 结果
     */
    @Override
    public int deleteMatSysCancelByMatCancelId(Long matCancelId) {
        return matSysCancelMapper.deleteMatSysCancelByMatCancelId(matCancelId);
    }

    @Override
    public int deleteMatSysCancelByMatCancelIds(Long[] matCancelIds) {
        return matSysCancelMapper.deleteMatSysCancelByMatCancelIds(matCancelIds);
    }
}
