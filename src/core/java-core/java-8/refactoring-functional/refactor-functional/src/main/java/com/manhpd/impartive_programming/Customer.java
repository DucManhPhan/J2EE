package com.manhpd.impartive_programming;

public class Customer {

    private Integer orderRewards;

    public Customer(Integer orderRewards) {
        this.orderRewards = orderRewards;
    }

    public Integer getOrderRewards() {
        return orderRewards;
    }

    public void addToRewardBalance(Integer orderRewards) {
        this.orderRewards += orderRewards;
    }
}
