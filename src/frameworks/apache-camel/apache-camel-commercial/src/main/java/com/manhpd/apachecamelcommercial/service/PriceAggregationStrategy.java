package com.manhpd.apachecamelcommercial.service;

import com.manhpd.apachecamelcommercial.model.Order;
import com.manhpd.apachecamelcommercial.model.OrderLine;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Component
public class PriceAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        OrderLine newBody = newExchange.getIn().getBody(OrderLine.class);
        if (Objects.isNull(oldExchange)) {
            Order order = new Order();
            order.setOrderNo(UUID.randomUUID().toString());
            order.setOrderDate(Instant.now().toString());
            order.setOrderPrice(newBody.getPrice());
            order.addOrderLine(newBody);

            newExchange.getIn().setBody(order, Order.class);
            return newExchange;
        }

        OrderLine newOrderLine = newExchange.getIn().getBody(OrderLine.class);
        Order order = oldExchange.getIn().getBody(Order.class);
        order.setOrderPrice(order.getOrderPrice() + newOrderLine.getPrice());
        order.addOrderLine(newOrderLine);
        oldExchange.getIn().setBody(order);

        return oldExchange;
    }
}
