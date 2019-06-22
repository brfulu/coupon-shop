package io.fulu.couponshop;

import io.fulu.couponshop.database.DBConnection;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

@ApplicationPath("/api")
public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("io.fulu.couponshop.coupon", "io.fulu.couponshop.shop");
        initDB();
    }

    private void initDB() {
        try {
            deleteTable("Coupons");
            deleteTable("Shops");
            deleteTable("Users");

            createShopsTable();
            createCouponsTable();
            createUsersTable();

            addShop("Maxi");
            addShop("Lidl");
            addCoupons(1, "Riza", 105.4, 230, new Date(), new Date());
            addUsers("Branko", "Fulurija", "ADMIN", "fulu", "sifra");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteTable(String name) throws SQLException {
        Connection conn = DBConnection.getConnnection();

        String sql = "DROP TABLE IF EXISTS " + name;

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    private void createShopsTable() throws SQLException {
        Connection conn = DBConnection.getConnnection();

        String sql = "CREATE TABLE Shops"
                + "  (id              INT AUTO_INCREMENT NOT NULL,"
                + "   name            VARCHAR(255) NOT NULL,"
                + "   PRIMARY KEY (id))";

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    private void createCouponsTable() throws SQLException {
        Connection conn = DBConnection.getConnnection();

        String sql = "CREATE TABLE IF NOT EXISTS Coupons"
                + "  (id              INT AUTO_INCREMENT NOT NULL,"
                + "   shop_id         INT NOT NULL,"
                + "   product         VARCHAR(255) NOT NULL,"
                + "   discount_price  FLOAT NOT NULL,"
                + "   original_price  FLOAT NOT NULL,"
                + "   valid_from      DATE NOT NULL,"
                + "   valid_to        DATE,"
                + "   PRIMARY KEY (id),"
                + "   FOREIGN KEY (shop_id) REFERENCES Shops(id))";

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    private void createUsersTable() throws SQLException {
        Connection conn = DBConnection.getConnnection();

        String sql = "CREATE TABLE IF NOT EXISTS Users"
                + "  (id              INT AUTO_INCREMENT NOT NULL,"
                + "   first_name      VARCHAR(255) NOT NULL,"
                + "   last_name       VARCHAR(255) NOT NULL,"
                + "   role            ENUM('OPERATOR', 'ADMIN') NOT NULL,"
                + "   username        VARCHAR(255) NOT NULL,"
                + "   password        VARCHAR(255) NOT NULL,"
                + "   PRIMARY KEY (id))";

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
        stmt.close();
    }

    private void addShop(String name) throws SQLException {
        Connection conn = DBConnection.getConnnection();
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO Shops (name) VALUES('" + name + "')");
    }

    private void addCoupons(int shopId, String product, double discountPrice,
                            double originalPrice, Date validFrom, Date validTo) throws SQLException {
        Connection conn = DBConnection.getConnnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Coupons"
                + " (shop_id, product, discount_price, original_price, valid_from, valid_to) "
                + " VALUES(?, ?, ?, ?, ?, ?)");

        stmt.setInt(1, shopId);
        stmt.setString(2, product);
        stmt.setFloat(3, (float) discountPrice);
        stmt.setFloat(4, (float) originalPrice);
        stmt.setDate(5, new java.sql.Date(validFrom.getTime()));
        stmt.setDate(6, new java.sql.Date(validTo.getTime()));

        stmt.execute();
        stmt.close();
    }

    private void addUsers(String firstName, String lastName, String role, String username, String password) throws SQLException {
        Connection conn = DBConnection.getConnnection();
        Statement stmt = conn.createStatement();
        stmt.execute("INSERT INTO Users (first_name, last_name, role, username, password)"
                + " VALUES('" + firstName + "','" + lastName + "','" + role + "','" + username + "','" + password + "')");
        stmt.close();
    }
}
