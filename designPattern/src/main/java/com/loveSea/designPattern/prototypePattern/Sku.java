package com.loveSea.designPattern.prototypePattern;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
public class Sku implements Cloneable {

    public static class SkuHolder {
        private static Sku sku = new Sku();
    }

    public static Sku newInstance() {
        try {
            return (Sku) SkuHolder.sku.clone();
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Sku();
        }
    }

/*    @Override
    public Sku clone() {
        Object ob = null;
        try {
            ob = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Sku) ob;
    }*/

    private Integer skuId;

    private Integer pyCategoryId;

    private String categoryCode;

    private String goodsCode;

    private String skuCode;

    private String modelNum;

    private String skuName;

    private String unit;

    private String attributeName;

    private String material;

    private String specification;

    private String style;

    private String linkUrl;

    private BigDecimal costPrice;

    private BigDecimal weight;

    private Boolean enabled;

    private Integer packageCount;

    private String performanceOwner;

    private Long performanceOwnerId;

    private String goodsStatus;

    private Date modified;

    private Boolean selfOwnedBrand;

    private Date devDate;

    private String productNo;

    private String memo;

    private String aliasCnName;

    private Integer storeId;

    private Date createDate;

    private BigDecimal packfee;

    private Boolean priceFactorChanged;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getPyCategoryId() {
        return pyCategoryId;
    }

