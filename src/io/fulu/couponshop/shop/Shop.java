package io.fulu.couponshop.shop;

import java.io.Serializable;

public class Shop implements Serializable {
    private long id;
    private String name;

    public Shop() {

    }

    public Shop(String name) {
        this.name = name;
    }

    public Shop(int id, String name) {
        this.id = id;
        this.name = name;
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
