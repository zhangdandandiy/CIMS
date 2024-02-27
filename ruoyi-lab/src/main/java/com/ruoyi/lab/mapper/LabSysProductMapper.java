package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.LabSysProduct;

import java.util.List;

/**
 * 专案 Mapper接口
 *
 * @author Dandan
 * @date 2023-08-04
 */
public interface LabSysProductMapper {
    /**
     * 查询专案
     *
     * @param id 专案主键
     * @return 专案
     */
    public LabSysProduct selectLabSysProductById(Long id);

    /**
     * 查询专案列表
     *
     * @param labSysProduct 专案
     * @return 专案集合
     */
    public List<LabSysProduct> selectLabSysProductList(LabSysProduct labSysProduct);

    /**
     * 新增专案
     *
     * @param labSysProduct 专案
     * @return 结果
     */
    public int insertLabSysProduct(LabSysProduct labSysProduct);

    /**
     * 修改专案
     *
     * @param labSysProduct 专案
     * @return 结果
     */
    public int updateLabSysProduct(LabSysProduct labSysProduct);

    /**
     * 删除专案
     *
     * @param id 专案主键
     * @return 结果
     */
    public int deleteLabSysProductById(Long id);

    /**
     * 批量删除专案
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabSysProductByIds(Long[] ids);

    /**
     * 查询所有专案
     *
     * @return
     */
    public List<String> selectAllProduct();
}
