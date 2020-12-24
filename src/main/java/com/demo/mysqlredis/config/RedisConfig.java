package com.demo.mysqlredis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhangchunguang
 * @data 2020/12/24 8:17 下午
 */
@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {
    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.database:0}")
    private Integer database;

    @Value("${spring.redis.host:127.0.0.1}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private Integer port;

    //@Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout:1000}")
    private Integer timeout;

    @Value("${thisSystem.periodJobWork:0}")
    private Integer amIPeriodJobWorker;

    @Bean
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        return jedisPoolConfig;
    }

    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = getRedisConfig();
        config.setMaxIdle(5000);
        config.setMaxTotal(10000);
        config.setTestOnBorrow(true);
        logger.info(String.format("----host:%s,port:%d,timeout:%d,database:%d-----",host,port,timeout,database));
        JedisPool pool = new JedisPool(config, host, port, timeout, null, database);
        logger.info("-----------init redis pool ok----------");
        return pool;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        RedisConfig.logger = logger;
    }

    public Integer getDatabase() {
        return database;
    }

    public void setDatabase(Integer database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}
