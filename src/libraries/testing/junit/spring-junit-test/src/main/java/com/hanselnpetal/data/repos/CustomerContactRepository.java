package com.hanselnpetal.data.repos;

import org.springframework.data.repository.CrudRepository;

import com.hanselnpetal.domain.CustomerContact;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

}
