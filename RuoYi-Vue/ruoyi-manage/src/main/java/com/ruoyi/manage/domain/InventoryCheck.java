package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存盘点对象 tb_inventory_check
 * 
 * @author shiro
 * @date 2025-03-28
 */
public class InventoryCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 盘点单ID */
    @Excel(name = "盘点单ID")
    private Long id;

    /** 盘点单号 */
    @Excel(name = "盘点单号")
    private String checkNo;

    /** 仓库ID */
    @Excel(name = "仓库ID")
    private Long warehouseId;

    /** 商家ID */
    private Long merchantId;

    /** 盘点类型：1全面盘点，2抽样盘点，3动态盘点 */
    @Excel(name = "盘点类型：1全面盘点，2抽样盘点，3动态盘点")
    private Integer checkType;

    /** 状态：0草稿，1待审核，2已审核，3已取消 */
    @Excel(name = "状态：0草稿，1待审核，2已审核，3已取消")
    private Integer status;

    /** 盘点总数量 */
    @Excel(name = "盘点总数量")
    private Long totalQuantity;

    /** 盘盈数量 */
    @Excel(name = "盘盈数量")
    private Long profitQuantity;

    /** 盘亏数量 */
    @Excel(name = "盘亏数量")
    private Long lossQuantity;

    /** 盘盈金额 */
    private BigDecimal profitAmount;

    /** 盘亏金额 */
    private BigDecimal lossAmount;

    /** 操作人 */
    private String operator;

    /** 操作人ID */
    private Long operatorId;

    /** 盘点时间 */
    private Date checkTime;

    /** 审核时间 */
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

    public void setCheckNo(String checkNo) 
    {
        this.checkNo = checkNo;
    }

    public String getCheckNo() 
    {
        return checkNo;
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

    public void setCheckType(Integer checkType) 
    {
        this.checkType = checkType;
    }

    public Integer getCheckType() 
    {
        return checkType;
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

    public void setProfitQuantity(Long profitQuantity) 
    {
        this.profitQuantity = profitQuantity;
    }

    public Long getProfitQuantity() 
    {
        return profitQuantity;
    }

    public void setLossQuantity(Long lossQuantity) 
    {
        this.lossQuantity = lossQuantity;
    }

    public Long getLossQuantity() 
    {
        return lossQuantity;
    }

    public void setProfitAmount(BigDecimal profitAmount) 
    {
        this.profitAmount = profitAmount;
    }

    public BigDecimal getProfitAmount() 
    {
        return profitAmount;
    }

    public void setLossAmount(BigDecimal lossAmount) 
    {
        this.lossAmount = lossAmount;
    }

    public BigDecimal getLossAmount() 
    {
        return lossAmount;
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

    public void setCheckTime(Date checkTime) 
    {
        this.checkTime = checkTime;
    }

    public Date getCheckTime() 
    {
        return checkTime;
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
            .append("checkNo", getCheckNo())
            .append("warehouseId", getWarehouseId())
            .append("merchantId", getMerchantId())
            .append("checkType", getCheckType())
            .append("status", getStatus())
            .append("totalQuantity", getTotalQuantity())
            .append("profitQuantity", getProfitQuantity())
            .append("lossQuantity", getLossQuantity())
            .append("profitAmount", getProfitAmount())
            .append("lossAmount", getLossAmount())
            .append("operator", getOperator())
            .append("operatorId", getOperatorId())
            .append("checkTime", getCheckTime())
            .append("auditTime", getAuditTime())
            .append("auditor", getAuditor())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
