package com.example.springboot.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 59315
 * @Date: 2020/6/20 20:37
 * @Description: 使用redis模拟数据库
 */
@RestController
@RequestMapping("/api")
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Redisson redisson;

    /**
     * 利用redis单线程特性
     * 1、死锁问题，在finally中释放锁
     * 2、down机问题，超时设置 (原子性)
     * 3、解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。UUID
     *   线程A：线程A获得锁，总时间15s（10s 后锁超时自动释放了，还没有执行finally释放锁的代码）
     *   线程B：线程B尝试获取锁，成功，总时间8s（5s后，线程A执行finally代码把线程B的锁释放了。）
     *   线程C：线程C尝试获取锁，成功，总时间5s（3s后，线程B执行finally代码把线程C的锁释放了。）
     * 4、业务逻辑没有执行完，锁就超时了？ 锁10秒，启动另一个分线程，定时器每10/3秒延时锁10秒
     * redisson
     */
    @GetMapping("/deduct-stock")
    public String deductStock() {
        //集群 分布式锁
        String lockKey = "product_101";
        String uuid = UUID.randomUUID().toString();
        try {
            // jedis.setnx(k, v)
            Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uuid, 30, TimeUnit.SECONDS);
            if (!aBoolean) {
                return "error_code";
            }
            // jedis.get(key);
            RLock lock = redisson.getLock(lockKey);lock.lock();
            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                int realStock = stock - 1;
                // jedis.set(key, value);
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减库存成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减库存失败，库存不足");
            }
        } finally {
            if (uuid.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                stringRedisTemplate.delete(lockKey);
            }
        }
        return "end";
    }
//    @GetMapping("/deduct-stock")
//    public String deductStock() {
//        // 单机 jvm级别的锁，控制不住超卖问题
//        synchronized (this) {
//            int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("stock")); // jedis.get(key);
//            if (stock > 0) {
//                int realStock = stock - 1;
//                stringRedisTemplate.opsForValue().set("stock", realStock + ""); // jedis.set(key, value);
//                System.out.println("扣减库存成功，剩余库存：" + realStock);
//            } else {
//                System.out.println("扣减库存失败，库存不足");
//            }
//        }
//        //集群 分布式锁
//
//        return "end";
//    }

}
