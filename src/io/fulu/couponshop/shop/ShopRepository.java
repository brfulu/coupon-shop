package io.fulu.couponshop.shop;

import io.fulu.couponshop.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShopRepository {

    private static List<ShopEntity> generateShops() {
        List<ShopEntity> shops = new ArrayList<>();
        shops.add(new ShopEntity(1, "Maxi"));
        shops.add(new ShopEntity(2, "Lidl"));
        return shops;
    }

    public synchronized static List<ShopEntity> getShops() {
        List<ShopEntity> shops = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Shops");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                shops.add(new ShopEntity(id, name));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shops;
    }

    public synchronized static ShopEntity getShopById(long id) {
        ShopEntity shop = null;
        try {
            Connection conn = DBConnection.getConnnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  * FROM Shops WHERE id = " + id);
            while (rs.next()) {
                String name = rs.getString("name");
                long version = rs.getLong("version");
                shop = new ShopEntity(id, name, version);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }

    public static ShopEntity addShop(ShopEntity shop) {
        try {
            Connection conn = DBConnection.getConnnection();
            String sql = "INSERT INTO Shops (name) VALUES(?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, shop.getName());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating shop failed, no rows affected.");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                shop.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating shop failed, no ID obtained.");
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
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

    public static ShopEntity updateShop(int id, ShopEntity updatedShop) {
        ShopEntity shop = getShopById(id);
        if (updatedShop.getName() != null) {
            shop.setName(updatedShop.getName());
        }
        try {
            Connection conn = DBConnection.getConnnection();
            String sql = "UPDATE Shops SET name=?, version=? WHERE id=? and version=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, shop.getName());
            stmt.setLong(2, shop.getVersion() + 1);
            stmt.setLong(3, id);
            stmt.setLong(4, shop.getVersion());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating shop failed, no rows affected.");
            }
            shop.setVersion(shop.getVersion() + 1);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }
}
