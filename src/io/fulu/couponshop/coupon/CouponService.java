package io.fulu.couponshop.coupon;


import java.util.ArrayList;
import java.util.List;

public class CouponService {

    public List<CouponDto> getCoupons() {
        List<CouponDto> couponDtos = new ArrayList<>();
        List<Coupon> coupons = CouponRepository.getCoupons();

        for (Coupon coupon : coupons) {
            CouponDto couponDto = new CouponDto(coupon);
            couponDtos.add(couponDto);
        }

        return couponDtos;
    }

    public CouponDto addCoupon(CouponDto couponDto) {
        Coupon coupon = new Coupon(couponDto);

        coupon = CouponRepository.addCoupon(coupon);
        couponDto.setId(coupon.getId());
        couponDto.setShop(coupon.getShop().getName());

        return couponDto;
    }

    public boolean deleteCoupon(long id) {
        return CouponRepository.deleteCoupon(id);
    }
}
