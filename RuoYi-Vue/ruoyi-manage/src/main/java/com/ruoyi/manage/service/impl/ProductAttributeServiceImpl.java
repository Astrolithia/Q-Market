package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.ProductAttributeMapper;
import com.ruoyi.manage.domain.ProductAttribute;
import com.ruoyi.manage.service.IProductAttributeService;

/**
 * 商品属性管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-29
 */
@Service
public class ProductAttributeServiceImpl implements IProductAttributeService 
{
    @Autowired
    private ProductAttributeMapper productAttributeMapper;

    /**
     * 查询商品属性管理
     * 
     * @param id 商品属性管理主键
     * @return 商品属性管理
     */
    @Override
    public ProductAttribute selectProductAttributeById(Long id)
    {
        return productAttributeMapper.selectProductAttributeById(id);
    }

    /**
     * 查询商品属性管理列表
     * 
     * @param productAttribute 商品属性管理
     * @return 商品属性管理
     */
    @Override
    public List<ProductAttribute> selectProductAttributeList(ProductAttribute productAttribute)
    {
        return productAttributeMapper.selectProductAttributeList(productAttribute);
    }

    /**
     * 新增商品属性管理
     * 
     * @param productAttribute 商品属性管理
     * @return 结果
     */
    @Override
    public int insertProductAttribute(ProductAttribute productAttribute)
    {
        productAttribute.setCreateTime(DateUtils.getNowDate());
        return productAttributeMapper.insertProductAttribute(productAttribute);
    }

    /**
     * 修改商品属性管理
     * 
     * @param productAttribute 商品属性管理
     * @return 结果
     */
    @Override
    public int updateProductAttribute(ProductAttribute productAttribute)
    {
        productAttribute.setUpdateTime(DateUtils.getNowDate());
        return productAttributeMapper.updateProductAttribute(productAttribute);
    }

    /**
     * 批量删除商品属性管理
     * 
     * @param ids 需要删除的商品属性管理主键
     * @return 结果
     */
    @Override
    public int deleteProductAttributeByIds(Long[] ids)
    {
        return productAttributeMapper.deleteProductAttributeByIds(ids);
    }

    /**
     * 删除商品属性管理信息
     * 
     * @param id 商品属性管理主键
     * @return 结果
     */
    @Override
    public int deleteProductAttributeById(Long id)
    {
        return productAttributeMapper.deleteProductAttributeById(id);
    }
}
