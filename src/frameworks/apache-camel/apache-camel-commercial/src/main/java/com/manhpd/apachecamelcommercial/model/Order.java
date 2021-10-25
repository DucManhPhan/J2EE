package com.manhpd.apachecamelcommercial.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Order {

    private String orderNo;

    private String orderDate;

    private List<OrderLine> orderLines;

    private double discount;

    private double orderPrice;

    public void addOrderLine(OrderLine orderLine) {
        if (Objects.isNull(orderLine)) {
            this.orderLines = new ArrayList<>();
        }

        this.orderLines.add(orderLine);
    }

}
