package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.ProductSku;

/**
 * 商品SKUService接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IProductSkuService 
{
    /**
     * 查询商品SKU
     * 
     * @param id 商品SKU主键
     * @return 商品SKU
     */
    public ProductSku selectProductSkuById(Long id);

    /**
     * 查询商品SKU列表
     * 
     * @param productSku 商品SKU
     * @return 商品SKU集合
     */
    public List<ProductSku> selectProductSkuList(ProductSku productSku);

    /**
     * 新增商品SKU
     * 
     * @param productSku 商品SKU
     * @return 结果
     */
    public int insertProductSku(ProductSku productSku);

    /**
     * 修改商品SKU
     * 
     * @param productSku 商品SKU
     * @return 结果
     */
    public int updateProductSku(ProductSku productSku);

    /**
     * 批量删除商品SKU
     * 
     * @param ids 需要删除的商品SKU主键集合
     * @return 结果
     */
    public int deleteProductSkuByIds(Long[] ids);

    /**
     * 删除商品SKU信息
     * 
     * @param id 商品SKU主键
     * @return 结果
     */
    public int deleteProductSkuById(Long id);
}
