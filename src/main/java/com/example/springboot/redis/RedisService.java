package com.example.springboot.redis;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description: sringboot中的 使用redistemplate操作redis
 * @author: Ye wubin
 * @date: 2018/10/18 21:10
 */
@Service
public class RedisService {

    private final Logger log = LoggerFactory.getLogger(RedisService.class);

    /**
     * 默认过期时间 60分钟
     */
    private final static long DEFALUT_TIMEOUT = 60L;

    private static final String KEY = "serialNumber";
    private static final String NOBIT = "000000";

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    /**
     * 保存
     *
     * @param key
     * @param value
     */
    public void put(final String key, final Object value) {
        put(key, value, DEFALUT_TIMEOUT);
    }

    /**
     * 保存
     *
     * @param key
     * @param value
     * @param timeout
     */
    public void put(final String key, final Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MINUTES);
    }

    /**
     * 保存
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    public void put(final String key, final Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 获取保存
     *
     * @param key
     * @param value
     * @param timeout
     * @param timeUnit
     */
    public Object getAndSet(final String key, final Object value, long timeout, TimeUnit timeUnit) {
        Object obj = redisTemplate.opsForValue().getAndSet(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
        return obj;
    }

    /**
     * 删除
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 获取
     *
     * @param key
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void expire(String key, long seconds) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("redis key为空");
        }
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }


}
