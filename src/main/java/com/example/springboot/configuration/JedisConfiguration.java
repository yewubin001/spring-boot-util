package com.example.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * Jedis是Redis官方推荐的Java连接开发工具。要在Java开发中使用好Redis中间件，
 * 必须对Jedis熟悉才能写成漂亮的代码，一般都是使用jedis连接池
 *
 * 自动配置
 *
 * @author
 */
//@Configuration
//@EnableConfigurationProperties({RedisProperties.class})
public class JedisConfiguration {
    @Autowired
    private RedisProperties redisProperties;

    @Bean
    @ConditionalOnMissingBean(name = "jedisPool")
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        Duration timeout = redisProperties.getTimeout();
        int timeoutInt = 5000;
        if (timeout != null) {
            timeoutInt = (int) timeout.getSeconds();
        }
        return new JedisPool(jedisPoolConfig, redisProperties.getHost(),
                redisProperties.getPort(),
                timeoutInt,
                redisProperties.getPassword(),
                redisProperties.getDatabase());
    }

}
