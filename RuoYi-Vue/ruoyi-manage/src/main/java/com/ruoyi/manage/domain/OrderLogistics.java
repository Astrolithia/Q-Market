package com.ruoyi.manage.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单物流对象 tb_order_logistics
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class OrderLogistics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 物流ID */
    private Long id;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderId;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 配送方式 */
    @Excel(name = "配送方式")
    private String shippingMethod;

    /** 物流公司 */
    @Excel(name = "物流公司")
    private String logisticsCompany;

    /** 物流单号 */
    @Excel(name = "物流单号")
    private String logisticsNo;

    /** 发货时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date shippingTime;

    /** 送达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "送达时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deliveryTime;

    /** 物流状态：0待发货，1已发货，2已签收 */
    @Excel(name = "物流状态：0待发货，1已发货，2已签收")
    private Integer logisticsStatus;

    /** 物流信息 */
    @Excel(name = "物流信息")
    private String logisticsInfo;

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

    public void setShippingMethod(String shippingMethod) 
    {
        this.shippingMethod = shippingMethod;
    }

    public String getShippingMethod() 
    {
        return shippingMethod;
    }

    public void setLogisticsCompany(String logisticsCompany) 
    {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsCompany() 
    {
        return logisticsCompany;
    }

    public void setLogisticsNo(String logisticsNo) 
    {
        this.logisticsNo = logisticsNo;
    }

    public String getLogisticsNo() 
    {
        return logisticsNo;
    }

    public void setShippingTime(Date shippingTime) 
    {
        this.shippingTime = shippingTime;
    }

    public Date getShippingTime() 
    {
        return shippingTime;
    }

    public void setDeliveryTime(Date deliveryTime) 
    {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() 
    {
        return deliveryTime;
    }

    public void setLogisticsStatus(Integer logisticsStatus) 
    {
        this.logisticsStatus = logisticsStatus;
    }

    public Integer getLogisticsStatus() 
    {
        return logisticsStatus;
    }

    public void setLogisticsInfo(String logisticsInfo) 
    {
        this.logisticsInfo = logisticsInfo;
    }

    public String getLogisticsInfo() 
    {
        return logisticsInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderId", getOrderId())
            .append("orderNo", getOrderNo())
            .append("shippingMethod", getShippingMethod())
            .append("logisticsCompany", getLogisticsCompany())
            .append("logisticsNo", getLogisticsNo())
            .append("shippingTime", getShippingTime())
            .append("deliveryTime", getDeliveryTime())
            .append("logisticsStatus", getLogisticsStatus())
            .append("logisticsInfo", getLogisticsInfo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
