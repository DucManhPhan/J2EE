package com.manhpd.apachecamelcommercial.model;

import lombok.Data;

@Data
public class OrderLine {

    private Product product;

    private int numberOfUnits;

    private double price;

}
