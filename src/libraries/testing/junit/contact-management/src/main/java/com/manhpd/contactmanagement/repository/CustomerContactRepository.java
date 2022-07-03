package com.manhpd.contactmanagement.repository;

import com.manhpd.contactmanagement.entity.CustomerContact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {
    CustomerContact findByEmail(String email);
}
