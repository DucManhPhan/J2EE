package com.manhpd.dto.purchase_order;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;

    private String city;

    private String postalCode;

    private String country;

}