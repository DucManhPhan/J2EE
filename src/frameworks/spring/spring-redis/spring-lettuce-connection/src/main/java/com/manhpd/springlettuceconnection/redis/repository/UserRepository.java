package com.manhpd.springlettuceconnection.redis.repository;

import com.manhpd.springlettuceconnection.redis.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
}
