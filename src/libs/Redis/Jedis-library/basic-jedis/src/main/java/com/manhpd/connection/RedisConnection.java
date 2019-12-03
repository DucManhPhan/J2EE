package com.manhpd.connection;

import com.manhpd.dto.RedisConfig;
import com.manhpd.utils.Constants;
import com.manhpd.utils.RedisUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RedisConnection {

    private static RedisConnection redisConnection = null;

    private RedisConfig redisConfig;

    private Pool<Jedis> pool;

    private RedisConnection() {
        this.redisConfig = RedisUtils.readRedisConfigFile("redis.conf");
    }

    public static RedisConnection getInstance() {
        if (redisConnection == null) {
            synchronized (RedisConnection.class) {
                if (redisConnection == null) {
                    redisConnection = new RedisConnection();
                }
            }
        }

        return redisConnection;
    }

    public Pool<Jedis> getPool() {
        try {
            if (this.pool == null || this.pool.isClosed()) {
                this.resetPool();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return this.pool;
    }

    private void initPool() {
        // 1st way
//        this.pool = new JedisPool(Constants.FIXED_HOST, Constants.FIXED_PORT);

        // 2nd way
        try {
            GenericObjectPoolConfig genericObjectPoolConfig = this.createPool(redisConfig);

            if ((redisConfig.getRedisMasterName() != null) && !"".equals(redisConfig.getRedisMasterName())) {
                this.createRedisSentinel(genericObjectPoolConfig);
            } else {
                this.createRedisPool(genericObjectPoolConfig);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    private void resetPool() {
        if (this.pool != null && !this.pool.isClosed()) {
            this.pool.close();
        }

        this.initPool();
    }

    public void checkPool() {
        Jedis jedis = null;
        try {
            jedis = getPool().getResource();
            if (jedis != null) {
                System.out.println("REDIS CONNECTION IS OK");
            } else {
                System.out.println("REDIS CONNECTION IS NOT OK, RECONNECTING .... ");
                resetPool();
            }
        } catch (Exception ex) {
            System.out.println("REDIS CONNECTION IS NOT OK, RECONNECTING .... ");
            resetPool();
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    private GenericObjectPoolConfig createPool(RedisConfig redisConfig) {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxWaitMillis(redisConfig.getRedisMaxWaitMs());
        genericObjectPoolConfig.setMaxTotal(redisConfig.getRedisMaxTotal());
        genericObjectPoolConfig.setMaxIdle(redisConfig.getRedisMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisConfig.getRedisMinIdle());
        genericObjectPoolConfig.setNumTestsPerEvictionRun(redisConfig.getRedisNumTestsPerEvictionRun());
        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(redisConfig.getRedisTimeBetweenEvictionRunMs());
        genericObjectPoolConfig.setMinEvictableIdleTimeMillis(redisConfig.getRedisMinEvictableIdleTimeMs());
        genericObjectPoolConfig.setTestOnBorrow(true);
        genericObjectPoolConfig.setTestOnReturn(true);
        genericObjectPoolConfig.setTestWhileIdle(true);
        genericObjectPoolConfig.setBlockWhenExhausted(true);

        return genericObjectPoolConfig;
    }

    private Set<String> getSentinelFromUrl(String redisUrl) {
        String[] str = redisUrl.split(",");
        Set<String> sentinel = new HashSet<String>();

        for (int i = 0; i < str.length; i++) {
            if (str[i].contains(":")) {
                int index = str[i].indexOf(":");
                String redisHost = str[i].substring(0, index).trim();
                String redisPort = str[i].substring(index + 1).trim();
                sentinel.add(new HostAndPort(redisHost, Integer.parseInt(redisPort)).toString());
            }
        }

        return sentinel;
    }

    private Map<String, String> getHostPort(String redisUrl) {
        Map<String, String> result = new HashMap<>();
        if (redisUrl.contains(":")) {
            int index = redisUrl.indexOf(":");
            String redisHost = redisUrl.substring(0, index).trim();
            String redisPort = redisUrl.substring(index + 1).trim();

            result.put(redisHost, redisPort);
        }

        return result;
    }

    private void createRedisSentinel(GenericObjectPoolConfig genericObjectPoolConfig) {
        Set<String> sentinel = this.getSentinelFromUrl(redisConfig.getRedisUrl());
        this.pool = new JedisSentinelPool(redisConfig.getRedisMasterName(),
                sentinel,
                genericObjectPoolConfig,
                (int) redisConfig.getRedisTimeout(),
                redisConfig.getRedisPass(),
                redisConfig.getRedisDb());
    }

    private void createRedisPool(GenericObjectPoolConfig genericObjectPoolConfig) {
        String redisHost = "";
        String redisPort = "";

        Map<String, String> hostPortUrl = this.getHostPort(redisConfig.getRedisUrl());
        if (hostPortUrl.size() > 0) {
            Map.Entry<String, String> entry = hostPortUrl.entrySet().iterator().next();
            redisHost = entry.getKey();
            redisPort = entry.getValue();
        }

        this.pool = new JedisPool(genericObjectPoolConfig,
                                redisHost,
                                Integer.parseInt(redisPort),
                                (int) redisConfig.getRedisTimeout(),
                                redisConfig.getRedisPass(),
                                redisConfig.getRedisDb());
    }

}
