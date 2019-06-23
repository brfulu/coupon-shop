package io.fulu.couponshop.shop;

import io.fulu.couponshop.coupon.Coupon;
import io.fulu.couponshop.coupon.CouponEntity;
import io.fulu.couponshop.coupon.CouponMapper;
import io.fulu.couponshop.coupon.CouponRepository;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

    public List<Shop> getShops() {
        return ShopMapper.mapToModelList(ShopRepository.getShops());
    }

    public Shop addShop(Shop shop) {
        ShopEntity shopEntity = ShopRepository.addShop(ShopMapper.mapToEntity(shop));
        return ShopMapper.mapToModel(shopEntity);
    }

    public boolean deleteShop(int id) {
        List<CouponEntity> coupons = CouponRepository.getCouponsByShopId(id);
        for (CouponEntity coupon : coupons) {
            CouponRepository.deleteCoupon(coupon.getId());
        }
        return ShopRepository.deleteShop(id);
    }

    public Shop updateShop(int id, Shop shop) {
        ShopEntity shopEntity = ShopRepository.updateShop(id, ShopMapper.mapToEntity(shop));
        return ShopMapper.mapToModel(shopEntity);
    }
}
