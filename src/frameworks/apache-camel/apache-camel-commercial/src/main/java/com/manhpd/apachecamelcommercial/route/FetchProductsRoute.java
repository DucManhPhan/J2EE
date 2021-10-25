package com.manhpd.apachecamelcommercial.route;

import com.manhpd.apachecamelcommercial.service.ProductService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FetchProductsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:fetchProducts")
                .routeId("direct-fetchProducts")
                .tracing()
                .log(">>> ${body}")
                .bean(ProductService.class, "fetchProductsByCategory")
                .end();
    }
}
