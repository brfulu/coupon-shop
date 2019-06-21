package io.fulu.couponshop.shop;

public class ShopDto {
    private long id;
    private String name;

    public ShopDto() {

    }

    public ShopDto(Shop shop) {
        this.id = shop.getId();
        this.name = shop.getName();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
