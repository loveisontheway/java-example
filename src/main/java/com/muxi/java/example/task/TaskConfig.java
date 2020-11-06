package com.muxi.java.example.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * 方案2：添加一个配置类即可,定时任务类或方法不用做任何改变 （推荐）
 * 多线程执行定时任务
 */
@Configuration
public class TaskConfig {

    /**
     * 所有的定时任务都放在一个线程池中,定时任务启动时使用不同的线程
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        // 设置scheduler执行线程为3个
        scheduler.setPoolSize(3);
        return scheduler;
    }

}
