package com.manhpd.ecommerce.persist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String email;

    private boolean newsletter;

    @Column(columnDefinition = "DECIMAL(2)")
    private BigDecimal rewardPoints;

    public Customer(String email) {
        this.email = email;
    }
}
