package com.manhpd;

import com.manhpd.connection.RedisConnection;
import com.manhpd.utils.RedisUtils;
import redis.clients.jedis.Jedis;

public class App {
    public static void main(String[] args) {
        RedisConnection redisConnection = RedisConnection.getInstance();
        redisConnection.checkPool();

//        String result = RedisUtils.get("phonetype");
//        System.out.println(result);

        RedisUtils.set("book", "dracular book");
        String result = RedisUtils.get("book");
        System.out.println(result);
    }
}
