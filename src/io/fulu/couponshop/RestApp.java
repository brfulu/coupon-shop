package io.fulu.couponshop;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class RestApp extends ResourceConfig {

    public RestApp() {
         packages("io.fulu.couponshop.coupon", "io.fulu.couponshop.shop");
    }
}
