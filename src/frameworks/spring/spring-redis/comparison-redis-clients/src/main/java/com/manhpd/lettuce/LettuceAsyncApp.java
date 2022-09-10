package com.manhpd.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

public class LettuceAsyncApp {

    private static final String LETTUCE_CONNECTION_STRING = "redis://localhost:6379";

    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create(LETTUCE_CONNECTION_STRING);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisAsyncCommands<String, String> async = connection.async();

        final String[] result = new String[1];

        async.set("foo-1", "bar-async-lettuce")
                .thenComposeAsync(ok -> async.get("foo-1"))
                .thenAccept(s -> result[0] = s)
                .toCompletableFuture()
                .join();

        connection.close();
        redisClient.shutdown();

        System.out.println(result[0]);
    }

}
