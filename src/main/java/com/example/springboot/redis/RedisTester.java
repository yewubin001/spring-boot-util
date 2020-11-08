package com.example.springboot.redis;

import com.example.springboot.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

/**
 *
 * 目前主流的开源解决方案有jedis,redission,lettuce三种解决方案
 * redission提供了额外的分布式锁功能
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class RedisTester {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void test1() {
        Jedis resource = jedisPool.getResource();
        resource.set("name", "yewubin");

        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        System.out.println("String--------------------");
        //设置 redis 字符串数据
        jedis.set("redis", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis为: " + jedis.get("redis"));
        System.out.println("list--------------------");
        //存储数据到列表中(List)
        jedis.lpush("list", "Runoob");
        jedis.lpush("list", "Google");
        jedis.lpush("list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("list", 0, 2);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list为: " + list.get(i));
        }
        System.out.println("hash--------------------");
        //存储数据到哈希(Hash)
        Map map = new HashMap<>();
        map.put("name", "redis tutorial");
        map.put("description", "redis basic commands for caching");
        map.put("likes", "20");
        jedis.hmset("hash", map);
        Map hash = jedis.hgetAll("hash");
        Iterator hashIter = hash.keySet().iterator();
        while (hashIter.hasNext()) {
            String key = (String) hashIter.next();
            String value = (String) hash.get(key);
            System.out.println(key + ":" + value);
        }

        System.out.println("set--------------------");
        //存储数据到set
        jedis.sadd("set", "redis", "mongodb", "mysql", "redis");
        Set set = jedis.smembers("set");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println("set为:" + iterator.next());
        }

        System.out.println("sortSet--------------------");

        jedis.zadd("sortSet", 1, "a");
        jedis.zadd("sortSet", 2, "b");
        jedis.zadd("sortSet", 3, "c");

    }

    @Test
    public void test() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("127.0.0.1");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: " + jedis.ping());
        // 获取数据并输出
        Set<String> keys = jedis.keys("*");
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
    }

    @Autowired
    private RedisService redisService;

    @Test
    public void testRedis(){
        redisService.put("name", "yewubin007");
    }
}