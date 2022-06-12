package com.manhpd.guestservice.persistence.repository;

import com.manhpd.guestservice.persistence.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
}
