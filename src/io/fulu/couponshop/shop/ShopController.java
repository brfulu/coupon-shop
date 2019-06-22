package io.fulu.couponshop.shop;

import io.fulu.couponshop.coupon.Coupon;
import io.fulu.couponshop.security.AdminRoleNeeded;

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
    @AdminRoleNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> getShops() {
        return shopService.getShops();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Shop addShop(Shop shop) {
        return shopService.addShop(shop);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCoupon(@PathParam("id") int id) {
        return shopService.deleteShop(id);
    }
}
