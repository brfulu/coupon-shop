package io.fulu.couponshop.coupon;

public class CouponRecord {
    private long id;
    private String product;
    private double discountedPrice;
    private double originalPrice;
    private long shopId;

    public CouponRecord(long id, String product, double discountedPrice, double originalPrice, long shopId) {
        this.id = id;
        this.product = product;
        this.discountedPrice = discountedPrice;
        this.originalPrice = originalPrice;
        this.shopId = shopId;
    }

    public long getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public long getShopId() {
        return shopId;
    }
}
