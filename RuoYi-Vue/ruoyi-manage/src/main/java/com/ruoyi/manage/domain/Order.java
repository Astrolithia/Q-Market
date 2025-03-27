package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单信息管理对象 tb_order
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class Order extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 客户ID */
    @Excel(name = "客户ID")
    private Long customerId;

    /** 订单总金额 */
    @Excel(name = "订单总金额")
    private BigDecimal totalAmount;

    /** 实付金额 */
    @Excel(name = "实付金额")
    private BigDecimal payAmount;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private BigDecimal discountAmount;

    /** 运费 */
    @Excel(name = "运费")
    private BigDecimal shippingFee;

    /** 支付方式：1支付宝，2微信，3银行卡 */
    @Excel(name = "支付方式：1支付宝，2微信，3银行卡")
    private Integer payType;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;

    /** 支付流水号 */
    @Excel(name = "支付流水号")
    private String payNo;

    /** 订单状态：0待付款，1待发货，2待收货，3已完成，4已取消，5退款中，6已退款 */
    @Excel(name = "订单状态：0待付款，1待发货，2待收货，3已完成，4已取消，5退款中，6已退款")
    private Integer orderStatus;

    /** 收货人 */
    @Excel(name = "收货人")
    private String consignee;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String mobile;

    /** 收货地址 */
    @Excel(name = "收货地址")
    private String address;

    /** 订单备注 */
    @Excel(name = "订单备注")
    private String message;

    /** 商家ID */
    @Excel(name = "商家ID")
    private Long merchantId;

    /** 是否删除：0未删除，1已删除 */
    @Excel(name = "是否删除：0未删除，1已删除")
    private Integer isDeleted;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }

    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }

    public void setPayAmount(BigDecimal payAmount) 
    {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() 
    {
        return payAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) 
    {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getDiscountAmount() 
    {
        return discountAmount;
    }

    public void setShippingFee(BigDecimal shippingFee) 
    {
        this.shippingFee = shippingFee;
    }

    public BigDecimal getShippingFee() 
    {
        return shippingFee;
    }

    public void setPayType(Integer payType) 
    {
        this.payType = payType;
    }

    public Integer getPayType() 
    {
        return payType;
    }

    public void setPayTime(Date payTime) 
    {
        this.payTime = payTime;
    }

    public Date getPayTime() 
    {
        return payTime;
    }

    public void setPayNo(String payNo) 
    {
        this.payNo = payNo;
    }

    public String getPayNo() 
    {
        return payNo;
    }

    public void setOrderStatus(Integer orderStatus) 
    {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderStatus() 
    {
        return orderStatus;
    }

    public void setConsignee(String consignee) 
    {
        this.consignee = consignee;
    }

    public String getConsignee() 
    {
        return consignee;
    }

    public void setMobile(String mobile) 
    {
        this.mobile = mobile;
    }

    public String getMobile() 
    {
        return mobile;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }

    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }

    public void setIsDeleted(Integer isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Integer getIsDeleted() 
    {
        return isDeleted;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("customerId", getCustomerId())
            .append("totalAmount", getTotalAmount())
            .append("payAmount", getPayAmount())
            .append("discountAmount", getDiscountAmount())
            .append("shippingFee", getShippingFee())
            .append("payType", getPayType())
            .append("payTime", getPayTime())
            .append("payNo", getPayNo())
            .append("orderStatus", getOrderStatus())
            .append("consignee", getConsignee())
            .append("mobile", getMobile())
            .append("address", getAddress())
            .append("message", getMessage())
            .append("merchantId", getMerchantId())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
