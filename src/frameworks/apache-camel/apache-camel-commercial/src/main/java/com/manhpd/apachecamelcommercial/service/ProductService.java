package com.manhpd.apachecamelcommercial.service;

import com.manhpd.apachecamelcommercial.model.OrderLine;
import com.manhpd.apachecamelcommercial.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    public double getPrice(final String productName) {
        return 2.4;
    }

    public List<OrderLine> generateOrder() {
        System.out.println("generating orders");
        List<OrderLine> orderLines = new ArrayList<OrderLine>();

        OrderLine orderLine = new OrderLine();
        orderLine.setProduct(new Product("Television", "Electronics"));

        orderLines.add(orderLine);

        orderLine = new OrderLine();
        orderLine.setProduct(new Product("Washing Machine", "Household"));

        orderLines.add(orderLine);
        return orderLines;
    }

    public List<Product> fetchProductsByCategory(final String category) {
        System.out.println("fetching products of category "+category);

        List<Product> products = new ArrayList<Product>();
        products.add(new Product("Television","Electronics"));
        products.add(new Product("Washing Machine","Household"));

        return products;
    }

}
