package com.manhpd.springlettuceconnection.service;

import com.manhpd.springlettuceconnection.redis.entity.User;
import com.manhpd.springlettuceconnection.redis.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RedisOperationsServiceImpl implements RedisOperationsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void insertNormalKeyValue() {
        this.redisTemplate.opsForValue().set("org","orange");
        System.out.println("Retrieve from redis: " + this.redisTemplate.opsForValue().get("org"));
    }

    /**
     * Insert an object into Redis. But Redis will save it as hash map.
     *
     */
    @Override
    public void insertHashValue() {
        // 1. Using repository of Spring Data Redis to insert hash value.
//        User user = new User(1L, "john", "john", "john");
//        this.userRepository.save(user);
//
//        System.out.println("Retrieved user: " + this.userRepository.findById(1L).toString());

        // 2. Define Map data structure to insert Redis.
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        List<String> list2 = new ArrayList<>();
        list2.add("5");
        list2.add("6");
        list2.add("7");
        list2.add("8");

        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("map1", list.toString());
        valueMap.put("map2", list2.toString());

        System.out.println("Insert map object into redis");
        this.redisTemplate.opsForHash().putAll("test", valueMap); // {map2=[5, 6, 7, 8], map1=[1, 2, 3, 4]}

        System.out.println("Retrieve hash value from Redis: " + this.redisTemplate.opsForHash().values("test"));
    }

    @Override
    public void insertListValue() {
        List<String> test = new ArrayList<>();
        test.add("1");
        test.add("2");
        test.add("3");
        test.add("4");

        this.redisTemplate.opsForList().leftPushAll("list-test", test);
        System.out.println(this.redisTemplate.opsForList().range("list-test", 0, -1));
    }

    @Override
    public void insertSetValue() {

    }

    @Override
    public void insertZSetValue() {

    }
}
