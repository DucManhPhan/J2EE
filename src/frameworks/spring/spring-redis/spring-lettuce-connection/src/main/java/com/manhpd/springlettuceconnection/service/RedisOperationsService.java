package com.manhpd.springlettuceconnection.service;

public interface RedisOperationsService {

    void insertNormalKeyValue();

    void insertHashValue();

    void insertListValue();

    void insertSetValue();

    void insertZSetValue();

}
