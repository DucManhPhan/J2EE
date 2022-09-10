package com.manhpd.jedis;

import redis.clients.jedis.Jedis;

public class JedisConnectionApp {

    private static final String JEDIS_CONNECTION_STRING = "redis://localhost:6379";

    public static void main(String[] args) {
        Jedis jedis = new Jedis(JEDIS_CONNECTION_STRING);

        jedis.set("foo", "bar");
        String result = jedis.get("foo");

        jedis.close();
        System.out.println(result);
    }
}
