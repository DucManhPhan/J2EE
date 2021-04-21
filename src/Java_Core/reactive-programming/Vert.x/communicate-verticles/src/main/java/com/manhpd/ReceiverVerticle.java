package com.manhpd;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.EventBus;

public class ReceiverVerticle extends AbstractVerticle {

    @Override
    public void start(Future<Void> future) {
        final EventBus eventBus = this.vertx.eventBus();
        eventBus.consumer(Constant.ADDRESS, receivedMessage -> {
            System.out.println("Received message: " + receivedMessage.body());
            receivedMessage.reply("Hello, world");
        });

        System.out.println("Receiver's ready");
    }

}
