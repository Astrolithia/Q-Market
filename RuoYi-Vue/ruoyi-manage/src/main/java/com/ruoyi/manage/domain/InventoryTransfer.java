package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存调拨对象 tb_inventory_transfer
 * 
 * @author shiro
 * @date 2025-03-28
 */
public class InventoryTransfer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 调拨单ID */
    private Long id;

    /** 调拨单号 */
    @Excel(name = "调拨单号")
    private String transferNo;

    /** 调出仓库ID */
    @Excel(name = "调出仓库ID")
    private Long outWarehouseId;

    /** 调入仓库ID */
    @Excel(name = "调入仓库ID")
    private Long inWarehouseId;

    /** 商家ID */
    private Long merchantId;

    /** 状态：0草稿，1待出库，2已出库待入库，3已完成，4已取消 */
    @Excel(name = "状态：0草稿，1待出库，2已出库待入库，3已完成，4已取消")
    private Integer status;

    /** 调拨总数量 */
    @Excel(name = "调拨总数量")
    private Long totalQuantity;

    /** 调拨总金额 */
    private BigDecimal totalAmount;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 操作人ID */
    private Long operatorId;

    /** 出库时间 */
    private Date outTime;

    /** 入库时间 */
    private Date inTime;

    /** 出库操作人 */
    private String outOperator;

    /** 入库操作人 */
    private String inOperator;

    /** 关联出库单号 */
    private String outStockNo;

    /** 关联入库单号 */
    private String inStockNo;

    /** 调拨原因 */
    private String reason;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setTransferNo(String transferNo) 
    {
        this.transferNo = transferNo;
    }

    public String getTransferNo() 
    {
        return transferNo;
    }

    public void setOutWarehouseId(Long outWarehouseId) 
    {
        this.outWarehouseId = outWarehouseId;
    }

    public Long getOutWarehouseId() 
    {
        return outWarehouseId;
    }

    public void setInWarehouseId(Long inWarehouseId) 
    {
        this.inWarehouseId = inWarehouseId;
    }

    public Long getInWarehouseId() 
    {
        return inWarehouseId;
    }

    public void setMerchantId(Long merchantId) 
    {
        this.merchantId = merchantId;
    }

    public Long getMerchantId() 
    {
        return merchantId;
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

    public void setOutTime(Date outTime) 
    {
        this.outTime = outTime;
    }

    public Date getOutTime() 
    {
        return outTime;
    }

    public void setInTime(Date inTime) 
    {
        this.inTime = inTime;
    }

    public Date getInTime() 
    {
        return inTime;
    }

    public void setOutOperator(String outOperator) 
    {
        this.outOperator = outOperator;
    }

    public String getOutOperator() 
    {
        return outOperator;
    }

    public void setInOperator(String inOperator) 
    {
        this.inOperator = inOperator;
    }

    public String getInOperator() 
    {
        return inOperator;
    }

    public void setOutStockNo(String outStockNo) 
    {
        this.outStockNo = outStockNo;
    }

    public String getOutStockNo() 
    {
        return outStockNo;
    }

    public void setInStockNo(String inStockNo) 
    {
        this.inStockNo = inStockNo;
    }

    public String getInStockNo() 
    {
        return inStockNo;
    }

    public void setReason(String reason) 
    {
        this.reason = reason;
    }

    public String getReason() 
    {
        return reason;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("transferNo", getTransferNo())
            .append("outWarehouseId", getOutWarehouseId())
            .append("inWarehouseId", getInWarehouseId())
            .append("merchantId", getMerchantId())
            .append("status", getStatus())
            .append("totalQuantity", getTotalQuantity())
            .append("totalAmount", getTotalAmount())
            .append("operator", getOperator())
            .append("operatorId", getOperatorId())
            .append("outTime", getOutTime())
            .append("inTime", getInTime())
            .append("outOperator", getOutOperator())
            .append("inOperator", getInOperator())
            .append("outStockNo", getOutStockNo())
            .append("inStockNo", getInStockNo())
            .append("reason", getReason())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
