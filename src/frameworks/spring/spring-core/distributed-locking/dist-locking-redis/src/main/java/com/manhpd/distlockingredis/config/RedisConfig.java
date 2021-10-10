package com.manhpd.distlockingredis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
public class RedisConfig {

    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Bean
    public Jedis jedis() {
        System.out.println("Created jedis successfully");
        JedisPool pool = new JedisPool(this.host, this.port);
        return pool.getResource();
    }

}
