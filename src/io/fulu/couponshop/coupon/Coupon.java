package io.fulu.couponshop.coupon;

import io.fulu.couponshop.shop.Shop;

public class Coupon {
    private long id;
    private String product;
    private double discountedPrice;
    private double originalPrice;
    private Shop shop;

    public Coupon(CouponDto couponDto) {
        this.product = couponDto.getProduct();
        this.discountedPrice = couponDto.getDiscountedPrice();
        this.originalPrice = couponDto.getOriginalPrice();
        this.shop = new Shop();
        this.shop.setName(couponDto.getShop());
    }

    public Coupon(CouponRecord couponRecord) {
        this.id = couponRecord.getId();
        this.product = couponRecord.getProduct();
        this.discountedPrice = couponRecord.getDiscountedPrice();
        this.originalPrice = couponRecord.getOriginalPrice();
    }

    public Coupon() {

    }

    public Coupon(long id) {
        this.id = id;
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

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
}
