package io.fulu.couponshop.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnnection() {
        Connection connection = null;
        try {
            String connectionURL = "jdbc:mysql://localhost:3306/coupon_shop_db";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(connectionURL, "root", "pass123");
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            e.getLocalizedMessage();
        }
        return connection;
    }

}
