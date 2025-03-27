package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 优惠券管理对象 tb_coupon
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class MarketingCoupon extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 优惠券ID */
    private Long id;

    /** 优惠券名称 */
    @Excel(name = "优惠券名称")
    private String name;

    /** 优惠券类型：1满减券，2折扣券，3直减券 */
    @Excel(name = "优惠券类型：1满减券，2折扣券，3直减券")
    private Integer type;

    /** 优惠券面值 */
    @Excel(name = "优惠券面值")
    private BigDecimal value;

    /** 使用门槛，0表示无门槛 */
    @Excel(name = "使用门槛，0表示无门槛")
    private BigDecimal minPoint;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 发行量，0表示不限量 */
    @Excel(name = "发行量，0表示不限量")
    private Long total;

    /** 已领取数量 */
    @Excel(name = "已领取数量")
    private Long used;

    /** 每人限领数量 */
    @Excel(name = "每人限领数量")
    private Long receiveCount;

    /** 状态：0禁用，1启用 */
    @Excel(name = "状态：0禁用，1启用")
    private Integer status;

    /** 使用说明 */
    @Excel(name = "使用说明")
    private String description;

    /** 商家ID */
    @Excel(name = "商家ID")
    private Long merchantId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public void setType(Integer type) 
    {
        this.type = type;
    }

    public Integer getType() 
    {
        return type;
    }

    public void setValue(BigDecimal value) 
    {
        this.value = value;
    }

    public BigDecimal getValue() 
    {
        return value;
    }

    public void setMinPoint(BigDecimal minPoint) 
    {
        this.minPoint = minPoint;
    }

    public BigDecimal getMinPoint() 
    {
        return minPoint;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setTotal(Long total) 
    {
        this.total = total;
    }

    public Long getTotal() 
    {
        return total;
    }

    public void setUsed(Long used) 
    {
        this.used = used;
    }

    public Long getUsed() 
    {
        return used;
    }

    public void setReceiveCount(Long receiveCount) 
    {
        this.receiveCount = receiveCount;
    }

    public Long getReceiveCount() 
    {
        return receiveCount;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
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
            .append("name", getName())
            .append("type", getType())
            .append("value", getValue())
            .append("minPoint", getMinPoint())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("total", getTotal())
            .append("used", getUsed())
            .append("receiveCount", getReceiveCount())
            .append("status", getStatus())
            .append("description", getDescription())
            .append("merchantId", getMerchantId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
