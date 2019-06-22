package io.fulu.couponshop.coupon;


import io.fulu.couponshop.pagination.PageInfo;
import io.fulu.couponshop.shop.ShopEntity;
import io.fulu.couponshop.shop.ShopRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponService {

    public List<Coupon> getCoupons(PageInfo pageInfo, boolean active) {
        List<Coupon> coupons = CouponMapper.mapToModelList(CouponRepository.getCoupons());

        List<Coupon> filteredCoupons = getFilteredCoupons(coupons, active);
        List<Coupon> couponsPage = getCouponsPage(filteredCoupons, pageInfo);

        return couponsPage;
    }

    private List<Coupon> getFilteredCoupons(List<Coupon> coupons, boolean active) {
        List<Coupon> filteredCoupons = new ArrayList<>();
        for (Coupon coupon : coupons) {
            long now = (new Date()).getTime();
            long from = coupon.getValidFrom().getTime();
            long to = coupon.getValidTo() == null ? now + 5 : coupon.getValidTo().getTime();

            if (!active || (now >= from && now <= to)) {
                filteredCoupons.add(coupon);
            }
        }
        return filteredCoupons;
    }

    private List<Coupon> getCouponsPage(List<Coupon> coupons, PageInfo pageInfo) {
        List<Coupon> couponsPage = new ArrayList<>();
        int start = Math.max(0, (pageInfo.getPage() - 1) * pageInfo.getSize());
        int end = Math.min(coupons.size(), start + pageInfo.getSize());
        for (int i = start; i < end; i++) {
            couponsPage.add(coupons.get(i));
        }
        return couponsPage;
    }

    public Coupon addCoupon(Coupon coupon) {
        CouponEntity couponEntity = CouponMapper.mapToEntity(coupon);

        ShopEntity shopEntity = ShopRepository.getShopById(coupon.getShop().getId());
        couponEntity.setShop(shopEntity);

        couponEntity = CouponRepository.addCoupon(couponEntity);
        return CouponMapper.mapToModel(couponEntity);
    }

    public boolean deleteCoupon(long id) {
        return CouponRepository.deleteCoupon(id);
    }
}
