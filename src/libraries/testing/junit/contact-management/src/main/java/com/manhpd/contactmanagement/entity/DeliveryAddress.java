package com.manhpd.contactmanagement.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "delivery_address")
@Data
@NoArgsConstructor
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String line1;

    private String line2;

    private String city;

    private String state;

    private String zipCode;

}
