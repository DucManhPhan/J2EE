package com.manhpd;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class VerticleDeployHelper extends AbstractVerticle {

    public Future<Void> deployHelper(String name){
        final Future<Void> future = Future.future();
        this.vertx.deployVerticle(name, res -> {
            if(res.failed()){
                System.out.println("Failed to deploy verticle " + name);
                future.fail(res.cause());
            } else {
                System.out.println("Deployed verticle " + name);
                future.complete();
            }
        });

        return future;
    }

}
