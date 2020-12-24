package com.demo.mysqlredis.service.impl;

import com.demo.mysqlredis.service.RedisService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author zhangchunguang
 * @data 2020/12/24 7:58 下午
 */
@Component
public class RedisServiceImpl implements RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Autowired
    private JedisPool jedisPool;

    @Override
    public Jedis getResoures() {
        return jedisPool.getResource();
    }

    @Override
    public void returnResources(Jedis jedis) {
        if (null != jedis) {
            jedis.close();
        }
    }

    /**
     * 设置key，value
     *
     * @param key 键
     * @param value 值
     * @return String
     */
    @Override
    public String set(final String key, final String value) {
        Jedis jedis = null;
        try {
            jedis = getResoures();
            return jedis.set(key.getBytes(), value.getBytes());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return "error";
        } finally {
            returnResources(jedis);
        }
    }

    /**
     * 获取key，value
     *
     * @param key 键
     * @return String，如果没有key，返回null
     */
    @Override
    public String get(final String key) {
        Jedis jedis = null;
        try {
            jedis = getResoures();
            byte[] rtn = jedis.get(key.getBytes());
            if (null != rtn) {
                return new String(rtn);
            }else{
                return null;
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        } finally {
            returnResources(jedis);
        }
    }

    /**
     * 设置有时间限制的key，value
     *
     * @param key 键
     * @param value 值
     * @param expire 寿命，秒
     * @return string
     */
    @Override
    public String setx(final String key, final String value, final Long expire) {
        Jedis jedis = null;
        try {
            jedis = getResoures();
            return jedis.setex(key.getBytes(), expire.intValue(), value.getBytes());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return "error";
        } finally {
            returnResources(jedis);
        }
    }

    /**
     * 设置有时间限制的key，value,如果以存在建，返回0，如果创建成功，返回1。
     *
     * @param key 键
     * @param value 值
     * @param expire 寿命，秒
     * @return 0-失败，1-成功
     */
    @Override
    public String setxnew(final String key, final String value, final Long expire) {
        Jedis jedis = null;
        try {
            jedis = getResoures();
            return jedis.set(key.getBytes(), value.getBytes(), "NX".getBytes(), "EX".getBytes(), expire);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return "error";
        } finally {
            returnResources(jedis);
        }
    }

    /**
     * 添加set数据的key
     * @param key
     * @param value
     * @return
     */
    @Override
    public Long sadd(final String key, final String value) {
        Jedis jedis = null;
        try {
            jedis = getResoures();
            return jedis.sadd(key.getBytes(), value.getBytes());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return -1L;
        } finally {
            returnResources(jedis);
        }
    }

    /**
     * 判断member是否是set类型数据key中的成员
     * @param key 键值
     * @param member 成员名
     * @return
     */
    @Override
    public Boolean sismember(final String key, final String member) {
        Jedis jedis = null;
        try {
            jedis = getResoures();
            return jedis.sismember(key.getBytes(), member.getBytes());
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return false;
        } finally {
            returnResources(jedis);
        }
    }

    /**
     * 设置键的寿命，秒数
     * @param key
     * @param seconds
     * @return
     */
    @Override
    public Long expire(final String key,final int seconds) {
        Jedis jedis = null;
        try {
            jedis = getResoures();
            return jedis.expire(key,seconds);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return -1L;
        } finally {
            returnResources(jedis);
        }
    }

    @Override
    public Long del(String key) {
        Jedis jedis = null;
        try {
            jedis = getResoures();
            Long rtn = jedis.del(key);
            if (null != rtn) {
                return rtn;
            }else{
                return null;
            }
        } catch (Exception e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        } finally {
            returnResources(jedis);
        }
    }
}
