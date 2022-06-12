package com.manhpd.reservationservice.controller;

import com.manhpd.reservationservice.persistence.entity.Reservation;
import com.manhpd.reservationservice.persistence.repository.ReservationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationRepository repository;

    public ReservationController(ReservationRepository repository){
        super();
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Reservation> getAllReservations(){
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id){
        return this.repository.findById(id).get();
    }

}
