package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.MerchantProduct;

/**
 * 商品信息管理Service接口
 * 
 * @author shiro
 * @date 2025-03-28
 */
public interface IMerchantProductService 
{
    /**
     * 查询商品信息管理
     * 
     * @param id 商品信息管理主键
     * @return 商品信息管理
     */
    public MerchantProduct selectMerchantProductById(Long id);

    /**
     * 查询商品信息管理列表
     * 
     * @param merchantProduct 商品信息管理
     * @return 商品信息管理集合
     */
    public List<MerchantProduct> selectMerchantProductList(MerchantProduct merchantProduct);

    /**
     * 新增商品信息管理
     * 
     * @param merchantProduct 商品信息管理
     * @return 结果
     */
    public int insertMerchantProduct(MerchantProduct merchantProduct);

    /**
     * 修改商品信息管理
     * 
     * @param merchantProduct 商品信息管理
     * @return 结果
     */
    public int updateMerchantProduct(MerchantProduct merchantProduct);

    /**
     * 批量删除商品信息管理
     * 
     * @param ids 需要删除的商品信息管理主键集合
     * @return 结果
     */
    public int deleteMerchantProductByIds(Long[] ids);

    /**
     * 删除商品信息管理信息
     * 
     * @param id 商品信息管理主键
     * @return 结果
     */
    public int deleteMerchantProductById(Long id);
}
