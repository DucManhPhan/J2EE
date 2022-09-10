package com.manhpd.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JedisPoolApp {

    private static final String JEDIS_CONNECTION_STRING = "redis://localhost:6379";

    public static void main(String[] args) {
        JedisPool jedisPool = new JedisPool(JEDIS_CONNECTION_STRING);

        List<String> allResults = IntStream.rangeClosed(1, 5)
                .parallel()
                .mapToObj(n -> {
                    Jedis jedis = jedisPool.getResource();

                    jedis.set("foo" + n, "bar" + n);
                    String result = jedis.get("foo" + n);

                    jedis.close();

                    return result;
                })
                .collect(Collectors.toList());

        jedisPool.close();
        System.out.println(allResults);
    }

}
