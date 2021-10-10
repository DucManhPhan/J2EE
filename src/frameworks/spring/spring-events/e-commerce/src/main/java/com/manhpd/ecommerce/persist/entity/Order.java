package com.manhpd.ecommerce.persist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    private OrderStatus status;

    @Column(length = 40)
    private String message;

    @ManyToOne
    private Customer customer;

    public Order(OrderStatus status) {
        this.status = status;
    }

    public enum OrderStatus {
        SAVED, COMPLETED
    }
}
