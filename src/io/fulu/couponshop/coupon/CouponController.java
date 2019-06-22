package io.fulu.couponshop.coupon;

import io.fulu.couponshop.database.DBConnection;
import io.fulu.couponshop.pagination.PageInfo;
import io.fulu.couponshop.pagination.PaginationResponse;

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
    public PaginationResponse getCoupons(@QueryParam("page") int page,
                                         @QueryParam("size") int size,
                                         @QueryParam("active") boolean active) {
        PageInfo pageInfo = new PageInfo(page, size);
        System.out.println(active);
        List<Coupon> coupons = couponService.getCoupons(pageInfo, active);
        return new PaginationResponse(pageInfo, coupons);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void addCoupon(Coupon coupon) {
        couponService.addCoupon(coupon);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCoupon(@PathParam("id") int id) {
        return couponService.deleteCoupon(id);
    }
}
