package io.fulu.couponshop.coupon;

public class CouponDto {
    private long id;
    private String product;
    private double discountedPrice;
    private double originalPrice;
    private String shop;
    private double discountPercentage;

    public CouponDto() {

    }

    public CouponDto(Coupon coupon) {
        this.id = coupon.getId();
        this.product = coupon.getProduct();
        this.discountedPrice = coupon.getDiscountedPrice();
        this.originalPrice = coupon.getOriginalPrice();
        this.shop = coupon.getShop().getName();
        this.discountPercentage = (1.0 - discountedPrice / originalPrice) * 100;
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

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
