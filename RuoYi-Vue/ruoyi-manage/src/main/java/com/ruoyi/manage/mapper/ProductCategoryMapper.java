package com.ruoyi.manage.mapper;

import java.util.List;
import com.ruoyi.manage.domain.ProductCategory;

/**
 * 商品分类管理Mapper接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface ProductCategoryMapper 
{
    /**
     * 查询商品分类管理
     * 
     * @param id 商品分类管理主键
     * @return 商品分类管理
     */
    public ProductCategory selectProductCategoryById(Long id);

    /**
     * 查询商品分类管理列表
     * 
     * @param productCategory 商品分类管理
     * @return 商品分类管理集合
     */
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory);

    /**
     * 新增商品分类管理
     * 
     * @param productCategory 商品分类管理
     * @return 结果
     */
    public int insertProductCategory(ProductCategory productCategory);

    /**
     * 修改商品分类管理
     * 
     * @param productCategory 商品分类管理
     * @return 结果
     */
    public int updateProductCategory(ProductCategory productCategory);

    /**
     * 删除商品分类管理
     * 
     * @param id 商品分类管理主键
     * @return 结果
     */
    public int deleteProductCategoryById(Long id);

    /**
     * 批量删除商品分类管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductCategoryByIds(Long[] ids);
}
