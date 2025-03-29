package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.ProductAttribute;

/**
 * 商品属性管理Service接口
 * 
 * @author shiro
 * @date 2025-03-29
 */
public interface IProductAttributeService 
{
    /**
     * 查询商品属性管理
     * 
     * @param id 商品属性管理主键
     * @return 商品属性管理
     */
    public ProductAttribute selectProductAttributeById(Long id);

    /**
     * 查询商品属性管理列表
     * 
     * @param productAttribute 商品属性管理
     * @return 商品属性管理集合
     */
    public List<ProductAttribute> selectProductAttributeList(ProductAttribute productAttribute);

    /**
     * 新增商品属性管理
     * 
     * @param productAttribute 商品属性管理
     * @return 结果
     */
    public int insertProductAttribute(ProductAttribute productAttribute);

    /**
     * 修改商品属性管理
     * 
     * @param productAttribute 商品属性管理
     * @return 结果
     */
    public int updateProductAttribute(ProductAttribute productAttribute);

    /**
     * 批量删除商品属性管理
     * 
     * @param ids 需要删除的商品属性管理主键集合
     * @return 结果
     */
    public int deleteProductAttributeByIds(Long[] ids);

    /**
     * 删除商品属性管理信息
     * 
     * @param id 商品属性管理主键
     * @return 结果
     */
    public int deleteProductAttributeById(Long id);
}
