package com.ruoyi.mat.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.mat.domain.MatSysFloorShelf;
import com.ruoyi.mat.domain.MatSysShelf;
import com.ruoyi.mat.domain.dto.MatSysShelfSearchInfoDto;
import com.ruoyi.mat.mapper.MatSysDetailMapper;
import com.ruoyi.mat.mapper.MatSysFloorShelfMapper;
import com.ruoyi.mat.mapper.MatSysShelfMapper;
import com.ruoyi.mat.mapper.MatSysUserFloorMapper;
import com.ruoyi.mat.service.IMatSysShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Dandan
 * @date 2023/8/22 13:15
 **/
@Service
public class MatSysShelfServiceImpl implements IMatSysShelfService {

    @Autowired
    private MatSysShelfMapper matSysShelfMapper;

    @Autowired
    private MatSysUserFloorMapper matSysUserFloorMapper;

    @Autowired
    private MatSysFloorShelfMapper matSysFloorShelfMapper;

    @Autowired
    private MatSysDetailMapper matSysDetailMapper;

    /**
     * 查询备品储位列表
     *
     * @param matSysShelf 备品储位
     * @return 备品储位集合
     */
    @Override
    public List<MatSysShelf> selectMatSysShelfList(MatSysShelfSearchInfoDto matSysShelf) {
        return matSysShelfMapper.selectMatSysShelfList(matSysShelf);
    }

    /**
     * 查询所有未被占用储位编号
     *
     * @param matSysShelf
     * @return
     */
    @Override
    public List<MatSysShelf> selectAllNoOccupyShelfCode(Long userId) {
        MatSysShelfSearchInfoDto searchInfo = new MatSysShelfSearchInfoDto();
        searchInfo.setUserId(userId);
        List<MatSysShelf> allShelfCode = matSysShelfMapper.selectMatSysShelfList(searchInfo);
        List<String> allOccupyShelfCode = matSysDetailMapper.selectAllOccupyShelfCode(userId);
        allShelfCode.removeIf(shelfCode -> allOccupyShelfCode.contains(shelfCode.getShelfCode()));
        /**
         *  List<String> allNoOccupyShelfCode = allShelfCode.stream()
         *                 .filter(shelfCode -> !allOccupyShelfCode.contains(shelfCode))
         *                 .collect(Collectors.toList());
         */
        return allShelfCode;
    }

    /**
     * 查询备品储位
     *
     * @param matSysShelf 备品储位信息
     * @return 备品储位
     */
    @Override
    public MatSysShelf selectMatSysShelfByShelfId(MatSysShelfSearchInfoDto matSysShelf) {
        return matSysShelfMapper.selectMatSysShelfByShelfId(matSysShelf);
    }

    /**
     * 新增备品储位及关联信息
     *
     * @param matSysShelf 备品储位
     * @return 结果
     */
    @Transactional
    @Override
    public int insertMatSysShelf(MatSysShelf matSysShelf, Long userId) {
        // 通过用户Id查询楼层Id
        Long floorId = matSysUserFloorMapper.searchMatSysUserFloorByUserId(userId);
        // 获取储位Id
        Long shelfId = matSysShelf.getShelfId();
        // 插入关联信息
        MatSysFloorShelf matSysFloorShelf = new MatSysFloorShelf();
        matSysFloorShelf.setFloorId(floorId);
        matSysFloorShelf.setShelfId(shelfId);
        matSysFloorShelfMapper.insertMatSysFloorShelf(matSysFloorShelf);
        // 新增楼层
        return matSysShelfMapper.insertMatSysShelf(matSysShelf);
    }

    /**
     * 修改备品储位
     *
     * @param matSysShelf 备品储位
     * @return 结果
     */
    @Override
    public int updateMatSysShelf(MatSysShelf matSysShelf) {
        return matSysShelfMapper.updateMatSysShelf(matSysShelf);
    }

    /**
     * 批量删除备品储位及关联信息
     *
     * @param shelfIds 需要删除的数据主键集合
     * @return 结果
     */
    @Override
    public int deleteMatSysShelfByShelfIds(Long[] shelfIds) {
        // 删除关联信息
        matSysFloorShelfMapper.deleteMatSysFloorShelfByShelfIds(shelfIds);
        // 删除储位信息
        return matSysShelfMapper.deleteMatSysShelfByShelfIds(shelfIds);
    }

    /**
     * 获取 mat_sys_shelf表中最大的主键ID
     *
     * @return
     */
    @Override
    public int getMatSysShelfMaxShelfId() {
        Integer maxId = matSysShelfMapper.getMatSysShelfMaxShelfId();
        if (maxId == null) {
            maxId = 0;
        }
        return maxId;
    }

    /**
     * 校验储位编号唯一性
     *
     * @param matSysShelf 备品储位信息
     * @return 结果
     */
    @Override
    public boolean checkMatSysShelfUnique(MatSysShelfSearchInfoDto matSysShelf) {
        MatSysShelf sysShelf = matSysShelfMapper.checkMatSysShelfUnique(matSysShelf);
        if (StringUtils.isNotNull(sysShelf)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

}
