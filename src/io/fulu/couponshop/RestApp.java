package io.fulu.couponshop;

import io.fulu.couponshop.coupon.Coupon;
import io.fulu.couponshop.coupon.CouponService;
import io.fulu.couponshop.database.DBConnection;
import io.fulu.couponshop.filter.CorsFilter;
import io.fulu.couponshop.shop.Shop;
import io.fulu.couponshop.shop.ShopService;
import io.fulu.couponshop.user.User;
import io.fulu.couponshop.user.UserService;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationPath("/api")
public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("io.fulu.couponshop.coupon",
                "io.fulu.couponshop.shop",
                "io.fulu.couponshop.user",
                "io.fulu.couponshop.filter",
                "io.fulu.couponshop.security");
//        getContainerResponseFilters().add(new CORSFilter());

//        register(CorsFilter.class);
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

            ShopService shopService = new ShopService();
            Shop maxi = new Shop("Maxi");
            maxi = shopService.addShop(maxi);
            Shop lidl = new Shop("Lidl");
            lidl = shopService.addShop(lidl);
            Shop shootiranje = new Shop("Shootiranje");
            shootiranje = shopService.addShop(shootiranje);
            Shop euromedik = new Shop("Euromedik");
            euromedik = shopService.addShop(euromedik);

            Date todat = new Date();

            CouponService couponService = new CouponService();
            Coupon coupon1 = new Coupon(maxi, "Riza", 105.4f, 230f, new Date(), parseDate("2019-06-26"));
            couponService.addCoupon(coupon1);
            Coupon coupon2 = new Coupon(lidl, "Pile", 234.4f, 350f, parseDate("2019-05-07"), new Date());
            couponService.addCoupon(coupon2);
            Coupon coupon3 = new Coupon(shootiranje, "Shot", 150.5f, 254.5f, parseDate("2019-06-22"), parseDate("2019-06-25"));
            couponService.addCoupon(coupon3);
            Coupon coupon4 = new Coupon(euromedik, "Pregled", 1150.5f, 2540.5f, new Date(), null);
            couponService.addCoupon(coupon4);
            Coupon coupon5 = new Coupon(maxi, "Milka", 125f, 148f, new Date(), new Date());
            couponService.addCoupon(coupon5);
            Coupon coupon6 = new Coupon(euromedik, "Masaza", 800.25f, 900f, parseDate("2019-04-27"), parseDate("2019-05-29"));
            couponService.addCoupon(coupon6);


            UserService userService = new UserService();
            User admin = new User("Branko", "Fulurija", "ADMIN", "fulu", "sifra");
            userService.register(admin);
            User operator = new User("Ana", "Joksimovic", "OPERATOR", "ana", "abc");
            userService.register(operator);
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
                + "   version         INT default 1 NOT NULL,"
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
                + "   version         INT default 1 NOT NULL,"
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

    private static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}
