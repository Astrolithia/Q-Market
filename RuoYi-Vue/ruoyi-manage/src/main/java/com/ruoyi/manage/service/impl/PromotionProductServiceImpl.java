package com.ruoyi.manage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.manage.mapper.PromotionProductMapper;
import com.ruoyi.manage.domain.PromotionProduct;
import com.ruoyi.manage.service.IPromotionProductService;

/**
 * 活动商品关联Service业务层处理
 * 
 * @author shiro
 * @date 2025-03-27
 */
@Service
public class PromotionProductServiceImpl implements IPromotionProductService 
{
    @Autowired
    private PromotionProductMapper promotionProductMapper;

    /**
     * 查询活动商品关联
     * 
     * @param id 活动商品关联主键
     * @return 活动商品关联
     */
    @Override
    public PromotionProduct selectPromotionProductById(Long id)
    {
        return promotionProductMapper.selectPromotionProductById(id);
    }

    /**
     * 查询活动商品关联列表
     * 
     * @param promotionProduct 活动商品关联
     * @return 活动商品关联
     */
    @Override
    public List<PromotionProduct> selectPromotionProductList(PromotionProduct promotionProduct)
    {
        return promotionProductMapper.selectPromotionProductList(promotionProduct);
    }

    /**
     * 新增活动商品关联
     * 
     * @param promotionProduct 活动商品关联
     * @return 结果
     */
    @Override
    public int insertPromotionProduct(PromotionProduct promotionProduct)
    {
        promotionProduct.setCreateTime(DateUtils.getNowDate());
        return promotionProductMapper.insertPromotionProduct(promotionProduct);
    }

    /**
     * 修改活动商品关联
     * 
     * @param promotionProduct 活动商品关联
     * @return 结果
     */
    @Override
    public int updatePromotionProduct(PromotionProduct promotionProduct)
    {
        return promotionProductMapper.updatePromotionProduct(promotionProduct);
    }

    /**
     * 批量删除活动商品关联
     * 
     * @param ids 需要删除的活动商品关联主键
     * @return 结果
     */
    @Override
    public int deletePromotionProductByIds(Long[] ids)
    {
        return promotionProductMapper.deletePromotionProductByIds(ids);
    }

    /**
     * 删除活动商品关联信息
     * 
     * @param id 活动商品关联主键
     * @return 结果
     */
    @Override
    public int deletePromotionProductById(Long id)
    {
        return promotionProductMapper.deletePromotionProductById(id);
    }
}
