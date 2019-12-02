package com.manhpd.utils;

import com.manhpd.connection.RedisConnection;
import com.manhpd.dto.RedisConfig;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class RedisUtils {

    private static RedisConnection redisConnection = RedisConnection.getInstance();

    public static RedisConfig readRedisConfigFile(String path) {
        RedisConfig redisConfig = new RedisConfig();

        try {
            // read configuration file
            Properties properties = new Properties();
//            FileInputStream redisConfigFile = new FileInputStream(path);
            InputStream redisConfigFile = RedisUtils.class.getClassLoader().getResourceAsStream(path);
            if (redisConfigFile == null) {
                System.out.println("Do not find redis.conf");
                return null;
            }

            properties.load(redisConfigFile);

            // parse each field
            // use redis url
            String tmp = properties.getProperty(Constants.REDIS_URL);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis URL is not correct.");
            }

            redisConfig.setRedisUrl(tmp);

            // use redis master name
            tmp = properties.getProperty(Constants.REDIS_MASTER_NAME);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis master name is empty");
            }
            redisConfig.setRedisMasterName(tmp.trim());

            // use redis db
            tmp = properties.getProperty(Constants.REDIS_DB);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis database is empty");
            }
            redisConfig.setRedisDb(Integer.parseInt(tmp.trim()));

            // use redis pass
            tmp = properties.getProperty(Constants.REDIS_PASS);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis password is empty");
            }
            redisConfig.setRedisPass(tmp);

            // use redis check
            tmp = properties.getProperty(Constants.REDIS_CHECK);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis check is empty");
            }
            redisConfig.setRedisCheck(Long.parseLong(tmp.trim()));

            // use redis timeout
            tmp = properties.getProperty(Constants.REDIS_TIMEOUT);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis timeout is empty");
            }
            redisConfig.setRedisTimeout(Long.parseLong(tmp.trim()));

            // use redis max wait milisecond
            tmp = properties.getProperty(Constants.REDIS_MAX_WAIT_MILLIS);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis max wait is empty");
            }
            redisConfig.setRedisMaxWaitMs(Long.parseLong(tmp.trim()));

            // use redis max total
            tmp = properties.getProperty(Constants.REDIS_MAX_TOTAL);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis max total is empty");
            }
            redisConfig.setRedisMaxTotal(Integer.parseInt(tmp.trim()));

            // use redis max idle
            tmp = properties.getProperty(Constants.REDIS_MAX_IDLE);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis max idle is empty");
            }
            redisConfig.setRedisMaxIdle(Integer.parseInt(tmp));

            // use redis min idle
            tmp = properties.getProperty(Constants.REDIS_MIN_IDLE);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis min idle is empty");
            }
            redisConfig.setRedisMinIdle(Integer.parseInt(tmp));

            // use redis num test per eviction run
            tmp = properties.getProperty(Constants.REDIS_NUM_TESTS_PER_EVICTION_RUN);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis redis num test per eviction run is empty");
            }
            redisConfig.setRedisNumTestsPerEvictionRun(Integer.parseInt(tmp));

            // use redis time between eviction run milliseconds
            tmp = properties.getProperty(Constants.REDIS_TIME_BETWEEN_EVICTION_RUN_MILLIS);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis time between eviction run is empty");
            }
            redisConfig.setRedisTimeBetweenEvictionRunMs(Long.parseLong(tmp.trim()));

            // use redis min evictable idle time milliseconds
            tmp = properties.getProperty(Constants.REDIS_MIN_EVICTABLE_IDLE_TIME_MILLIS);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis num test per eviction run is empty");
            }
            redisConfig.setRedisMinEvictableIdleTimeMs(Long.parseLong(tmp.trim()));

            // use redis value this instance
            tmp = properties.getProperty(Constants.REDIS_VALUE_THIS_INSTANCE);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis value this instance is empty");
            }
            redisConfig.setRedisValueThisInstance(tmp.trim());

            // use redis key unique device
            tmp = properties.getProperty(Constants.REDIS_KEY_UNIQUE_DEVICE);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis key unique device is empty");
            }
            redisConfig.setRedisKeyUniqueDevice(tmp.trim());

            // use redis ttl unique device day
            tmp = properties.getProperty(Constants.REDIS_TTL_UNIQUE_DEVICE_DAY);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis ttl unique device is empty");
            }
            redisConfig.setRedisTtlUniqueDeviceDay(Integer.parseInt(tmp.trim()));

            // use redis key total success
            tmp = properties.getProperty(Constants.REDIS_KEY_TOTAL_SUCCESS);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis key total success is empty");
            }
            redisConfig.setRedisKeyTotalSuccess(tmp.trim());

            // use redis ttl total success day
            tmp = properties.getProperty(Constants.REDIS_TTL_TOTAL_SUCCESS_DAY);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis ttl total success day is empty");
            }
            redisConfig.setRedisTtlTotalSuccessDay(Integer.parseInt(tmp.trim()));

            // use redis key total failure
            tmp = properties.getProperty(Constants.REDIS_KEY_TOTAL_FAILURE);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis ttl total success day is empty");
            }
            redisConfig.setRedisKeyTotalFailure(tmp.trim());

            // use redis ttl total failure day
            tmp = properties.getProperty(Constants.REDIS_TTL_TOTAL_FAILURE_DAY);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis ttl total failure day is empty");
            }
            redisConfig.setRedisTtlTotalFailureDay(Integer.parseInt(tmp.trim()));

            // use redis key total notsend
            tmp = properties.getProperty(Constants.REDIS_KEY_TOTAL_NOTSEND);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis key total not send is empty");
            }
            redisConfig.setRedisKeyTotalNotSend(tmp.trim());

            // use redis ttl total not send day
            tmp = properties.getProperty(Constants.REDIS_TTL_TOTAL_NOTSEND_DAY);
            if (StringUtils.isEmpty(tmp)) {
                System.out.println("Redis ttl total not send is empty");
            }
            redisConfig.setRedisTtlTotalNotSendDay(Integer.parseInt(tmp.trim()));
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return redisConfig;
    }

    public static long expireAt(String keyCache, long expireTime) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.expireAt(keyCache, expireTime);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

        return result;
    }

    public static Set<String> keys(String keyPattern) {
        Set<String> result = null;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.keys(keyPattern);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public static String rpop(String keyCache) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.rpop(keyCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public static List<String> lrange(String keyCache, long idxStart, long idxStop) {
        List<String> result = null;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.lrange(keyCache, idxStart, idxStop);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public static long llen(String keyCache) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.llen(keyCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public static String get(String keyCache) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.get(keyCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public static long lpush(String keyCache, String valueCache) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.lpush(keyCache, valueCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public static boolean exists(String keyCache) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.exists(keyCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public static boolean hexists(String keyCache, String fieldName) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.hexists(keyCache, fieldName);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public String hget(String keyCache, String fieldName) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.hget(keyCache, fieldName);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public long hdel(String keyCache, String fieldName) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.hdel(keyCache, fieldName);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public long hset(String keyCache, String fieldName, String valueCache) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.hset(keyCache, fieldName, valueCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public long sadd(String keyCache, String valueCache) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.sadd(keyCache, valueCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public boolean sismember(String keyCache, String valueCache) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.sismember(keyCache, valueCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public long incrBy(String keyCache, int valueCache) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.incrBy(keyCache, valueCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public String setex(String keyCache, int secondsLive, String valueCache) {
        String result = null;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.setex(keyCache, secondsLive, valueCache);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

    public long expire(String keyCache, int expireTime) {
        long result = 0;
        Jedis jedis = null;
        try {
            jedis = redisConnection.getPool().getResource();
            if (jedis != null) {
                result = jedis.expire(keyCache, expireTime);
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                if (jedis != null) {
                    jedis.close();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
        return result;
    }

}
