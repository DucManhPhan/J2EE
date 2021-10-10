package com.manhpd.after;

import com.manhpd.Product;

public class ProductRepository {
    public Product getProductById(Long id) {
        // For testing purpose if ID equals 99, return null
        if(id == 99) {
            return null;
        }
        // Get product from the database
        return new Product(id);
    }
}
