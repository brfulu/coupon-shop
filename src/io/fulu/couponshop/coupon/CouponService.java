package io.fulu.couponshop.coupon;


import io.fulu.couponshop.pagination.PageInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponService {

    public List<Coupon> getCoupons(PageInfo pageInfo, boolean active) {
        List<Coupon> coupons = CouponRepository.getCoupons();

        List<Coupon> filteredCoupons = new ArrayList<>();
        for (Coupon coupon : coupons) {
            long now = (new Date()).getTime();
            long from = coupon.getValidFrom().getTime();
            long to = coupon.getValidTo() == null ? now + 5 : coupon.getValidTo().getTime();

            if (!active || (now >= from && now <= to)) {
                filteredCoupons.add(coupon);
            }
        }

        List<Coupon> couponsPage = new ArrayList<>();
        int start = Math.max(0, (pageInfo.getPage() - 1) * pageInfo.getSize());
        int end = Math.min(filteredCoupons.size(), start + pageInfo.getSize());
        for (int i = start; i < end; i++) {
            couponsPage.add(filteredCoupons.get(i));
        }

        return couponsPage;
    }

    public void addCoupon(Coupon coupon) {
        CouponRepository.addCoupon(coupon);
    }

    public boolean deleteCoupon(long id) {
        return CouponRepository.deleteCoupon(id);
    }
}
