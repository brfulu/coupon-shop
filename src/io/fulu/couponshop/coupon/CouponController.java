package io.fulu.couponshop.coupon;

import io.fulu.couponshop.pagination.PageInfo;
import io.fulu.couponshop.pagination.PaginationResponse;
import io.fulu.couponshop.security.JWTTokenNeeded;
import io.fulu.couponshop.shop.Shop;

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
    @JWTTokenNeeded
    @Produces(MediaType.APPLICATION_JSON)
    public PaginationResponse getCoupons(@QueryParam("page") int page,
                                         @DefaultValue("1000") @QueryParam("size") int size,
                                         @QueryParam("active") boolean active) {
        PageInfo pageInfo = new PageInfo(page, size);
        System.out.println(active);
        List<Coupon> coupons = couponService.getCoupons(pageInfo, active);
        return new PaginationResponse(pageInfo, coupons);
    }

    @POST
    @JWTTokenNeeded
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Coupon addCoupon(Coupon coupon) {
       return couponService.addCoupon(coupon);
    }

    @DELETE
    @JWTTokenNeeded
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCoupon(@PathParam("id") int id) {
        return couponService.deleteCoupon(id);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Coupon updateCoupon(@PathParam("id") int id, Coupon coupon) {
        return couponService.updateCoupon(id, coupon);
    }
}
