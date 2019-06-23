package io.fulu.couponshop.coupon;

import io.fulu.couponshop.shop.Shop;
import io.fulu.couponshop.shop.ShopEntity;

import java.util.Date;

public class CouponEntity {
    private long id;
    private ShopEntity shop;
    private String product;
    private float discountPrice;
    private float originalPrice;
    private Date validFrom;
    private Date validTo;
    private long version;

    public CouponEntity() {

    }

    public CouponEntity(long id, ShopEntity shop, String product, float discountPrice, float originalPrice, Date validFrom, Date validTo) {
        this.id = id;
        this.shop = shop;
        this.product = product;
        this.discountPrice = discountPrice;
        this.originalPrice = originalPrice;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.version = 1;
    }

    public CouponEntity(long id, ShopEntity shop, String product, float discountPrice, float originalPrice, Date validFrom, Date validTo, long version) {
        this.id = id;
        this.shop = shop;
        this.product = product;
        this.discountPrice = discountPrice;
        this.originalPrice = originalPrice;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.version = version;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShopEntity getShop() {
        return shop;
    }

    public void setShop(ShopEntity shop) {
        this.shop = shop;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }

    public float getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(float originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }
}
