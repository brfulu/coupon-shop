package io.fulu.couponshop.coupon;

import io.fulu.couponshop.database.DBConnection;
import io.fulu.couponshop.shop.ShopEntity;
import io.fulu.couponshop.shop.ShopRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponRepository {

    public synchronized static List<CouponEntity> getCoupons() {
        List<CouponEntity> coupons = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Coupons");
            coupons = convertToCouponList(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupons;
    }

    public synchronized static CouponEntity addCoupon(CouponEntity coupon) {
        try {
            Connection conn = DBConnection.getConnnection();
            String sql = "INSERT INTO Coupons (shop_id, product, discount_price, original_price, valid_from, valid_to)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, coupon.getShop().getId());
            stmt.setString(2, coupon.getProduct());
            stmt.setFloat(3, coupon.getDiscountPrice());
            stmt.setFloat(4, coupon.getOriginalPrice());
            stmt.setDate(5, new java.sql.Date(coupon.getValidFrom().getTime()));
            try {
                stmt.setDate(6, new java.sql.Date(coupon.getValidTo().getTime()));
            } catch (NullPointerException e) {
                stmt.setDate(6, null);
            }

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating coupon failed, no rows affected.");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                coupon.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating coupon failed, no ID obtained.");
            }

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

    public static List<CouponEntity> getCouponsByShopId(int shopId) {
        List<CouponEntity> coupons = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Coupons WHERE shop_id = " + shopId);
            coupons = convertToCouponList(rs);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coupons;
    }

    private static List<CouponEntity> convertToCouponList(ResultSet rs) throws SQLException {
        List<CouponEntity> coupons = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            int shopId = rs.getInt("shop_id");
            String product = rs.getString("product");
            float discountPrice = rs.getFloat("discount_price");
            float originalPrice = rs.getFloat("original_price");
            Date validFrom = rs.getDate("valid_from");
            Date validTo = rs.getDate("valid_to");

            ShopEntity shop = ShopRepository.getShopById(shopId);

            coupons.add(new CouponEntity(id, shop, product, discountPrice, originalPrice, validFrom, validTo));
        }
        return coupons;
    }
}
