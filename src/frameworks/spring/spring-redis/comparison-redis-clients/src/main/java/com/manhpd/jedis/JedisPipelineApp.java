package com.manhpd.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Tuple;

import java.util.Set;
import java.util.stream.Collectors;

public class JedisPipelineApp {

    private static final String JEDIS_CONNECTION_STRING = "redis://localhost:6379";

    public static void main(String[] args) {
        Jedis jedis = new Jedis(JEDIS_CONNECTION_STRING);
        Pipeline pipeline = jedis.pipelined();

        pipeline.set("foo-2", "bar-pipeline");
        Response<String> get = pipeline.get("foo-2");

        pipeline.zadd("baz", 13, "alpha");
        pipeline.zadd("baz", 23, "bravo");
        pipeline.zadd("baz", 42, "charlie");

        Response<Set<Tuple>> range = pipeline.zrangeWithScores("baz", 0, -1);

        pipeline.sync();

        jedis.close();

        System.out.println(get.get()); //bar-pipeline
        System.out.println(range.get().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" ")));

    }
}
