package io.fulu.couponshop.shop;

import io.fulu.couponshop.coupon.Coupon;
import io.fulu.couponshop.coupon.CouponRepository;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

    public List<Shop> getShops() {
        return ShopRepository.getShops();
    }

    public void addCoupon(Shop shop) {
        ShopRepository.addShop(shop);
    }

    public boolean deleteShop(int id) {
        List<Coupon> coupons = CouponRepository.getCouponsByShopId(id);
        for (Coupon coupon : coupons) {
            CouponRepository.deleteCoupon(coupon.getId());
        }
        return ShopRepository.deleteShop(id);
    }
}
