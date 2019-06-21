package io.fulu.couponshop.shop;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/shops")
public class ShopController {
    private final ShopService shopService;

    public ShopController() {
        this.shopService = new ShopService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> getShops() {
        return shopService.getShops();
    }

}
