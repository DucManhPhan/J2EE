package com.manhpd.ecommerce.persist.repository;

import com.manhpd.ecommerce.persist.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}