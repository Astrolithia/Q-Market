package com.ruoyi.manage.service;

import java.util.List;
import com.ruoyi.manage.domain.PromotionProduct;

/**
 * 活动商品关联Service接口
 * 
 * @author shiro
 * @date 2025-03-27
 */
public interface IPromotionProductService 
{
    /**
     * 查询活动商品关联
     * 
     * @param id 活动商品关联主键
     * @return 活动商品关联
     */
    public PromotionProduct selectPromotionProductById(Long id);

    /**
     * 查询活动商品关联列表
     * 
     * @param promotionProduct 活动商品关联
     * @return 活动商品关联集合
     */
    public List<PromotionProduct> selectPromotionProductList(PromotionProduct promotionProduct);

    /**
     * 新增活动商品关联
     * 
     * @param promotionProduct 活动商品关联
     * @return 结果
     */
    public int insertPromotionProduct(PromotionProduct promotionProduct);

    /**
     * 修改活动商品关联
     * 
     * @param promotionProduct 活动商品关联
     * @return 结果
     */
    public int updatePromotionProduct(PromotionProduct promotionProduct);

    /**
     * 批量删除活动商品关联
     * 
     * @param ids 需要删除的活动商品关联主键集合
     * @return 结果
     */
    public int deletePromotionProductByIds(Long[] ids);

    /**
     * 删除活动商品关联信息
     * 
     * @param id 活动商品关联主键
     * @return 结果
     */
    public int deletePromotionProductById(Long id);
}
