package com.ppl.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author ppliang
 * @date 2020/12/10 17:10
 */
@Configuration
public class RedisConfig {
    private final static String endWithMS="ms";

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private String timeout;
    @Value("${spring.redis.jedis.pool.max-active}")
    private Integer maxActive;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private String maxWait;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.min-idle}")
    private Integer minIdle;

    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig=new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMaxWaitMillis(getMaxWaitMillis(maxWait));
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, getTimeOut(timeout), password);
        return jedisPool;
    }

    private Integer getTimeOut(String timeout){
        return Integer.valueOf(timeout.substring(0, timeout.length() - 2));
    }
    private Long getMaxWaitMillis(String maxWaitMillis){
        return Long.valueOf(maxWaitMillis.substring(0,maxWaitMillis.length()-2));
    }
}
