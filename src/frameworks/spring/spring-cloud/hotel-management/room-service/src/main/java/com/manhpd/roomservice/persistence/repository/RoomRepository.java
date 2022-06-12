package com.manhpd.roomservice.persistence.repository;

import com.manhpd.roomservice.persistence.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
