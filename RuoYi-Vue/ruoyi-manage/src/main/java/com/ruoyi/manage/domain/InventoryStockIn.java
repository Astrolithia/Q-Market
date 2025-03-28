package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库管理对象 tb_stock_in
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class InventoryStockIn extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 入库单ID */
    private Long id;

    /** 入库单号 */
    @Excel(name = "入库单号")
    private String inNo;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long warehouseId;

    /** 商家ID */
    @Excel(name = "商家ID")
    private Long merchantId;

    /** 入库类型：1采购入库，2退货入库，3调拨入库 */
    @Excel(name = "入库类型：1采购入库，2退货入库，3调拨入库")
    private Integer inType;

    /** 状态：0待审核，1已审核，2已取消 */
    @Excel(name = "状态：0待审核，1已审核，2已取消")
    private Integer status;

    /** 入库总数量 */
    @Excel(name = "入库总数量")
    private Long totalQuantity;

    /** 入库总金额 */
    @Excel(name = "入库总金额")
    private BigDecimal totalAmount;

    /** 操作人 */
    @Excel(name = "操作人")
    private String operator;

    /** 操作人ID */
    private Long operatorId;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 审核人 */
    private String auditor;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setInNo(String inNo) 
    {
        this.inNo = inNo;
    }

    public String getInNo() 
    {
        return inNo;
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

    public void setInType(Integer inType) 
    {
        this.inType = inType;
    }

    public Integer getInType() 
    {
        return inType;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("inNo", getInNo())
            .append("warehouseId", getWarehouseId())
            .append("merchantId", getMerchantId())
            .append("inType", getInType())
            .append("status", getStatus())
            .append("totalQuantity", getTotalQuantity())
            .append("totalAmount", getTotalAmount())
            .append("operator", getOperator())
            .append("operatorId", getOperatorId())
            .append("auditTime", getAuditTime())
            .append("auditor", getAuditor())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
