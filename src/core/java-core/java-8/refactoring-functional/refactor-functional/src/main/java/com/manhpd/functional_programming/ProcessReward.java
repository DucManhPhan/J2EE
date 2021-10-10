package com.manhpd.functional_programming;

import com.manhpd.impartive_programming.OrderStatus;

public class ProcessReward {

    /**
     * In functional programming, most methods that represent pure functions are static
     * Functional programming tends to prefer objects that just hold data,
     * but we still have side effects and void methods. To avoid them, use referential transparency.
     *
     * @param order
     */
    public static Order issueRewards(Order order) {
        return new Order(order.getOrderRewards(), OrderStatus.REWARDS_ISSUED);
    }

    public Customer updateBalanceReward(Order order, Customer customer) {
        if (order.getOrderRewards() != null) {
            Customer newCustomer = new Customer(customer.getRewardBalance() + order.getOrderRewards());
            return newCustomer;
        }

        return customer;
    }
}
