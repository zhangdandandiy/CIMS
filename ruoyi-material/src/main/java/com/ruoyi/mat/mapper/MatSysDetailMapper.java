package com.ruoyi.mat.mapper;

import com.ruoyi.mat.domain.MatSysDetail;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchInfoDto;
import com.ruoyi.mat.domain.dto.MatSysDetailSearchListDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 备品详细信息 数据层
 *
 * @author Dandan
 * @date 2023/8/21 8:57
 **/
@Mapper
public interface MatSysDetailMapper {

    /**
     * 查询备品详细信息列表
     *
     * @param matSysDetail 备品详细信息
     * @return 备品详细信息集合
     */
    public List<MatSysDetail> selectMatSysDetailList(MatSysDetailSearchListDto matSysDetail);

    /**
     * 查询所有被占用储位
     *
     * @return
     */
    public List<String> selectAllOccupyShelfCode(Long userId);

    /**
     * 校验料号唯一性
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    public MatSysDetail checkMatCodeUnique(MatSysDetailSearchListDto matSysDetail);

    /**
     * 查询备品详细信息
     *
     * @param matId 备品详细信息主键
     * @return 备品详细信息
     */
    public MatSysDetail selectMatSysDetailByMatId(MatSysDetailSearchInfoDto matSysDetail);

    /**
     * 新增备品详细信息
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    public int insertMatSysDetail(MatSysDetail matSysDetail);

    /**
     * 修改备品详细信息
     *
     * @param matSysDetail 备品详细信息
     * @return 结果
     */
    public int updateMatSysDetail(MatSysDetail matSysDetail);

    /**
     * 删除备品详细信息
     *
     * @param matId 备品详细信息主键
     * @return 结果
     */
    public int deleteMatSysDetailByMatId(Long matId);

    /**
     * 批量删除备品详细信息
     *
     * @param matIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMatSysDetailByMatIds(Long[] matIds);

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

    /**
     * 根据料号和用户名查询备品信息
     *
     * @param matCode
     * @param userName
     * @return
     */
    public MatSysDetail selectMatSysDetail(@Param("matCode") String matCode, @Param("userName") String userName);

}
