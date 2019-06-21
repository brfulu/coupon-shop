package io.fulu.couponshop.coupon;

import io.fulu.couponshop.database.DBConnection;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/coupons")
public class CouponController {
    private final CouponService couponService;

    public CouponController() {
        this.couponService = new CouponService();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coupon> getCoupons() {
        DBConnection.getConnnection();
        return couponService.getCoupons();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addCoupon(Coupon couponDto) {
        couponService.addCoupon(couponDto);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCoupon(@PathParam("id") int id) {
        return couponService.deleteCoupon(id);
    }
}
