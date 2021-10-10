package com.manhpd.dto.purchase_order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    private String name;

    private Address shippingAddress;

    private Address billingAddress;

    private Loyalty loyalty;

}
