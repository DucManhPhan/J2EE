package com.manhpd.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedisConfig {

    private String redisUrl;

    private String redisMasterName;

    private int redisDb;

    private String redisPass;

    private long redisCheck;

    private long redisTimeout;

    private long redisMaxWaitMs;

    private int redisMaxTotal;

    private int redisMaxIdle;

    private int redisMinIdle;

    private int redisNumTestsPerEvictionRun;

    private long redisTimeBetweenEvictionRunMs;

    private long redisMinEvictableIdleTimeMs;

    private String redisValueThisInstance;

    private String redisKeyUniqueDevice;

    private int redisTtlUniqueDeviceDay;

    private String redisKeyTotalSuccess;

    private int redisTtlTotalSuccessDay;

    private String redisKeyTotalFailure;

    private int redisTtlTotalFailureDay;

    private String redisKeyTotalNotSend;

    private int redisTtlTotalNotSendDay;

}
