package io.fulu.couponshop.coupon;

import com.mysql.cj.xdevapi.Result;
import io.fulu.couponshop.database.DBConnection;
import io.fulu.couponshop.shop.Shop;
import io.fulu.couponshop.shop.ShopRepository;

import javax.validation.constraints.Null;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponRepository {

    public synchronized static List<Coupon> getCoupons() {
        List<Coupon> coupons = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Coupons");
            coupons = convertToCouponsList(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupons;
    }

    public synchronized static Coupon addCoupon(Coupon coupon) {
        try {
            Connection conn = DBConnection.getConnnection();
            String sql = "INSERT INTO Coupons (shop_id, product, discount_price, original_price, valid_from, valid_to)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, coupon.getShop().getId());
            stmt.setString(2, coupon.getProduct());
            stmt.setFloat(3, coupon.getDiscountPrice());
            stmt.setFloat(4, coupon.getOriginalPrice());
            stmt.setDate(5, new java.sql.Date(coupon.getValidFrom().getTime()));
            try {
                stmt.setDate(6, new java.sql.Date(coupon.getValidTo().getTime()));
            } catch (NullPointerException e) {
                stmt.setDate(6, null);
            }
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupon;
    }

    public static boolean deleteCoupon(long id) {
        int affectedRows = 0;
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            affectedRows = stmt.executeUpdate("DELETE FROM Coupons WHERE id = " + id);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows == 1;
    }

    public static List<Coupon> getCouponsByShopId(int shopId) {
        List<Coupon> coupons = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Coupons WHERE shop_id = " + shopId);
            coupons = convertToCouponsList(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupons;
    }

    private static List<Coupon> convertToCouponsList(ResultSet rs) throws SQLException {
        List<Coupon> coupons = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            int shopId = rs.getInt("shop_id");
            String product = rs.getString("product");
            float discountPrice = rs.getFloat("discount_price");
            float originalPrice = rs.getFloat("original_price");
            Date validFrom = rs.getDate("valid_from");
            Date validTo = rs.getDate("valid_to");

            Shop shop = ShopRepository.getShopById(shopId);

            coupons.add(new Coupon(id, shop, product, discountPrice, originalPrice, validFrom, validTo));
        }
        return coupons;
    }
}
