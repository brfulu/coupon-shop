package io.fulu.couponshop.coupon;

import io.fulu.couponshop.shop.Shop;
import io.fulu.couponshop.shop.ShopRepository;

import java.util.ArrayList;
import java.util.List;

public class CouponRepository {
    private static List<CouponRecord> COUPON_RECORDS;
    private static int ID_COUNTER = 2;

    static {
        COUPON_RECORDS = fillCouponRecords();
    }

    private static List<CouponRecord> fillCouponRecords() {
        List<CouponRecord> couponRecords = new ArrayList<>();
        couponRecords.add(new CouponRecord(1, "Tablet", 150.75, 190.0, 5));
        couponRecords.add(new CouponRecord(2, "Pile", 10.3, 20.0, 10));
        return couponRecords;
    }

    public synchronized static List<Coupon> getCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        for (CouponRecord couponRecord : COUPON_RECORDS) {
            Coupon coupon = new Coupon(couponRecord);

            Shop shop = ShopRepository.getShopById(couponRecord.getShopId());
            coupon.setShop(shop);

            coupons.add(coupon);
        }

        return coupons;
    }

    public synchronized static Coupon addCoupon(Coupon coupon) {
        coupon.setId(++ID_COUNTER);
        String shopName = coupon.getShop().getName();

        Shop shop = ShopRepository.getShopByName(shopName);
        coupon.setShop(shop);

        CouponRecord couponRecord = new CouponRecord(coupon.getId(), coupon.getProduct(), coupon.getDiscountedPrice(),
                coupon.getOriginalPrice(), coupon.getShop().getId());

        COUPON_RECORDS.add(couponRecord);

        return coupon;
    }

    public static boolean deleteCoupon(long id) {
        return COUPON_RECORDS.removeIf(record -> record.getId() == id);
    }
}
