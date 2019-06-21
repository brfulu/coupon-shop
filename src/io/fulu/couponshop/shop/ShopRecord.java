package io.fulu.couponshop.shop;

public class ShopRecord {
    private long id;
    private String name;

    public ShopRecord(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
