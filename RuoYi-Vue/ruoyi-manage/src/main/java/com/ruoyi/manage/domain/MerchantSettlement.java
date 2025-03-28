package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商家结算管理对象 tb_merchant_settlement
 * 
 * @author shiro
 * @date 2025-03-28
 */
public class MerchantSettlement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 结算ID */
    @Excel(name = "结算ID")
    private Long id;

    /** 商家ID */
    @Excel(name = "商家ID")
    private Long merchantId;

    /** 结算单号 */
    @Excel(name = "结算单号")
    private String settlementNo;

    /** 结算开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settlementStart;

    /** 结算结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settlementEnd;

    /** 订单数量 */
    @Excel(name = "订单数量")
    private Long orderCount;

    /** 结算总金额 */
    @Excel(name = "结算总金额")
    private BigDecimal totalAmount;

    /** 平台分成 */
    @Excel(name = "平台分成")
    private BigDecimal platformAmount;

    /** 商家分成 */
    @Excel(name = "商家分成")
    private BigDecimal merchantAmount;

    /** 状态：0待结算，1已结算 */
    @Excel(name = "状态：0待结算，1已结算")
    private Integer status;

    /** 结算时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "结算时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date settlementTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }

    public void setSettlementNo(String settlementNo) 
    {
        this.settlementNo = settlementNo;
    }

    public String getSettlementNo() 
    {
        return settlementNo;
    }

    public void setSettlementStart(Date settlementStart) 
    {
        this.settlementStart = settlementStart;
    }

    public Date getSettlementStart() 
    {
        return settlementStart;
    }

    public void setSettlementEnd(Date settlementEnd) 
    {
        this.settlementEnd = settlementEnd;
    }

    public Date getSettlementEnd() 
    {
        return settlementEnd;
    }

    public void setOrderCount(Long orderCount) 
    {
        this.orderCount = orderCount;
    }

    public Long getOrderCount() 
    {
        return orderCount;
    }

    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }

    public void setPlatformAmount(BigDecimal platformAmount) 
    {
        this.platformAmount = platformAmount;
    }

    public BigDecimal getPlatformAmount() 
    {
        return platformAmount;
    }

    public void setMerchantAmount(BigDecimal merchantAmount) 
    {
        this.merchantAmount = merchantAmount;
    }

    public BigDecimal getMerchantAmount() 
    {
        return merchantAmount;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setSettlementTime(Date settlementTime) 
    {
        this.settlementTime = settlementTime;
    }

    public Date getSettlementTime() 
    {
        return settlementTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("merchantId", getMerchantId())
            .append("settlementNo", getSettlementNo())
            .append("settlementStart", getSettlementStart())
            .append("settlementEnd", getSettlementEnd())
            .append("orderCount", getOrderCount())
            .append("totalAmount", getTotalAmount())
            .append("platformAmount", getPlatformAmount())
            .append("merchantAmount", getMerchantAmount())
            .append("status", getStatus())
            .append("settlementTime", getSettlementTime())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
