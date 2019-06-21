package io.fulu.couponshop.coupon;


import java.util.ArrayList;
import java.util.List;

public class CouponService {

    public List<Coupon> getCoupons() {
        return CouponRepository.getCoupons();
    }

    public void addCoupon(Coupon coupon) {
        CouponRepository.addCoupon(coupon);
    }

    public boolean deleteCoupon(long id) {
        return CouponRepository.deleteCoupon(id);
    }
}
