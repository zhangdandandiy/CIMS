package com.ruoyi.mat.service;

import com.ruoyi.mat.domain.MatSysDetail;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchListDto;

import java.util.List;

/**
 * 备品详细信息 Service 接口
 *
 * @author Dandan
 * @date 2023/8/21 9:03
 **/
public interface IMatSysDetailService {

    /**
     * 查询备品详细信息
     *
     * @param matId 备品详细信息主键
     * @return 备品详细信息
     */
    public MatSysDetail selectMatSysDetailByMatId(MatSysDetailSearchInfoDto matSysDetail);

    /**
     * 查询备品详细信息列表
     *
     * @param matSysDetail 备品详细信息
     * @return 备品详细信息集合
     */
    public List<MatSysDetail> selectMatSysDetailList(MatSysDetailSearchListDto matSysDetail);

    /**
     * 新增备品详细信息
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    public int insertMatSysDetail(MatSysDetail matSysDetail, Long userId);

    /**
     * 修改备品详细信息
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    public int updateMatSysDetail(MatSysDetail matSysDetail);

    /**
     * 批量删除备品详细信息
     *
     * @param matIds 需要删除的备品详细信息主键集合
     * @return 结果
     */
    public int deleteMatSysDetailByMatIds(Long[] matIds);

    /**
     * 删除备品详细信息信息
     *
     * @param matId 备品详细信息主键
     * @return 结果
     */
    public int deleteMatSysDetailByMatId(Long matId);

    /**
     * 导入备品数据
     *
     * @param matSysDetailList 备品数据列表
     * @param isUpdateSupport  是否更新支持，如果已存在，则进行更新数据
     * @param operName         操作备品
     * @return 结果
     */
    public String importDetail(List<MatSysDetail> matSysDetailList, Boolean isUpdateSupport, String operName, Long userId);

    /**
     * 通过备品编号查询备品
     *
     * @param matCode 备品编号
     * @return 备品对象信息
     */
    public MatSysDetail selectDetailByMatCode(MatSysDetailSearchInfoDto matSysDetail);

    /**
     * 查询备品详细信息列表
     *
     * @param matSysDetail 备品详细信息
     * @return 备品详细信息集合
     */
    public List<MatSysDetail> selectAllMatSysDetailList(MatSysDetail matSysDetail);

    /**
     * 校验料号唯一性
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    public boolean checkMatCodeUnique(MatSysDetailSearchListDto matSysDetail);

    /**
     * 查询所有应用专案
     *
     * @return
     */
    public List<String> selectAllMatProject();

    /**
     * 查询对应楼层下的所有应用专案
     *
     * @param userId
     * @return
     */
    public List<String> selectAllMatProjectByUserId(Long userId);

}
