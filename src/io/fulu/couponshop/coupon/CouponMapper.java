package io.fulu.couponshop.coupon;

import io.fulu.couponshop.shop.ShopMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CouponMapper {

    public static CouponEntity mapToEntity(Coupon coupon) {
        if (coupon == null) {
            return null;
        }

        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setId(coupon.getId());
        couponEntity.setProduct(coupon.getProduct());
        couponEntity.setDiscountPrice(coupon.getDiscountPrice());
        couponEntity.setOriginalPrice(coupon.getOriginalPrice());
        couponEntity.setValidFrom(coupon.getValidFrom());
        couponEntity.setValidTo(coupon.getValidTo());

        return couponEntity;
    }

    public static Coupon mapToModel(CouponEntity couponEntity) {
        if (couponEntity == null) {
            return null;
        }

        Coupon coupon = new Coupon();
        coupon.setId(couponEntity.getId());
        coupon.setProduct(couponEntity.getProduct());
        coupon.setDiscountPrice(couponEntity.getDiscountPrice());
        coupon.setOriginalPrice(couponEntity.getOriginalPrice());
        coupon.setShop(ShopMapper.mapToModel(couponEntity.getShop()));
        coupon.setValidFrom(couponEntity.getValidFrom());
        coupon.setValidTo(couponEntity.getValidTo());
        coupon.setDiscount(calculateDiscount(couponEntity.getOriginalPrice(), couponEntity.getDiscountPrice()));

        return coupon;
    }


    private static float calculateDiscount(float originalPrice, float discountPrice) {
        if (discountPrice == originalPrice) {
            return 0f;
        }

        if (discountPrice == 0) {
            return 100f;
        }

        return (100 - (discountPrice / originalPrice * 100));
    }

    public static List<Coupon> mapToModelList(List<CouponEntity> couponEntities) {
        if (couponEntities == null) {
            return null;
        }
        return couponEntities.stream().map(CouponMapper::mapToModel).collect(Collectors.toList());
    }
}
