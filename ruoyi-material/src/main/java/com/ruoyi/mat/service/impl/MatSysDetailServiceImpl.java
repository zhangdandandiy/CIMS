package com.ruoyi.mat.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanValidators;
import com.ruoyi.mat.domain.MatSysDetail;
import com.ruoyi.mat.domain.MatSysFloor;
import com.ruoyi.mat.domain.MatSysFloorShelf;
import com.ruoyi.mat.domain.MatSysShelf;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchListDto;
import com.ruoyi.mat.mapper.*;
import com.ruoyi.mat.service.IMatSysDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

/**
 * 备品详细信息 Service业务层处理
 *
 * @author Dandan
 * @date 2023/8/21 9:05
 **/
@Service
public class MatSysDetailServiceImpl implements IMatSysDetailService {
    private static final Logger log = LoggerFactory.getLogger(MatSysDetailServiceImpl.class);

    @Autowired
    private MatSysDetailMapper matSysDetailMapper;

    @Autowired
    private MatSysUserFloorMapper matSysUserFloorMapper;

    @Autowired
    private MatSysFloorMapper matSysFloorMapper;

    @Autowired
    private MatSysShelfMapper matSysShelfMapper;

    @Autowired
    private MatSysFloorShelfMapper matSysFloorShelfMapper;

    @Autowired
    protected Validator validator;

    /**
     * 查询备品详细信息
     *
     * @param matId 备品详细信息主键
     * @return 备品详细信息
     */
    @Override
    public MatSysDetail selectMatSysDetailByMatId(MatSysDetailSearchInfoDto matSysDetail) {
        return matSysDetailMapper.selectMatSysDetailByMatId(matSysDetail);
    }

    /**
     * 查询备品详细信息列表
     *
     * @param matSysDetail 备品详细信息
     * @return 备品详细信息
     */
    @Override
    public List<MatSysDetail> selectMatSysDetailList(MatSysDetailSearchListDto matSysDetail) {
        return matSysDetailMapper.selectMatSysDetailList(matSysDetail);
    }

    /**
     * 新增备品详细信息
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    @Override
    public int insertMatSysDetail(MatSysDetail matSysDetail, Long userId) {
        // 根据用户Id查找用户所对应的楼层编号
        MatSysFloor matSysFloor = matSysUserFloorMapper.searchMatSysUserFloorInfoByUserId(userId);
        matSysDetail.setMatFloorCode(matSysFloor.getFloorCode());
        return matSysDetailMapper.insertMatSysDetail(matSysDetail);
    }

    /**
     * 修改备品详细信息
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    @Override
    public int updateMatSysDetail(MatSysDetail matSysDetail) {
        return matSysDetailMapper.updateMatSysDetail(matSysDetail);
    }

    /**
     * 批量删除备品详细信息
     *
     * @param matIds 需要删除的备品详细信息主键
     * @return 结果
     */
    @Override
    public int deleteMatSysDetailByMatIds(Long[] matIds) {
        return matSysDetailMapper.deleteMatSysDetailByMatIds(matIds);
    }

    /**
     * 删除备品详细信息信息
     *
     * @param matId 备品详细信息主键
     * @return 结果
     */
    @Override
    public int deleteMatSysDetailByMatId(Long matId) {
        return matSysDetailMapper.deleteMatSysDetailByMatId(matId);
    }

