package io.fulu.couponshop.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopService {

    public List<ShopDto> getShops() {
        List<ShopDto> shopDtos = new ArrayList<>();
        List<Shop> shops = ShopRepository.getShops();
        for (Shop shop : shops) {
            ShopDto shopDto = new ShopDto(shop);
            shopDtos.add(shopDto);
        }
        return shopDtos;
    }
}
