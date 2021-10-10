package com.manhpd.functional_programming;

import com.manhpd.impartive_programming.OrderStatus;

public class Order {

    private final Integer orderRewards;

    private final OrderStatus orderStatus;

    public Order(Integer orderRewards, OrderStatus orderStatus) {
        this.orderRewards = orderRewards;
        this.orderStatus = orderStatus;
    }

    public Integer getOrderRewards() {
        return 10;
    }
}
