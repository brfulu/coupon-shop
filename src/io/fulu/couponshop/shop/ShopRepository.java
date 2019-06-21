package io.fulu.couponshop.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopRepository {
    private static List<Shop> SHOPS;

    static {
        SHOPS = generateShops();
    }

    private static List<Shop> generateShops() {
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(1, "Maxi"));
        shops.add(new Shop(2, "Lidl"));
        return shops;
    }

    public synchronized static List<Shop> getShops() {
        return SHOPS;
    }

    public synchronized static Shop getShopById(long id) {
        for (Shop shop : SHOPS) {
            if (shop.getId() == id) {
                return shop;
            }
        }
        return null;
    }

    public synchronized static Shop getShopByName(String name) {
        for (Shop shop : SHOPS) {
            if (shop.getName().equals(name)) {
                return shop;
            }
        }
        return null;
    }

}
