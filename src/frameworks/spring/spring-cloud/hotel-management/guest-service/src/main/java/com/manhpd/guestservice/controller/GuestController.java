package com.manhpd.guestservice.controller;

import com.manhpd.guestservice.persistence.entity.Guest;
import com.manhpd.guestservice.persistence.repository.GuestRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guests")
public class GuestController {

    private final GuestRepository repository;

    public GuestController(GuestRepository repository) {
        super();
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Guest> getAllGuests() {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") long id) {
        return this.repository.findById(id).get();
    }

}
