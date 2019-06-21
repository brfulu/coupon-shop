package io.fulu.couponshop.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

    public List<Shop> getShops() {
        return ShopRepository.getShops();
    }
}
