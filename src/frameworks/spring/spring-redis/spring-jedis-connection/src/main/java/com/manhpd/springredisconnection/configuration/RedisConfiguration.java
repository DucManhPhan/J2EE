package com.manhpd.springredisconnection.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Currently, we only configure Jedis to connect the single Redis, not Redis cluster.
 *
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName("localhost");
//        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.setClientName("xxx");
//        jedisConnectionFactory.setPassword("xxx");

        return jedisConnectionFactory;
    }

    @Bean
    RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTempate = new RedisTemplate<>();
        redisTempate.setConnectionFactory(jedisConnectionFactory());

        return redisTempate;
    }

}
