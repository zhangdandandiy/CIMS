package com.ruoyi.lab.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.lab.domain.LabSysProduct;
import com.ruoyi.lab.mapper.LabSysProductMapper;
import com.ruoyi.lab.service.ILabSysProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 专案 Service业务层处理
 *
 * @author Dandan
 * @date 2023-08-04
 */
@Service
public class LabSysProductServiceImpl implements ILabSysProductService {
    @Autowired
    private LabSysProductMapper labSysProductMapper;

    /**
     * 查询专案
     *
     * @param id 专案主键
     * @return 专案
     */
    @Override
    public LabSysProduct selectLabSysProductById(Long id) {
        return labSysProductMapper.selectLabSysProductById(id);
    }

    /**
     * 查询专案列表
     *
     * @param labSysProduct 专案
     * @return 专案
     */
    @Override
    public List<LabSysProduct> selectLabSysProductList(LabSysProduct labSysProduct) {
        return labSysProductMapper.selectLabSysProductList(labSysProduct);
    }

    /**
     * 新增专案
     *
     * @param labSysProduct 专案
     * @return 结果
     */
    @Override
    public int insertLabSysProduct(LabSysProduct labSysProduct) {
        labSysProduct.setCreateTime(DateUtils.getNowDate());
        return labSysProductMapper.insertLabSysProduct(labSysProduct);
    }

    /**
     * 修改专案
     *
     * @param labSysProduct 专案
     * @return 结果
     */
    @Override
    public int updateLabSysProduct(LabSysProduct labSysProduct) {
        labSysProduct.setUpdateTime(DateUtils.getNowDate());
        return labSysProductMapper.updateLabSysProduct(labSysProduct);
    }

    /**
     * 批量删除专案
     *
     * @param ids 需要删除的专案主键
     * @return 结果
     */
    @Override
    public int deleteLabSysProductByIds(Long[] ids) {
        return labSysProductMapper.deleteLabSysProductByIds(ids);
    }

    /**
     * 删除专案信息
     *
     * @param id 专案主键
     * @return 结果
     */
    @Override
    public int deleteLabSysProductById(Long id) {
        return labSysProductMapper.deleteLabSysProductById(id);
    }

    /**
     * 查询所有专案
     *
     * @return
     */
    @Override
    public List<String> selectAllProduct() {
        return labSysProductMapper.selectAllProduct();
    }
}
