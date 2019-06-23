package io.fulu.couponshop.shop;

public class ShopEntity {
    private long id;
    private String name;
    private long version;

    public ShopEntity() {

    }

    public ShopEntity(long id, String name) {
        this.id = id;
        this.name = name;
        this.version = 1;
    }

    public ShopEntity(long id, String name, long version) {
        this.id = id;
        this.name = name;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
