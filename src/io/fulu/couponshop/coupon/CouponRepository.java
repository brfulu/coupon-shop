package io.fulu.couponshop.coupon;

import io.fulu.couponshop.shop.Shop;
import io.fulu.couponshop.shop.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class CouponRepository {
    private static List<Coupon> COUPONS;
    private static int ID_COUNTER = 2;

    static {
        COUPONS = generateCoupons();
    }

    private static List<Coupon> generateCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        return coupons;
    }

    public synchronized static List<Coupon> getCoupons() {
        return COUPONS;
    }

    public synchronized static Coupon addCoupon(Coupon coupon) {
        coupon.setId(++ID_COUNTER);
        String shopName = coupon.getShop().getName();

        Shop shop = ShopRepository.getShopByName(shopName);
        coupon.setShop(shop);

        COUPONS.add(coupon);

        return coupon;
    }

    public static boolean deleteCoupon(long id) {
        return COUPONS.removeIf(coupon -> coupon.getId() == id);
    }
}
