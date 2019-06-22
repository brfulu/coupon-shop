package io.fulu.couponshop.shop;

import com.mysql.cj.xdevapi.Result;
import io.fulu.couponshop.coupon.Coupon;
import io.fulu.couponshop.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopRepository {

    private static List<Shop> generateShops() {
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop(1, "Maxi"));
        shops.add(new Shop(2, "Lidl"));
        return shops;
    }

    public synchronized static List<Shop> getShops() {
        List<Shop> shops = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Shops");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                shops.add(new Shop(id, name));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shops;
    }

    public synchronized static Shop getShopById(int id) {
        Shop shop = null;
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  * FROM Shops WHERE id = " + id);
            while (rs.next()) {
                String name = rs.getString("name");
                shop = new Shop(id, name);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }

    public static void addShop(Shop shop) {
        try {
            Connection conn = DBConnection.getConnnection();
            String sql = "INSERT INTO Shops (name) VALUES(?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, shop.getName());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean deleteShop(int id) {
        int affectedRows = 0;
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            affectedRows = stmt.executeUpdate("DELETE FROM Shops WHERE id = " + id);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return affectedRows == 1;
    }
}
