package com.manhpd.springlettuceconnection.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("user")
@Data
@ToString
@AllArgsConstructor
public class User {

    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

}
