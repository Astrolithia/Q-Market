package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.ProductBrand;

/**
 * 商品品牌管理Service接口
 * 
 * @author shiro
 * @date 2025-03-29
 */
public interface IProductBrandService 
{
    /**
     * 查询商品品牌管理
     * 
     * @param id 商品品牌管理主键
     * @return 商品品牌管理
     */
    public ProductBrand selectProductBrandById(Long id);

    /**
     * 查询商品品牌管理列表
     * 
     * @param productBrand 商品品牌管理
     * @return 商品品牌管理集合
     */
    public List<ProductBrand> selectProductBrandList(ProductBrand productBrand);

    /**
     * 新增商品品牌管理
     * 
     * @param productBrand 商品品牌管理
     * @return 结果
     */
    public int insertProductBrand(ProductBrand productBrand);

    /**
     * 修改商品品牌管理
     * 
     * @param productBrand 商品品牌管理
     * @return 结果
     */
    public int updateProductBrand(ProductBrand productBrand);

    /**
     * 批量删除商品品牌管理
     * 
     * @param ids 需要删除的商品品牌管理主键集合
     * @return 结果
     */
    public int deleteProductBrandByIds(Long[] ids);

    /**
     * 删除商品品牌管理信息
     * 
     * @param id 商品品牌管理主键
     * @return 结果
     */
    public int deleteProductBrandById(Long id);
}
