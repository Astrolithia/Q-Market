package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动商品关联对象 tb_promotion_product
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class PromotionProduct extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 活动ID */
    @Excel(name = "活动ID")
    private Long promotionId;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long productId;

    /** SKU ID */
    @Excel(name = "SKU ID")
    private Long skuId;

    /** 活动价格 */
    @Excel(name = "活动价格")
    private BigDecimal promotionPrice;

    /** 限购数量，0表示不限购 */
    @Excel(name = "限购数量，0表示不限购")
    private Long stockLimit;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setPromotionId(Long promotionId) 
    {
        this.promotionId = promotionId;
    }

    public Long getPromotionId() 
    {
        return promotionId;
    }

    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }

    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) 
    {
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getPromotionPrice() 
    {
        return promotionPrice;
    }

    public void setStockLimit(Long stockLimit) 
    {
        this.stockLimit = stockLimit;
    }

    public Long getStockLimit() 
    {
        return stockLimit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("promotionId", getPromotionId())
            .append("productId", getProductId())
            .append("skuId", getSkuId())
            .append("promotionPrice", getPromotionPrice())
            .append("stockLimit", getStockLimit())
            .append("createTime", getCreateTime())
            .toString();
    }
}
