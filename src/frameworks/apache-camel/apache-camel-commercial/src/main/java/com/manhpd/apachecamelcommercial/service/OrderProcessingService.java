package com.manhpd.apachecamelcommercial.service;

import com.manhpd.apachecamelcommercial.model.Order;
import com.manhpd.apachecamelcommercial.model.OrderLine;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProcessingService {

    @Autowired
    private ProducerTemplate producerTemplate;

    public Order process(final List<OrderLine> orderLines) {
        Order order = this.producerTemplate.requestBody(
                "direct:processOrder", orderLines, Order.class);

        return order;
    }

}
