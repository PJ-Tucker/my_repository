package com.demo.mysqlredis.service;

import redis.clients.jedis.Jedis;

/**
 * @author zhangchunguang
 * @data 2020/12/24 7:59 下午
 */
public interface RedisService {
    Jedis getResoures();
    void returnResources(Jedis jedis);

    String set(final String key, final String value);
    String setx(final String key, final String value, final Long expire);
    String setxnew(final String key, final String value, final Long expire);
    String get(final String key);

    Long sadd(final String key, final String value);
    Boolean sismember(final String key, final String member);
    Long expire(final String key,final int seconds);

    Long del(final String key);
}
