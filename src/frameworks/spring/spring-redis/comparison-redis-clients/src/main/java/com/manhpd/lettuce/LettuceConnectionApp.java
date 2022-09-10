package com.manhpd.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceConnectionApp {

    private static final String LETTUCE_CONNECTION_STRING = "redis://localhost:6379";

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create(LETTUCE_CONNECTION_STRING);
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        RedisCommands<String, String> sync = connection.sync();
        sync.set("foo-1", "bar-1");

        String result = sync.get("foo-1");

        connection.close();
        redisClient.shutdown();

        System.out.println(result);
    }

}
