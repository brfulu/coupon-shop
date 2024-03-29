package io.fulu.couponshop.coupon;

import io.fulu.couponshop.shop.Shop;

import java.io.Serializable;
import java.util.Date;

public class Coupon implements Serializable {
    private long id;
    private Shop shop;
    private String product;
    private float discountPrice;
    private float originalPrice;
    private Date validTo;
    private Date validFrom;
    private float discount;

    public Coupon() {

    }

    public Coupon(Shop shop, String product, float discountPrice, float originalPrice, Date validFrom, Date validTo) {
        this.shop = shop;
        this.product = product;
        this.discountPrice = discountPrice;
        this.originalPrice = originalPrice;
        this.validTo = validTo;
        this.validFrom = validFrom;
    }

    public Coupon(int id, Shop shop, String product, float discountPrice, float originalPrice, Date validFrom, Date validTo) {
        this.id = id;
        this.shop = shop;
        this.product = product;
        this.discountPrice = discountPrice;
        this.originalPrice = originalPrice;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