    /**
     * 导入备品数据
     *
     * @param matSysDetailList 备品数据列表
     * @param isUpdateSupport  是否更新支持，如果已存在，则进行更新数据
     * @param operName         操作备品
     * @return 结果
     */
    @Override
    public String importDetail(List<MatSysDetail> matSysDetailList, Boolean isUpdateSupport, String operName, Long userId) {
        if (StringUtils.isNull(matSysDetailList) || matSysDetailList.size() == 0) {
            throw new ServiceException("导入备品数据不能为空");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        // 根据用户名查询用户楼层
        String userFloorCode = matSysFloorMapper.selectFloorCodeByUserName(operName);
        // 根据用户名查询储位列表
        List<String> shelfCodeList = matSysShelfMapper.selectShelfCodeByUserName(operName);
        // 查询全部占用储位
        List<String> occupyShelfCodeList = matSysDetailMapper.selectAllOccupyShelfCode(userId);
        for (MatSysDetail detail : matSysDetailList) {
            try {
                // 验证是否存在这个备品的料号
                MatSysDetailSearchInfoDto matSysDetail = new MatSysDetailSearchInfoDto();
                matSysDetail.setMatCode(detail.getMatCode());
                matSysDetail.setUserId(userId);
                MatSysDetail materialInfo = matSysDetailMapper.selectDetailByMatCode(matSysDetail);
                if (StringUtils.isNull(materialInfo) && userFloorCode.equals(detail.getMatFloorCode()) && shelfCodeList.contains(detail.getMatShelfCode()) && !occupyShelfCodeList.contains(detail.getMatShelfCode())) {
                    BeanValidators.validateWithException(validator, detail);
                    detail.setCreateBy(operName);
                    matSysDetailMapper.insertMatSysDetail(detail);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、料号" + detail.getMatCode() + " 导入成功");
                } else if (occupyShelfCodeList.contains(detail.getMatShelfCode())) {
                    failureNum++;
                    failureMsg.append("<br/>" + detail.getMatShelfCode() + " 储位已被占用，请选择其他储位或者新增储位");
                } else if (!userFloorCode.equals(detail.getMatFloorCode())) {
                    failureNum++;
                    failureMsg.append("<br/>" + operName + " 不属于" + detail.getMatFloorCode() + " 楼层");
                } else if (!shelfCodeList.contains(detail.getMatShelfCode())) {
                    successNum++;
                    // 获取最大主键
                    Long shelfId = Long.valueOf(matSysShelfMapper.getMatSysShelfMaxShelfId() + 1);
                    MatSysShelf matSysShelf = new MatSysShelf();
                    matSysShelf.setShelfId(shelfId);
                    matSysShelf.setShelfCode(detail.getMatShelfCode());
                    matSysShelf.setCreateBy(operName);
                    // 通过用户Id查询楼层Id
                    Long floorId = matSysUserFloorMapper.searchMatSysUserFloorByUserId(userId);
                    // 插入关联信息
                    MatSysFloorShelf matSysFloorShelf = new MatSysFloorShelf();
                    matSysFloorShelf.setFloorId(floorId);
                    matSysFloorShelf.setShelfId(shelfId);
                    matSysFloorShelfMapper.insertMatSysFloorShelf(matSysFloorShelf);
                    // 新增备品储位
                    matSysShelfMapper.insertMatSysShelf(matSysShelf);
                    // 新增料号
                    detail.setCreateBy(operName);
                    matSysDetailMapper.insertMatSysDetail(detail);
                    successMsg.append("<br/>" + detail.getMatShelfCode() + " 储位不存在，已新增");
//                    failureNum++;
//                    failureMsg.append("<br/>" + detail.getMatShelfCode() + " 储位不存在");
                } else if (isUpdateSupport) {
                    BeanValidators.validateWithException(validator, detail);
                    detail.setMatCode(materialInfo.getMatCode());
                    matSysDetailMapper.updateMatSysDetail(detail);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、料号" + detail.getMatCode() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、料号" + detail.getMatCode() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、料号" + detail.getMatCode() + " 导入失败";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, 0);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共" + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    /**
     * 通过备品编号查询备品
     *
     * @param matCode 备品编号
     * @return 备品对象信息
     */
    @Override
    public MatSysDetail selectDetailByMatCode(MatSysDetailSearchInfoDto matSysDetail) {
        return matSysDetailMapper.selectDetailByMatCode(matSysDetail);
    }

    /**
     * 查询备品详细信息列表
     *
     * @param matSysDetail 备品详细信息
     * @return 备品详细信息集合
     */
    @Override
    public List<MatSysDetail> selectAllMatSysDetailList(MatSysDetail matSysDetail) {
        return matSysDetailMapper.selectAllMatSysDetailList(matSysDetail);
    }

    /**
     * 校验料号唯一性
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    @Override
    public boolean checkMatCodeUnique(MatSysDetailSearchListDto matSysDetail) {
        MatSysDetail sysDetail = matSysDetailMapper.checkMatCodeUnique(matSysDetail);
        if (StringUtils.isNotNull(sysDetail)) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 查询所有应用专案
     *
     * @return
     */
    @Override
    public List<String> selectAllMatProject(){
        return matSysDetailMapper.selectAllMatProject();
    }

    /**
     * 查询对应楼层下的所有应用专案
     *
     * @param userId
     * @return
     */
    @Override
    public List<String> selectAllMatProjectByUserId(Long userId){
        return matSysDetailMapper.selectAllMatProjectByUserId(userId);
    }

}
