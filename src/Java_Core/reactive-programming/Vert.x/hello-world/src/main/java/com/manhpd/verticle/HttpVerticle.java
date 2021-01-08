package com.manhpd.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class HttpVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {
        vertx.createHttpServer()
             .requestHandler(r -> r.response().end("Welcome to Vert.x Intro"))
             .listen(this.config().getInteger("http.port", 9090), result -> {
            if (result.succeeded()) {
                future.complete();
            } else {
                future.fail(result.cause());
            }
        });
    }

}
