package com.ruoyi.mat.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.mat.domain.MatSysDetail;
import com.ruoyi.mat.domain.MatSysInput;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysInputSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysInputSearchListDto;
import com.ruoyi.mat.domain.dto.MatSysInputTotalInfoDto;
import com.ruoyi.mat.mapper.MatSysDetailMapper;
import com.ruoyi.mat.mapper.MatSysInputMapper;
import com.ruoyi.mat.service.IMatSysInputService;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/21 15:30
 **/

@Service
public class MatSysInputServiceImpl implements IMatSysInputService {

    @Autowired
    private MatSysInputMapper matSysInputMapper;

    @Autowired
    private MatSysDetailMapper matSysDetailMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 查询备品入库信息
     *
     * @param matInputId 备品入库信息主键
     * @return 备品入库信息
     */
    @Override
    public MatSysInput selectMatSysInputByMatInputId(MatSysInputSearchInfoDto matSysInput) {
        return matSysInputMapper.selectMatSysInputByMatInputId(matSysInput);
    }

    /**
     * 查询备品入库信息列表
     *
     * @param matSysInput 备品入库信息
     * @return 备品入库信息
     */
    @Override
    public List<MatSysInput> selectMatSysInputList(MatSysInputSearchListDto matSysInput) {
        return matSysInputMapper.selectMatSysInputList(matSysInput);
    }

    /**
     * 查询备品总的入库信息列表
     *
     * @param matSysInput 备品入库信息
     * @return 备品入库信息集合
     */
    @Override
    public List<MatSysInputTotalInfoDto> selectMatSysInputTotalList(MatSysInputSearchListDto matSysInput){
        return matSysInputMapper.selectMatSysInputTotalList(matSysInput);
    }

    /**
     * 新增备品入库信息
     *
     * @param matSysInput 备品入库信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMatSysInput(MatSysInput matSysInput) {
        // 获取入库数量
        Long matInputNumber = matSysInput.getMatInputNumber();
        // 获取入库备品编号
        String matCode = matSysInput.getMatCode();
        // 获取用户名
        String userName = matSysInput.getMatInputUser();
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
        matSysDetail.setMatNumber(stockNumber + matInputNumber);
        matSysDetailMapper.updateMatSysDetail(matSysDetail);
        return matSysInputMapper.insertMatSysInput(matSysInput);
    }

    /**
     * 批量删除备品入库信息
     *
     * @param matInputIds 需要删除的备品入库信息主键
     * @return 结果
     */
    @Override
    public int deleteMatSysInputByMatInputIds(Long[] matInputIds) {
        return matSysInputMapper.deleteMatSysInputByMatInputIds(matInputIds);
    }

    /**
     * 删除备品入库信息信息
     *
     * @param matInputId 备品入库信息主键
     * @return 结果
     */
    @Override
    public int deleteMatSysInputByMatInputId(Long matInputId) {
        return matSysInputMapper.deleteMatSysInputByMatInputId(matInputId);
    }

}
