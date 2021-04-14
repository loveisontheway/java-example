package com.muxi.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁 - redisson实现
 *
 * @author jl.jiang 2021/4/14
 */
@RestController
public class RedisController {

    @Resource
    private Redisson redisson;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redis")
    public String redis() {

        /*
        // 1：synchronized内置锁，这种方式，在单体架构（项目war包部署在一台Tomcat下）
        // but，在分布式架构中不可行
//        synchronized (this) {

        String lockKey = "lockKey";
        // 为每个线程生成uuid（唯一标识）
//        String uuid = UUID.randomUUID().toString();
        // 3：try（捕获异常）finally（始终释放锁）

        // redisson拿锁对象
        RLock redissonLock = redisson.getLock(lockKey);

        try {
            // jedis.setntx(key, value)
            // result: true(有key) false(无key)
            // 2：加锁
//            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "muxi");
            // 4：解决死锁问题，设置过期时间
//            stringRedisTemplate.expire(lockKey, 10, TimeUnit.SECONDS);

            // 如果在创建锁时宕机，来不及设置锁过期时间，也会造成死锁，缺乏原子性
            // 5：解决原子性问题，两行代码合并成一行代码（redis提供）
            *//*
            Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, uuid, 10, TimeUnit.SECONDS);
            if (!result) {
                return "error code";
            }
             *//*

            // 7：解决锁过期时间问题（在设置过期时间范围内，无法执行完代码流程）
            // 思路：锁续命（定时器（每隔10s检查是否持有锁，如果持有则延长锁的时间））
            // 最终解法：redisson
            // redisson加锁（lua脚本语言(用c语言实现原子性-要么同时执行成功，要么同时执行失败)-嵌入式）
            redissonLock.lock();    // 底层实现类似代码：setIfAbsent(lockKey, uuid, 30, TimeUnit.SECONDS)
            // jedis.get("stock")
//            stringRedisTemplate.opsForValue().set("stock", "60");
            String val = stringRedisTemplate.opsForValue().get("stock");
            int stock = StringUtils.isEmpty(val) ? 0 : Integer.parseInt(val);
            if (stock > 0) {
                int realStock = stock - 1;
                // jedis.set(key, value)
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            // redisson释放锁
            redissonLock.unlock();
            *//*
            // 6：判断是否是自己生成的线程锁，规避销毁其它线程锁
            if (uuid.equals(stringRedisTemplate.opsForValue().get(lockKey))) {
                // 释放锁
                stringRedisTemplate.delete(lockKey);
            }
             *//*
        }
//        }
        return "end";
        */

        /** 最终优化版本 */
        String lockKey = "lockKey";
        // redisson获取锁对象
        RLock redissonLock = redisson.getLock(lockKey);
        try {
            // redisson加锁（lua脚本语言(用c语言实现原子性-要么同时执行成功，要么同时执行失败)-嵌入式）
            redissonLock.lock();    // 底层实现类似代码：setIfAbsent(lockKey, uuid, 30, TimeUnit.SECONDS)
            // 业务逻辑代码处理
            String val = stringRedisTemplate.opsForValue().get("stock");
            int stock = StringUtils.isEmpty(val) ? 0 : Integer.parseInt(val);
            if (stock > 0) {
                int realStock = stock - 1;
                stringRedisTemplate.opsForValue().set("stock", realStock + "");
                System.out.println("扣减成功，剩余库存：" + realStock);
            } else {
                System.out.println("扣减失败，库存不足");
            }
        } finally {
            // redisson释放锁
            redissonLock.unlock();
        }
        return "end";
    }
}
