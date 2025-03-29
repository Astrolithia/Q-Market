package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.ProductAttribute;

/**
 * 商品属性管理Mapper接口
 * 
 * @author shiro
 * @date 2025-03-29
 */
public interface ProductAttributeMapper 
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
     * 删除商品属性管理
     * 
     * @param id 商品属性管理主键
     * @return 结果
     */
    public int deleteProductAttributeById(Long id);

    /**
     * 批量删除商品属性管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductAttributeByIds(Long[] ids);
}
