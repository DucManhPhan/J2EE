package com.manhpd;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class SenderVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {
        final Router router = Router.router(vertx);
        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("Content-Type", "text/html")
                    .end("<h1>Hello from non-clustered messager example!</h1>");
        });
        
        router.post("/send/:message").handler(this::sendMessage);

        this.vertx.createHttpServer()
                  .requestHandler(router::accept)
                  .listen(config().getInteger("http.server.port", 8080), result -> {
                        if (result.succeeded()) {
                            System.out.println("Http server running on port " + 8080);
                            future.complete();
                        } else {
                            System.out.println("Could not start a Http server");
                            future.fail(result.cause());
                        }
                  });
    }

    private void sendMessage(RoutingContext routingContext) {
        final EventBus eventBus = this.vertx.eventBus();
        final String message = routingContext.request().getParam(Constant.PATH_PARAM);

        eventBus.send(Constant.ADDRESS, message, reply -> {
            if (reply.succeeded()) {
                System.out.println("Received reply: " + reply.result().body());
            } else {
                System.out.println("No reply");
            }
        });

        routingContext.response().end(message);
    }

}
