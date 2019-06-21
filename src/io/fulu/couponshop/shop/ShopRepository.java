package io.fulu.couponshop.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopRepository {
    private static List<ShopRecord> SHOP_RECORDS;

    static {
        SHOP_RECORDS = fillShopRecords();
    }

    private static List<ShopRecord> fillShopRecords() {
        List<ShopRecord> shopRecords = new ArrayList<>();
        shopRecords.add(new ShopRecord(1, "Maxi"));
        shopRecords.add(new ShopRecord(2, "Shootiranje"));
        shopRecords.add(new ShopRecord(3, "Euromedik"));
        shopRecords.add(new ShopRecord(4, "Konoba Akustik"));
        shopRecords.add(new ShopRecord(5, "Gigatron"));
        shopRecords.add(new ShopRecord(6, "Idea"));
        shopRecords.add(new ShopRecord(7, "Spa & Wellness"));
        shopRecords.add(new ShopRecord(8, "Burrito Madre"));
        shopRecords.add(new ShopRecord(9, "Jysk"));
        shopRecords.add(new ShopRecord(10, "Lidl"));
        return shopRecords;
    }

    public synchronized static List<Shop> getShops() {
        List<Shop> shops = new ArrayList<>();
        for (ShopRecord record : SHOP_RECORDS) {
            shops.add(new Shop(record.getId(), record.getName()));
        }
        return shops;
    }

    public synchronized static Shop getShopById(long id) {
        for (ShopRecord record : SHOP_RECORDS) {
            if (record.getId() == id) {
                return new Shop(record.getId(), record.getName());
            }
        }
        return null;
    }

    public synchronized static Shop getShopByName(String name) {
        for (ShopRecord record : SHOP_RECORDS) {
            if (record.getName().equals(name)) {
                return new Shop(record.getId(), record.getName());
            }
        }
        return null;
    }

}
