package io.fulu.couponshop.coupon;

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
    public List<CouponDto> getCoupons() {
        return couponService.getCoupons();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CouponDto addCoupon(CouponDto couponDto) {
        return couponService.addCoupon(couponDto);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCoupon(@PathParam("id") int id) {
        return couponService.deleteCoupon(id);
    }
}
