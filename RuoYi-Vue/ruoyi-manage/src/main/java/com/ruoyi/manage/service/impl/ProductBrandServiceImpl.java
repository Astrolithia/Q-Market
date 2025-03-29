package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.ProductBrandMapper;
import com.ruoyi.manage.domain.ProductBrand;
import com.ruoyi.manage.service.IProductBrandService;

/**
 * 商品品牌管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-29
 */
@Service
public class ProductBrandServiceImpl implements IProductBrandService 
{
    @Autowired
    private ProductBrandMapper productBrandMapper;

    /**
     * 查询商品品牌管理
     * 
     * @param id 商品品牌管理主键
     * @return 商品品牌管理
     */
    @Override
    public ProductBrand selectProductBrandById(Long id)
    {
        return productBrandMapper.selectProductBrandById(id);
    }

    /**
     * 查询商品品牌管理列表
     * 
     * @param productBrand 商品品牌管理
     * @return 商品品牌管理
     */
    @Override
    public List<ProductBrand> selectProductBrandList(ProductBrand productBrand)
    {
        return productBrandMapper.selectProductBrandList(productBrand);
    }

    /**
     * 新增商品品牌管理
     * 
     * @param productBrand 商品品牌管理
     * @return 结果
     */
    @Override
    public int insertProductBrand(ProductBrand productBrand)
    {
        productBrand.setCreateTime(DateUtils.getNowDate());
        return productBrandMapper.insertProductBrand(productBrand);
    }

    /**
     * 修改商品品牌管理
     * 
     * @param productBrand 商品品牌管理
     * @return 结果
     */
    @Override
    public int updateProductBrand(ProductBrand productBrand)
    {
        productBrand.setUpdateTime(DateUtils.getNowDate());
        return productBrandMapper.updateProductBrand(productBrand);
    }

    /**
     * 批量删除商品品牌管理
     * 
     * @param ids 需要删除的商品品牌管理主键
     * @return 结果
     */
    @Override
    public int deleteProductBrandByIds(Long[] ids)
    {
        return productBrandMapper.deleteProductBrandByIds(ids);
    }

    /**
     * 删除商品品牌管理信息
     * 
     * @param id 商品品牌管理主键
     * @return 结果
     */
    @Override
    public int deleteProductBrandById(Long id)
    {
        return productBrandMapper.deleteProductBrandById(id);
    }
}
