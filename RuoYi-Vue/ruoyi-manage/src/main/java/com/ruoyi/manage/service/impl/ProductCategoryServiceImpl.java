package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.ProductCategoryMapper;
import com.ruoyi.manage.domain.ProductCategory;
import com.ruoyi.manage.service.IProductCategoryService;

/**
 * 商品分类管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService 
{
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    /**
     * 查询商品分类管理
     * 
     * @param id 商品分类管理主键
     * @return 商品分类管理
     */
    @Override
    public ProductCategory selectProductCategoryById(Long id)
    {
        return productCategoryMapper.selectProductCategoryById(id);
    }

    /**
     * 查询商品分类管理列表
     * 
     * @param productCategory 商品分类管理
     * @return 商品分类管理
     */
    @Override
    public List<ProductCategory> selectProductCategoryList(ProductCategory productCategory)
    {
        return productCategoryMapper.selectProductCategoryList(productCategory);
    }

    /**
     * 新增商品分类管理
     * 
     * @param productCategory 商品分类管理
     * @return 结果
     */
    @Override
    public int insertProductCategory(ProductCategory productCategory)
    {
        productCategory.setCreateTime(DateUtils.getNowDate());
        return productCategoryMapper.insertProductCategory(productCategory);
    }

    /**
     * 修改商品分类管理
     * 
     * @param productCategory 商品分类管理
     * @return 结果
     */
    @Override
    public int updateProductCategory(ProductCategory productCategory)
    {
        productCategory.setUpdateTime(DateUtils.getNowDate());
        return productCategoryMapper.updateProductCategory(productCategory);
    }

    /**
     * 批量删除商品分类管理
     * 
     * @param ids 需要删除的商品分类管理主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryByIds(Long[] ids)
    {
        return productCategoryMapper.deleteProductCategoryByIds(ids);
    }

    /**
     * 删除商品分类管理信息
     * 
     * @param id 商品分类管理主键
     * @return 结果
     */
    @Override
    public int deleteProductCategoryById(Long id)
    {
        return productCategoryMapper.deleteProductCategoryById(id);
    }
}
