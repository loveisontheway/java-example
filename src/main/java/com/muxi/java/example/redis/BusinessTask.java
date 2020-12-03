package com.muxi.java.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Test
 *
 * @author jl.jiang 2020/12/2
 */
@Component
public class BusinessTask {
    private final static String LOCK_ID = "java-test";
    @Autowired
    private DistributedLock distributedLock;

    @Scheduled(cron = "0/10 * * * * ? ")
    public void doSomething() {
        boolean lock = distributedLock.getLock(LOCK_ID, 10 * 1000);
        if (lock) {
            System.out.println("执行任务");
            distributedLock.releaseLock(LOCK_ID);
        } else {
            System.out.println("没有抢到锁");
        }
    }
}