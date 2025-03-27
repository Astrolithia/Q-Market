package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单明细管理对象 tb_order_item
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class OrderItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 明细ID */
    private Long id;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long productId;

    /** SKU ID */
    @Excel(name = "SKU ID")
    private Long skuId;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String productName;

    /** SKU规格 */
    @Excel(name = "SKU规格")
    private String skuSpec;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String productImage;

    /** 原价 */
    @Excel(name = "原价")
    private BigDecimal price;

    /** 实际价格 */
    @Excel(name = "实际价格")
    private BigDecimal actualPrice;

    /** 数量 */
    @Excel(name = "数量")
    private Long quantity;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal totalAmount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }

    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
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

    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }

    public void setSkuSpec(String skuSpec) 
    {
        this.skuSpec = skuSpec;
    }

    public String getSkuSpec() 
    {
        return skuSpec;
    }

    public void setProductImage(String productImage) 
    {
        this.productImage = productImage;
    }

    public String getProductImage() 
    {
        return productImage;
    }

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    public void setActualPrice(BigDecimal actualPrice) 
    {
        this.actualPrice = actualPrice;
    }

    public BigDecimal getActualPrice() 
    {
        return actualPrice;
    }

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }

    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderNo", getOrderNo())
            .append("productId", getProductId())
            .append("skuId", getSkuId())
            .append("productName", getProductName())
            .append("skuSpec", getSkuSpec())
            .append("productImage", getProductImage())
            .append("price", getPrice())
            .append("actualPrice", getActualPrice())
            .append("quantity", getQuantity())
            .append("totalAmount", getTotalAmount())
            .append("createTime", getCreateTime())
            .toString();
    }
}
