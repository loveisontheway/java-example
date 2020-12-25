package com.muxi.java.example.web;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Java 基于 Redis的分布式锁 Redisson基本用法
 *
 * @author jl.jiang 2020/12/25
 */
@RestController
public class RedissonController {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 分布式锁键
     */
    private final static String LOCK_KEY = "LockKey";

    @GetMapping("/lock")
    public Map<String, String> lock() {

        final Map<String, String> result = new HashMap<>(1);

        /**
         * 获取锁对象
         * */
        final RLock lock = redissonClient.getLock(LOCK_KEY);

        System.out.println("当前线程状态: " + lock.isLocked());

        if (lock.isLocked()) {
            result.put("message", "当前线程被锁!! " + lock.isLocked());
        } else {

            /**
             * 加锁以后4秒钟自动解锁(为了防止死锁的产生)
             * */
            lock.lock(4, TimeUnit.SECONDS);
            try {
                /**
                 * 在此处理业务
                 * */
                //Thread.sleep(3900L);

                result.put("message", "处理逻辑完成!! " + LocalDateTime.now());
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());

                result.put("message", "处理逻辑失败!! " + LocalDateTime.now());
            } finally {

                System.out.println("当前时间: " + LocalDateTime.now());
                if (lock.isLocked()) {
                    lock.unlock();
                } else {
                    System.out.println("未正常解锁!!");
                }
            }
        }

        return result;
    }

}


