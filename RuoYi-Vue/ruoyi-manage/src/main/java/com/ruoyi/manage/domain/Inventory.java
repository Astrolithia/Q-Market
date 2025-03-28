package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存查询对象 tb_inventory
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class Inventory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 库存ID */
    private Long id;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long warehouseId;

    /** 商品ID */
    @Excel(name = "商品ID")
    private Long productId;

    /** SKU ID */
    @Excel(name = "SKU ID")
    private Long skuId;

    /** 数量 */
    @Excel(name = "数量")
    private Long quantity;

    /** 锁定数量 */
    @Excel(name = "锁定数量")
    private Long lockQuantity;

    /** 可用数量 */
    @Excel(name = "可用数量")
    private Long availableQuantity;

    /** 预警数量 */
    @Excel(name = "预警数量")
    private Long alertQuantity;

    /** 成本价 */
    private BigDecimal costPrice;

    /** 商家ID */
    private Long merchantId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setWarehouseId(Long warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() 
    {
        return warehouseId;
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

    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }

    public void setLockQuantity(Long lockQuantity) 
    {
        this.lockQuantity = lockQuantity;
    }

    public Long getLockQuantity() 
    {
        return lockQuantity;
    }

    public void setAvailableQuantity(Long availableQuantity) 
    {
        this.availableQuantity = availableQuantity;
    }

    public Long getAvailableQuantity() 
    {
        return availableQuantity;
    }

    public void setAlertQuantity(Long alertQuantity) 
    {
        this.alertQuantity = alertQuantity;
    }

    public Long getAlertQuantity() 
    {
        return alertQuantity;
    }

    public void setCostPrice(BigDecimal costPrice) 
    {
        this.costPrice = costPrice;
    }

    public BigDecimal getCostPrice() 
    {
        return costPrice;
    }

    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("warehouseId", getWarehouseId())
            .append("productId", getProductId())
            .append("skuId", getSkuId())
            .append("quantity", getQuantity())
            .append("lockQuantity", getLockQuantity())
            .append("availableQuantity", getAvailableQuantity())
            .append("alertQuantity", getAlertQuantity())
            .append("costPrice", getCostPrice())
            .append("merchantId", getMerchantId())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
