package com.manhpd.vendingmachine.persistence.repository;

import com.manhpd.vendingmachine.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
