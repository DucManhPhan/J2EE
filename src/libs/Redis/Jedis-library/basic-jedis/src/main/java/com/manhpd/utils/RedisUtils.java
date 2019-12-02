package com.manhpd.utils;

import com.manhpd.connection.RedisConnection;
import com.manhpd.dto.RedisConfig;
import redis.clients.jedis.Jedis;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class RedisUtils {

    private static RedisConnection redisConnection = new RedisConnection();

    public static RedisConfig readRedisConfigFile(String path) {
        RedisConfig redisConfig = new RedisConfig();

        try {
            // read configuration file
            Properties properties = new Properties();
            FileInputStream redisConfigFile = new FileInputStream(path);
            properties.load(redisConfigFile);

            // parse each field
            redisConfig.setRedisUrl(properties.getProperty(Constants.REDIS_URL));
            redisConfig.setRedisMasterName(properties.getProperty(Constants.REDIS_MASTER_NAME).trim());
            redisConfig.setRedisDb(Integer.parseInt(properties.getProperty(Constants.REDIS_DB).trim()));
            redisConfig.setRedisPass(properties.getProperty(Constants.REDIS_PASS));
            redisConfig.setRedisCheck(Long.parseLong(properties.getProperty(Constants.REDIS_CHECK).trim()));
            redisConfig.setRedisTimeout(Long.parseLong(properties.getProperty(Constants.REDIS_TIMEOUT).trim()));
            redisConfig.setRedisMaxWaitMs(Long.parseLong(properties.getProperty(Constants.REDIS_MAX_WAIT_MILLIS).trim()));
            redisConfig.setRedisMaxTotal(Integer.parseInt(properties.getProperty(Constants.REDIS_MAX_TOTAL).trim()));
            redisConfig.setRedisMaxIdle(Integer.parseInt(properties.getProperty(Constants.REDIS_MAX_IDLE)));
            redisConfig.setRedisMinIdle(Integer.parseInt(properties.getProperty(Constants.REDIS_MIN_IDLE)));
            redisConfig.setRedisNumTestsPerEvictionRun(Integer.parseInt(properties.getProperty(Constants.REDIS_NUM_TESTS_PER_EVICTION_RUN)));
            redisConfig.setRedisTimeBetweenEvictionRunMs(Long.parseLong(properties.getProperty(Constants.REDIS_TIME_BETWEEN_EVICTION_RUN_MILLIS).trim()));
            redisConfig.setRedisMinEvictableIdleTimeMs(Long.parseLong(properties.getProperty(Constants.REDIS_MIN_EVICTABLE_IDLE_TIME_MILLIS).trim()));
            redisConfig.setRedisValueThisInstance(properties.getProperty(Constants.REDIS_VALUE_THIS_INSTANCE).trim());
            redisConfig.setRedisKeyUniqueDevice(properties.getProperty(Constants.REDIS_KEY_UNIQUE_DEVICE).trim());
            redisConfig.setRedisTtlUniqueDeviceDay(Integer.parseInt(properties.getProperty(Constants.REDIS_TTL_UNIQUE_DEVICE_DAY).trim()));
            redisConfig.setRedisKeyTotalSuccess(properties.getProperty(Constants.REDIS_KEY_TOTAL_SUCCESS).trim());
            redisConfig.setRedisTtlTotalSuccessDay(Integer.parseInt(properties.getProperty(Constants.REDIS_TTL_TOTAL_SUCCESS_DAY).trim()));
            redisConfig.setRedisKeyTotalFailure(properties.getProperty(Constants.REDIS_KEY_TOTAL_FAILURE).trim());
            redisConfig.setRedisTtlTotalFailureDay(Integer.parseInt(properties.getProperty(Constants.REDIS_TTL_TOTAL_FAILURE_DAY).trim()));
            redisConfig.setRedisKeyTotalNotSend(properties.getProperty(Constants.REDIS_KEY_TOTAL_NOTSEND).trim());
            redisConfig.setRedisTtlTotalNotSendDay(Integer.parseInt(properties.getProperty(Constants.REDIS_TTL_TOTAL_NOTSEND_DAY).trim()));
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
