package com.manhpd;


import com.manhpd.verticle.HelloVerticle;
import com.manhpd.verticle.HttpVerticle;
import com.manhpd.verticle.RestVerticle;
import io.vertx.core.Vertx;

public class App {
    public static void main( String[] args ) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
        vertx.deployVerticle(new HttpVerticle());
        vertx.deployVerticle(new RestVerticle());
    }
}
