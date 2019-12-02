package com.manhpd;

import com.manhpd.connection.RedisConnection;

public class App {
    public static void main(String[] args) {
        RedisConnection redisConnection = RedisConnection.getInstance();
        redisConnection.checkPool();
    }
}
