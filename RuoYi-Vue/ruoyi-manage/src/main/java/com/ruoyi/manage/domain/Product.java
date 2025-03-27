package com.ruoyi.manage.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 商品信息管理对象 tb_product
 * 
 * @author shiro
 * @date 2025-03-27
 */
public class Product extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 商品ID */
    private Long id;

    /** 商品标题 */
    @Excel(name = "商品标题")
    private String title;

    /** 副标题 */
    @Excel(name = "副标题")
    private String subTitle;

    /** 分类ID */
    @Excel(name = "分类ID")
    private Long categoryId;

    /** 品牌ID */
    @Excel(name = "品牌ID")
    private Long brandId;

    /** 商品价格 */
    @Excel(name = "商品价格")
    private BigDecimal price;

    /** 市场价 */
    @Excel(name = "市场价")
    private BigDecimal marketPrice;

    /** 成本价 */
    @Excel(name = "成本价")
    private BigDecimal costPrice;

    /** 库存 */
    @Excel(name = "库存")
    private Long stock;

    /** 销量 */
    @Excel(name = "销量")
    private Long sales;

    /** 主图 */
    @Excel(name = "主图")
    private String mainImage;

    /** 相册图片，多个以逗号分隔 */
    @Excel(name = "相册图片，多个以逗号分隔")
    private String album;

    /** 视频URL */
    @Excel(name = "视频URL")
    private String video;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 重量(KG) */
    @Excel(name = "重量(KG)")
    private BigDecimal weight;

    /** 关键字 */
    @Excel(name = "关键字")
    private String keywords;

    /** 状态：0下架，1上架 */
    @Excel(name = "状态：0下架，1上架")
    private Integer status;

    /** 商品介绍 */
    @Excel(name = "商品介绍")
    private String introduction;

    /** 规格参数 */
    @Excel(name = "规格参数")
    private String specification;

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

    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }

    public void setSubTitle(String subTitle) 
    {
        this.subTitle = subTitle;
    }

    public String getSubTitle() 
    {
        return subTitle;
    }

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }

    public void setBrandId(Long brandId) 
    {
        this.brandId = brandId;
    }

    public Long getBrandId() 
    {
        return brandId;
    }

    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }

    public void setMarketPrice(BigDecimal marketPrice) 
    {
        this.marketPrice = marketPrice;
    }

    public BigDecimal getMarketPrice() 
    {
        return marketPrice;
    }

    public void setCostPrice(BigDecimal costPrice) 
    {
        this.costPrice = costPrice;
    }

    public BigDecimal getCostPrice() 
    {
        return costPrice;
    }

    public void setStock(Long stock) 
    {
        this.stock = stock;
    }

    public Long getStock() 
    {
        return stock;
    }

    public void setSales(Long sales) 
    {
        this.sales = sales;
    }

    public Long getSales() 
    {
        return sales;
    }

    public void setMainImage(String mainImage) 
    {
        this.mainImage = mainImage;
    }

    public String getMainImage() 
    {
        return mainImage;
    }

    public void setAlbum(String album) 
    {
        this.album = album;
    }

    public String getAlbum() 
    {
        return album;
    }

    public void setVideo(String video) 
    {
        this.video = video;
    }

    public String getVideo() 
    {
        return video;
    }

    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }

    public void setWeight(BigDecimal weight) 
    {
        this.weight = weight;
    }

    public BigDecimal getWeight() 
    {
        return weight;
    }

    public void setKeywords(String keywords) 
    {
        this.keywords = keywords;
    }

    public String getKeywords() 
    {
        return keywords;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }

    public void setSpecification(String specification) 
    {
        this.specification = specification;
    }

    public String getSpecification() 
    {
        return specification;
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
            .append("title", getTitle())
            .append("subTitle", getSubTitle())
            .append("categoryId", getCategoryId())
            .append("brandId", getBrandId())
            .append("price", getPrice())
            .append("marketPrice", getMarketPrice())
            .append("costPrice", getCostPrice())
            .append("stock", getStock())
            .append("sales", getSales())
            .append("mainImage", getMainImage())
            .append("album", getAlbum())
            .append("video", getVideo())
            .append("unit", getUnit())
            .append("weight", getWeight())
            .append("keywords", getKeywords())
            .append("status", getStatus())
            .append("introduction", getIntroduction())
            .append("specification", getSpecification())
            .append("merchantId", getMerchantId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
