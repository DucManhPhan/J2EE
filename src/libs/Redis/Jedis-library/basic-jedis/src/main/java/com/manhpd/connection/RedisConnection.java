package com.manhpd.connection;

import com.manhpd.dto.RedisConfig;
import com.manhpd.utils.RedisUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.Pool;

import java.util.HashSet;
import java.util.Set;

public class RedisConnection {

    private RedisConfig redisConfig;

    private Pool<Jedis> pool;

    public RedisConnection() {
        this.redisConfig = RedisUtils.readRedisConfigFile("./redis.conf");
    }

    public  Pool<Jedis> getPool() {
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
        try {
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

            if ((redisConfig.getRedisMasterName() != null) && !"".equals(redisConfig.getRedisMasterName())) {
                String[] str = redisConfig.getRedisUrl().split(",");
                Set<String> sentinel = new HashSet<String>();

                for (int i = 0; i < str.length; i++) {
                    if (str[i].contains(":")) {
                        int index = str[i].indexOf(":");
                        String redisHost = str[i].substring(0, index).trim();
                        String redisPort = str[i].substring(index + 1).trim();
                        sentinel.add(new HostAndPort(redisHost, Integer.parseInt(redisPort)).toString());
                    }
                }

                pool = new JedisSentinelPool(
                        redisConfig.getRedisMasterName(),
                        sentinel,
                        genericObjectPoolConfig,
                        (int) redisConfig.getRedisTimeout(),
                        redisConfig.getRedisPass(),
                        redisConfig.getRedisDb()
                );
            } else {
                String str = redisConfig.getRedisUrl();
                String redisHost = "";
                String redisPort = "";

                if (str.contains(":")) {
                    int index = str.indexOf(":");
                    redisHost = str.substring(0, index).trim();
                    redisPort = str.substring(index + 1).trim();
                }

                pool = new JedisPool(
                        genericObjectPoolConfig,
                        redisHost,
                        Integer.parseInt(redisPort),
                        (int) redisConfig.getRedisTimeout(),
                        redisConfig.getRedisPass(),
                        redisConfig.getRedisDb()
                );
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

}
