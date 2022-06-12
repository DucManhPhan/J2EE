package com.manhpd.reservationservice.persistence.repository;

import com.manhpd.reservationservice.persistence.entity.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
}
