package io.fulu.couponshop.shop;

import java.util.List;
import java.util.stream.Collectors;

public class ShopMapper {
    public static ShopEntity mapToEntity(Shop shop) {
        if (shop == null) {
            return null;
        }

        ShopEntity shopEntity = new ShopEntity();
        shopEntity.setId(shop.getId());
        shopEntity.setName(shop.getName());
        return shopEntity;
    }

    public static Shop mapToModel(ShopEntity shopEntity) {
        if (shopEntity == null) {
            return null;
        }

        Shop shop = new Shop();
        shop.setId(shopEntity.getId());
        shop.setName(shopEntity.getName());
        return shop;
    }

    public static List<Shop> mapToModelList(List<ShopEntity> shopEntities) {
        return shopEntities.stream().map(ShopMapper::mapToModel).collect(Collectors.toList());
    }
}
