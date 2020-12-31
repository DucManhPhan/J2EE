package com.manhpd.before;

import com.manhpd.Product;

public class ProductRepository {
    public Product getProductById(Long id) {
        // Get product from the database
        return new Product(id);
    }
}
