package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.ProductSkuMapper;
import com.ruoyi.manage.domain.ProductSku;
import com.ruoyi.manage.service.IProductSkuService;

/**
 * 商品SKUService业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class ProductSkuServiceImpl implements IProductSkuService 
{
    @Autowired
    private ProductSkuMapper productSkuMapper;

    /**
     * 查询商品SKU
     * 
     * @param id 商品SKU主键
     * @return 商品SKU
     */
    @Override
    public ProductSku selectProductSkuById(Long id)
    {
        return productSkuMapper.selectProductSkuById(id);
    }

    /**
     * 查询商品SKU列表
     * 
     * @param productSku 商品SKU
     * @return 商品SKU
     */
    @Override
    public List<ProductSku> selectProductSkuList(ProductSku productSku)
    {
        return productSkuMapper.selectProductSkuList(productSku);
    }

    /**
     * 新增商品SKU
     * 
     * @param productSku 商品SKU
     * @return 结果
     */
    @Override
    public int insertProductSku(ProductSku productSku)
    {
        productSku.setCreateTime(DateUtils.getNowDate());
        return productSkuMapper.insertProductSku(productSku);
    }

    /**
     * 修改商品SKU
     * 
     * @param productSku 商品SKU
     * @return 结果
     */
    @Override
    public int updateProductSku(ProductSku productSku)
    {
        productSku.setUpdateTime(DateUtils.getNowDate());
        return productSkuMapper.updateProductSku(productSku);
    }

    /**
     * 批量删除商品SKU
     * 
     * @param ids 需要删除的商品SKU主键
     * @return 结果
     */
    @Override
    public int deleteProductSkuByIds(Long[] ids)
    {
        return productSkuMapper.deleteProductSkuByIds(ids);
    }

    /**
     * 删除商品SKU信息
     * 
     * @param id 商品SKU主键
     * @return 结果
     */
    @Override
    public int deleteProductSkuById(Long id)
    {
        return productSkuMapper.deleteProductSkuById(id);
    }
}
