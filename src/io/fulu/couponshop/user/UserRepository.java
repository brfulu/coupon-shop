package io.fulu.couponshop.user;

import io.fulu.couponshop.database.BasicConnectionPool;
import io.fulu.couponshop.database.DBConnection;
import io.fulu.couponshop.shop.ShopEntity;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static List<UserEntity> getUsers() {
        List<UserEntity> users = new ArrayList<>();
        Connection conn = BasicConnectionPool.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                UserRole role = UserRole.valueOf(rs.getString("role"));
                String username = rs.getString("username");
                String password = rs.getString("password");

                users.add(new UserEntity(id, firstName, lastName, role, username, password));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(conn);
        }
        return users;
    }

    public static UserEntity getUserByUsername(String username) {
        UserEntity user = null;
        Connection conn = BasicConnectionPool.getInstance().getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT  * FROM Users WHERE username = '" + username + "'");
            System.out.println("evo me");
            while (rs.next()) {
                System.out.println("usao");
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                UserRole role = UserRole.valueOf(rs.getString("role"));
                String password = rs.getString("password");

                user = new UserEntity(id, firstName, lastName, role, username, password);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(conn);
        }
        return user;
    }

    public static UserEntity addUser(UserEntity user) {
        Connection conn = BasicConnectionPool.getInstance().getConnection();
        try {
            String sql = "INSERT INTO Users (first_name, last_name, role, username, password) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getRole().toString());
            stmt.setString(4, user.getUsername());
            stmt.setString(5, DigestUtils.sha512Hex(user.getPassword()));

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BasicConnectionPool.getInstance().releaseConnection(conn);
        }
        return user;
    }
}
