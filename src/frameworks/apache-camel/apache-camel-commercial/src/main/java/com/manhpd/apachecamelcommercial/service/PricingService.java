package com.manhpd.apachecamelcommercial.service;

import com.manhpd.apachecamelcommercial.model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class PricingService {

    public OrderLine calculatePrice(final OrderLine orderLine ) {
        String category = orderLine.getProduct().getProductCategory();
        if ("Electronics".equalsIgnoreCase(category)) {
            orderLine.setPrice(300.0);
        } else if ("Household".equalsIgnoreCase(category)) {
            orderLine.setPrice(55.0);
        } else {
            orderLine.setPrice(99.0);
        }

        return orderLine;
    }

}
