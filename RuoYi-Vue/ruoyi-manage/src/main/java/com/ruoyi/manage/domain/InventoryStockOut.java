package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出库管理对象 tb_stock_out
 * 
 * @author shiro
 * @date 2025-03-28
 */
public class InventoryStockOut extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 出库单ID */
    @Excel(name = "出库单ID")
    private Long id;

    /** 出库单号 */
    @Excel(name = "出库单号")
    private String outNo;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long warehouseId;

    /** 商家ID */
    private Long merchantId;

    /** 出库类型：1销售出库，2退货出库，3调拨出库 */
    @Excel(name = "出库类型：1销售出库，2退货出库，3调拨出库")
    private Integer outType;

    /** 状态：0待审核，1已审核，2已取消 */
    @Excel(name = "状态：0待审核，1已审核，2已取消")
    private Integer status;

    /** 出库总数量 */
    @Excel(name = "出库总数量")
    private Long totalQuantity;

    /** 出库总金额 */
    @Excel(name = "出库总金额")
    private BigDecimal totalAmount;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 操作人ID */
    private Long operatorId;

    /** 审核时间 */
    private Date auditTime;

    /** 审核人 */
    private String auditor;

    /** 关联订单ID */
    private Long orderId;

    /** 关联订单号 */
    private String orderNo;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setOutNo(String outNo) 
    {
        this.outNo = outNo;
    }

    public String getOutNo() 
    {
        return outNo;
    }

    public void setWarehouseId(Long warehouseId) 
    {
        this.warehouseId = warehouseId;
    }

    public Long getWarehouseId() 
    {
        return warehouseId;
    }

    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
    }

    public void setOutType(Integer outType) 
    {
        this.outType = outType;
    }

    public Integer getOutType() 
    {
        return outType;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setTotalQuantity(Long totalQuantity) 
    {
        this.totalQuantity = totalQuantity;
    }

    public Long getTotalQuantity() 
    {
        return totalQuantity;
    }

    public void setTotalAmount(BigDecimal totalAmount) 
    {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() 
    {
        return totalAmount;
    }

    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }

    public void setOperatorId(Long operatorId) 
    {
        this.operatorId = operatorId;
    }

    public Long getOperatorId() 
    {
        return operatorId;
    }

    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }

    public void setAuditor(String auditor) 
    {
        this.auditor = auditor;
    }

    public String getAuditor() 
    {
        return auditor;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("outNo", getOutNo())
            .append("warehouseId", getWarehouseId())
            .append("merchantId", getMerchantId())
            .append("outType", getOutType())
            .append("status", getStatus())
            .append("totalQuantity", getTotalQuantity())
            .append("totalAmount", getTotalAmount())
            .append("operator", getOperator())
            .append("operatorId", getOperatorId())
            .append("auditTime", getAuditTime())
            .append("auditor", getAuditor())
            .append("orderId", getOrderId())
            .append("orderNo", getOrderNo())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
