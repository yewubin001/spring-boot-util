package com.example.springboot.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 59315
 * @Date: 2020/6/20 20:37
 * @Description: 使用redis模拟数据库
 */
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/deduct-stock")
    public String deductStock() {
        // 单机 jvm级别的锁
        synchronized (this){
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get(key);
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key, value);
                System.out.println("扣减库存成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减库存失败，库存不足");
            }
        }
        //集群 分布式锁



        return "end";
    }

}
