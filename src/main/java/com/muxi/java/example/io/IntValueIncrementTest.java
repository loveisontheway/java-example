package com.muxi.java.example.io;

import java.util.concurrent.*;

/**
 * int++ 线程不安全
 *
 * @author jjl
 * @date 2022/5/13
 */
public class IntValueIncrementTest {

    public static int value = 0;

    public static void main(String[] args) throws InterruptedException {
        // 设置核心池大小
        int corePoolSize = 10000;

        // 设置线程池最大能接受多少线程
        int maximumPoolSize = 10000;

        // 当前线程数大于corePoolSize、小于maximumPoolSize时，超出corePoolSize的线程数的生命周期
        long keepActiveTime = 200;

        // 设置时间单位，秒
        TimeUnit timeUnit = TimeUnit.SECONDS;

        // 设置线程池缓存队列的排队策略为FIFO，并且指定缓存队列大小为5
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(5);

        // 创建ThreadPoolExecutor线程池对象，并初始化该对象的各种参数
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepActiveTime, timeUnit, workQueue);

        for (int i = 0; i < 10000; i++) {
            executor.execute(() -> {
                for (int j = 0; j < 4; j++) {
                    System.out.println(value++);
                }
            });
        }
        executor.shutdown();
        Thread.sleep(3000);
        System.out.println("最终结果是" + value);
    }

}
