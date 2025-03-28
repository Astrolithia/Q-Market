package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.MerchantProductMapper;
import com.ruoyi.manage.domain.MerchantProduct;
import com.ruoyi.manage.service.IMerchantProductService;

/**
 * 商品信息管理Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-28
 */
@Service
public class MerchantProductServiceImpl implements IMerchantProductService 
{
    @Autowired
    private MerchantProductMapper merchantProductMapper;

    /**
     * 查询商品信息管理
     * 
     * @param id 商品信息管理主键
     * @return 商品信息管理
     */
    @Override
    public MerchantProduct selectMerchantProductById(Long id)
    {
        return merchantProductMapper.selectMerchantProductById(id);
    }

    /**
     * 查询商品信息管理列表
     * 
     * @param merchantProduct 商品信息管理
     * @return 商品信息管理
     */
    @Override
    public List<MerchantProduct> selectMerchantProductList(MerchantProduct merchantProduct)
    {
        return merchantProductMapper.selectMerchantProductList(merchantProduct);
    }

    /**
     * 新增商品信息管理
     * 
     * @param merchantProduct 商品信息管理
     * @return 结果
     */
    @Override
    public int insertMerchantProduct(MerchantProduct merchantProduct)
    {
        merchantProduct.setCreateTime(DateUtils.getNowDate());
        return merchantProductMapper.insertMerchantProduct(merchantProduct);
    }

    /**
     * 修改商品信息管理
     * 
     * @param merchantProduct 商品信息管理
     * @return 结果
     */
    @Override
    public int updateMerchantProduct(MerchantProduct merchantProduct)
    {
        merchantProduct.setUpdateTime(DateUtils.getNowDate());
        return merchantProductMapper.updateMerchantProduct(merchantProduct);
    }

    /**
     * 批量删除商品信息管理
     * 
     * @param ids 需要删除的商品信息管理主键
     * @return 结果
     */
    @Override
    public int deleteMerchantProductByIds(Long[] ids)
    {
        return merchantProductMapper.deleteMerchantProductByIds(ids);
    }

    /**
     * 删除商品信息管理信息
     * 
     * @param id 商品信息管理主键
     * @return 结果
     */
    @Override
    public int deleteMerchantProductById(Long id)
    {
        return merchantProductMapper.deleteMerchantProductById(id);
    }
}
