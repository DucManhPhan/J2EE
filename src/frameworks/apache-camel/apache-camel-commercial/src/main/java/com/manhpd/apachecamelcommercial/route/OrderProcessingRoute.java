package com.manhpd.apachecamelcommercial.route;

import com.manhpd.apachecamelcommercial.service.PriceAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingRoute extends RouteBuilder {

    @Autowired
    private PriceAggregationStrategy priceAggregationStrategy;

    @Override
    public void configure() throws Exception {
        from("direct:fetchProcess")
                .split(body(), this.priceAggregationStrategy).parallelProcessing()
                .to("bean:pricingService?method=calculatePrice")
                .end();
    }

}
