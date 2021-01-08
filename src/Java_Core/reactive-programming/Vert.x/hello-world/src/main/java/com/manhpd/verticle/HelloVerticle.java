package com.manhpd.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloVerticle extends AbstractVerticle {

    private final Logger logger = LoggerFactory.getLogger(HelloVerticle.class);

    @Override
    public void start(Future<Void> future) {
        System.out.println( "Hello World!" );
    }

    @Override
    public void stop(Future<Void> Future) {
        System.out.println("Stop our verticle");
    }

}
