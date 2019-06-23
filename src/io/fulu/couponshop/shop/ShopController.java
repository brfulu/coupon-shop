package io.fulu.couponshop.shop;

import io.fulu.couponshop.coupon.Coupon;
import io.fulu.couponshop.security.AdminRoleNeeded;
import io.fulu.couponshop.security.JWTTokenNeeded;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/shops")
public class ShopController {
    private final ShopService shopService;

    public ShopController() {
        this.shopService = new ShopService();
    }

    @GET
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> getShops() {
        return shopService.getShops();
    }

    @POST
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Shop addShop(Shop shop) {
        return shopService.addShop(shop);
    }

    @DELETE
    @JWTTokenNeeded
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteShop(@PathParam("id") int id) {
        return shopService.deleteShop(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shop updateShop(@PathParam("id") int id, Shop shop) {
        return shopService.updateShop(id, shop);
    }
}
