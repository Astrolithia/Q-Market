package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员等级管理对象 tb_customer_level
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class CustomerLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 等级ID */
    private Long id;

    /** 等级名称 */
    @Excel(name = "等级名称")
    private String levelName;

    /** 最低积分 */
    private Long minPoint;

    /** 最高积分 */
    private Long maxPoint;

    /** 折扣率 */
    @Excel(name = "折扣率")
    private BigDecimal discount;

    /** 等级描述 */
    @Excel(name = "等级描述")
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setLevelName(String levelName) 
    {
        this.levelName = levelName;
    }

    public String getLevelName() 
    {
        return levelName;
    }

    public void setMinPoint(Long minPoint) 
    {
        this.minPoint = minPoint;
    }

    public Long getMinPoint() 
    {
        return minPoint;
    }

    public void setMaxPoint(Long maxPoint) 
    {
        this.maxPoint = maxPoint;
    }

    public Long getMaxPoint() 
    {
        return maxPoint;
    }

    public void setDiscount(BigDecimal discount) 
    {
        this.discount = discount;
    }

    public BigDecimal getDiscount() 
    {
        return discount;
    }

    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("levelName", getLevelName())
            .append("minPoint", getMinPoint())
            .append("maxPoint", getMaxPoint())
            .append("discount", getDiscount())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