    public void setPyCategoryId(Integer pyCategoryId) {
        this.pyCategoryId = pyCategoryId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode == null ? null : categoryCode.trim();
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode == null ? null : goodsCode.trim();
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getModelNum() {
        return modelNum;
    }

    public void setModelNum(String modelNum) {
        this.modelNum = modelNum == null ? null : modelNum.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName == null ? null : attributeName.trim();
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material == null ? null : material.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getPackageCount() {
        return packageCount;
    }

    public void setPackageCount(Integer packageCount) {
        this.packageCount = packageCount;
    }

    public String getPerformanceOwner() {
        return performanceOwner;
    }

    public void setPerformanceOwner(String performanceOwner) {
        this.performanceOwner = performanceOwner == null ? null : performanceOwner.trim();
    }

    public Long getPerformanceOwnerId() {
        return performanceOwnerId;
    }

    public void setPerformanceOwnerId(Long performanceOwnerId) {
        this.performanceOwnerId = performanceOwnerId;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus == null ? null : goodsStatus.trim();
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Boolean getSelfOwnedBrand() {
        return selfOwnedBrand;
    }

    public void setSelfOwnedBrand(Boolean selfOwnedBrand) {
        this.selfOwnedBrand = selfOwnedBrand;
    }

    public Date getDevDate() {
        return devDate;
    }

    public void setDevDate(Date devDate) {
        this.devDate = devDate;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo == null ? null : productNo.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getAliasCnName() {
        return aliasCnName;
    }

    public void setAliasCnName(String aliasCnName) {
        this.aliasCnName = aliasCnName == null ? null : aliasCnName.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getPackfee() {
        return packfee;
    }

    public void setPackfee(BigDecimal packfee) {
        this.packfee = packfee;
    }

    public Boolean getPriceFactorChanged() {
        return priceFactorChanged;
    }

    public void setPriceFactorChanged(Boolean priceFactorChanged) {
        this.priceFactorChanged = priceFactorChanged;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuId=").append(skuId);
        sb.append(", pyCategoryId=").append(pyCategoryId);
        sb.append(", categoryCode=").append(categoryCode);
        sb.append(", goodsCode=").append(goodsCode);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", modelNum=").append(modelNum);
        sb.append(", skuName=").append(skuName);
        sb.append(", unit=").append(unit);
        sb.append(", attributeName=").append(attributeName);
        sb.append(", material=").append(material);
        sb.append(", specification=").append(specification);
        sb.append(", style=").append(style);
        sb.append(", linkUrl=").append(linkUrl);
        sb.append(", costPrice=").append(costPrice);
        sb.append(", weight=").append(weight);
        sb.append(", enabled=").append(enabled);
        sb.append(", packageCount=").append(packageCount);
        sb.append(", performanceOwner=").append(performanceOwner);
        sb.append(", performanceOwnerId=").append(performanceOwnerId);
        sb.append(", goodsStatus=").append(goodsStatus);
        sb.append(", modified=").append(modified);
        sb.append(", selfOwnedBrand=").append(selfOwnedBrand);
        sb.append(", devDate=").append(devDate);
        sb.append(", productNo=").append(productNo);
        sb.append(", memo=").append(memo);
        sb.append(", aliasCnName=").append(aliasCnName);
        sb.append(", storeId=").append(storeId);
        sb.append(", createDate=").append(createDate);
        sb.append(", packfee=").append(packfee);
        sb.append(", priceFactorChanged=").append(priceFactorChanged);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Sku other = (Sku) that;
        return (this.getSkuId() == null ? other.getSkuId() == null : this.getSkuId().equals(other.getSkuId()))
                && (this.getPyCategoryId() == null ? other.getPyCategoryId() == null : this.getPyCategoryId().equals(other.getPyCategoryId()))
                && (this.getCategoryCode() == null ? other.getCategoryCode() == null : this.getCategoryCode().equals(other.getCategoryCode()))
                && (this.getGoodsCode() == null ? other.getGoodsCode() == null : this.getGoodsCode().equals(other.getGoodsCode()))
                && (this.getSkuCode() == null ? other.getSkuCode() == null : this.getSkuCode().equals(other.getSkuCode()))
                && (this.getModelNum() == null ? other.getModelNum() == null : this.getModelNum().equals(other.getModelNum()))
                && (this.getSkuName() == null ? other.getSkuName() == null : this.getSkuName().equals(other.getSkuName()))
                && (this.getUnit() == null ? other.getUnit() == null : this.getUnit().equals(other.getUnit()))
                && (this.getAttributeName() == null ? other.getAttributeName() == null : this.getAttributeName().equals(other.getAttributeName()))
                && (this.getMaterial() == null ? other.getMaterial() == null : this.getMaterial().equals(other.getMaterial()))
                && (this.getSpecification() == null ? other.getSpecification() == null : this.getSpecification().equals(other.getSpecification()))
                && (this.getStyle() == null ? other.getStyle() == null : this.getStyle().equals(other.getStyle()))
                && (this.getLinkUrl() == null ? other.getLinkUrl() == null : this.getLinkUrl().equals(other.getLinkUrl()))
                && (this.getCostPrice() == null ? other.getCostPrice() == null : this.getCostPrice().equals(other.getCostPrice()))
                && (this.getWeight() == null ? other.getWeight() == null : this.getWeight().equals(other.getWeight()))
                && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
                && (this.getPackageCount() == null ? other.getPackageCount() == null : this.getPackageCount().equals(other.getPackageCount()))
                && (this.getPerformanceOwner() == null ? other.getPerformanceOwner() == null : this.getPerformanceOwner().equals(other.getPerformanceOwner()))
                && (this.getPerformanceOwnerId() == null ? other.getPerformanceOwnerId() == null : this.getPerformanceOwnerId().equals(other.getPerformanceOwnerId()))
                && (this.getGoodsStatus() == null ? other.getGoodsStatus() == null : this.getGoodsStatus().equals(other.getGoodsStatus()))
                && (this.getModified() == null ? other.getModified() == null : this.getModified().equals(other.getModified()))
                && (this.getSelfOwnedBrand() == null ? other.getSelfOwnedBrand() == null : this.getSelfOwnedBrand().equals(other.getSelfOwnedBrand()))
                && (this.getDevDate() == null ? other.getDevDate() == null : this.getDevDate().equals(other.getDevDate()))
                && (this.getProductNo() == null ? other.getProductNo() == null : this.getProductNo().equals(other.getProductNo()))
                && (this.getMemo() == null ? other.getMemo() == null : this.getMemo().equals(other.getMemo()))
                && (this.getAliasCnName() == null ? other.getAliasCnName() == null : this.getAliasCnName().equals(other.getAliasCnName()))
                && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
                && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
                && (this.getPackfee() == null ? other.getPackfee() == null : this.getPackfee().equals(other.getPackfee()))
                && (this.getPriceFactorChanged() == null ? other.getPriceFactorChanged() == null : this.getPriceFactorChanged().equals(other.getPriceFactorChanged()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getSkuId() == null) ? 0 : getSkuId().hashCode());
        result = prime * result + ((getPyCategoryId() == null) ? 0 : getPyCategoryId().hashCode());
        result = prime * result + ((getCategoryCode() == null) ? 0 : getCategoryCode().hashCode());
        result = prime * result + ((getGoodsCode() == null) ? 0 : getGoodsCode().hashCode());
        result = prime * result + ((getSkuCode() == null) ? 0 : getSkuCode().hashCode());
        result = prime * result + ((getModelNum() == null) ? 0 : getModelNum().hashCode());
        result = prime * result + ((getSkuName() == null) ? 0 : getSkuName().hashCode());
        result = prime * result + ((getUnit() == null) ? 0 : getUnit().hashCode());
        result = prime * result + ((getAttributeName() == null) ? 0 : getAttributeName().hashCode());
        result = prime * result + ((getMaterial() == null) ? 0 : getMaterial().hashCode());
        result = prime * result + ((getSpecification() == null) ? 0 : getSpecification().hashCode());
        result = prime * result + ((getStyle() == null) ? 0 : getStyle().hashCode());
        result = prime * result + ((getLinkUrl() == null) ? 0 : getLinkUrl().hashCode());
        result = prime * result + ((getCostPrice() == null) ? 0 : getCostPrice().hashCode());
        result = prime * result + ((getWeight() == null) ? 0 : getWeight().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        result = prime * result + ((getPackageCount() == null) ? 0 : getPackageCount().hashCode());
        result = prime * result + ((getPerformanceOwner() == null) ? 0 : getPerformanceOwner().hashCode());
        result = prime * result + ((getPerformanceOwnerId() == null) ? 0 : getPerformanceOwnerId().hashCode());
        result = prime * result + ((getGoodsStatus() == null) ? 0 : getGoodsStatus().hashCode());
        result = prime * result + ((getModified() == null) ? 0 : getModified().hashCode());
        result = prime * result + ((getSelfOwnedBrand() == null) ? 0 : getSelfOwnedBrand().hashCode());
        result = prime * result + ((getDevDate() == null) ? 0 : getDevDate().hashCode());
        result = prime * result + ((getProductNo() == null) ? 0 : getProductNo().hashCode());
        result = prime * result + ((getMemo() == null) ? 0 : getMemo().hashCode());
        result = prime * result + ((getAliasCnName() == null) ? 0 : getAliasCnName().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getPackfee() == null) ? 0 : getPackfee().hashCode());
        result = prime * result + ((getPriceFactorChanged() == null) ? 0 : getPriceFactorChanged().hashCode());
        return result;
    }

    /**
     * This enum was generated by MyBatis Generator.
     * This enum corresponds to the database table base_sku
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    public enum Column {
        skuId("sku_id"),
        pyCategoryId("py_category_id"),
        categoryCode("category_code"),
        goodsCode("goods_code"),
        skuCode("sku_code"),
        modelNum("model_num"),
        skuName("sku_name"),
        unit("unit"),
        attributeName("attribute_name"),
        material("material"),
        specification("specification"),
        style("style"),
        linkUrl("link_url"),
        costPrice("cost_price"),
        weight("weight"),
        enabled("enabled"),
        packageCount("package_count"),
        performanceOwner("performance_owner"),
        performanceOwnerId("performance_owner_id"),
        goodsStatus("goods_status"),
        modified("modified"),
        selfOwnedBrand("self_owned_brand"),
        devDate("dev_date"),
        productNo("product_no"),
        memo("memo"),
        aliasCnName("alias_cn_name"),
        storeId("store_id"),
        createDate("create_date"),
        packfee("packfee"),
        priceFactorChanged("price_factor_changed");

        /**
         * This field was generated by MyBatis Generator.
         * This field corresponds to the database table base_sku
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        private final String column;

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table base_sku
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String value() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table base_sku
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String getValue() {
            return this.column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table base_sku
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        Column(String column) {
            this.column = column;
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table base_sku
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String desc() {
            return this.column + " DESC";
        }

        /**
         * This method was generated by MyBatis Generator.
         * This method corresponds to the database table base_sku
         *
         * @mbg.generated
         * @project https://github.com/itfsw/mybatis-generator-plugin
         */
        public String asc() {
            return this.column + " ASC";
        }
    }
}