package com.manhpd.impartive_programming;

public class Order {

    private Customer customer;

    private OrderStatus orderStatus;

    /**
     * First step:
     * issueRewards() method violate the no side effect
     * because it does two thing:
     * - sets the value of the OrderStatus field --> uses the mutation of the global variables that is outside of issueRewards() method.
     * - call addToRewardBalance() method of Customer object.
     * --> issueRewards() has multiple responsibilities --> violate SRP.
     *
     * Solution:
     * - split the mutation of variables statements to the other methods.
     * - Because functional programs are built by composing functions that
     * take an argument and return a value. And in functional programming,
     * the state of the object tends to decrease.
     *
     * In updateBalanceReward() method, the customer object is only used in
     * this.customer.addToRewardBalance(this.getOrderRewards());
     * So, we can remove the customer field in the Order object.
     */
    public void issueRewards() {
        this.orderStatus = OrderStatus.REWARDS_ISSUED;
        if (this.getOrderRewards() != null) {
            this.customer.addToRewardBalance(this.getOrderRewards());
        }
    }

    public Integer getOrderRewards() {
        return 10;
    }

}
