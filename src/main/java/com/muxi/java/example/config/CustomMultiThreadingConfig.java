package com.muxi.java.example.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 基于 Spring 实现多线程
 * 配置类实现AsyncConfigurer接口，并重写getAsyncExecutor方法，并返回一个ThreadPoolTaskExecutor，
 * 这样我们就获得一个基于线程池TaskExecutor
 */
@Configuration
@ComponentScan("com.muxi.java.example")
@EnableAsync    // 利用@EnableAsync注解开启异步任务支持
public class CustomMultiThreadingConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        pool.setCorePoolSize(5);
        // 设置最大线程数
        pool.setMaxPoolSize(10);
        // 设置队列容量
        pool.setQueueCapacity(25);
        // 设置线程活跃时间（秒）
        pool.setKeepAliveSeconds(60);
        // 设置默认线程名称
        pool.setThreadNamePrefix("tyb-thread-");
        pool.initialize();
        return pool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
