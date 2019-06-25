package com.example.springboot.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @description: RedisAutoConfiguration配置类已经定义了redisTemplate的bean,
 * 如果没有自定义redisTemplate，则用自动配置里面的bean
 * @author: Ye wubin
 * @date: 2018/12/26 16:02
 */
@Configuration
public class RedisConfiguration {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setDefaultSerializer(new GenericJackson2JsonRedisSerializerEx());
        template.setEnableDefaultSerializer(true);
        return template;
    }

}
