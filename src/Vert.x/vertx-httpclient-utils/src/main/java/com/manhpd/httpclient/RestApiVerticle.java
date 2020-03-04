package com.manhpd.httpclient;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

public class RestApiVerticle extends AbstractVerticle {

    private WebClientOptions createWebClientOption() {
        WebClientOptions options = new WebClientOptions();
        options.setDefaultHost("http://localhost");
        options.setDefaultPort(8080);

        return options;
    }

    private WebClient createWebClient() {
        WebClientOptions options = this.createWebClientOption();
        WebClient webClient = WebClient.create(this.vertx, options);

        return webClient;
    }

    @Override
    public void start(Future<Void> future) {
        WebClient webClient = this.createWebClient();
    }

}
