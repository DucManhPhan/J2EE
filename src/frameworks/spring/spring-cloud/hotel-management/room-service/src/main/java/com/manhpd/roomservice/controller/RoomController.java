package com.manhpd.roomservice.controller;

import com.manhpd.roomservice.persistence.entity.Room;
import com.manhpd.roomservice.persistence.repository.RoomRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository repository;

    public RoomController(RoomRepository repository){
        super();
        this.repository = repository;
    }

    @GetMapping
    public Iterable<Room> getAllRooms(){
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id")long id){
        return this.repository.findById(id).get();
    }

}
