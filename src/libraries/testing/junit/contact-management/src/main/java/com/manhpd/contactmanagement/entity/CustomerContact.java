package com.manhpd.contactmanagement.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="customer_contact")
@Data
@NoArgsConstructor
public class CustomerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String deliveryAddressLine1;

    private String deliveryAddressLine2;

    private String deliveryAddressCity;

    private String deliveryAddressState;

    private String deliveryAddressZipCode;

}