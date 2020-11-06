package com.muxi.java.example.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * 方案3：添加一个配置类即可(实现SchedulingConfigurer接口),定时任务类或方法不用做任何改变
 * 多线程执行定时任务
 */
/*@Configuration
public class SchedulingConfig implements SchedulingConfigurer {
    *//**
     * @描述: 所有的定时任务都放在一个线程池中,定时任务启动时使用不同的线程
     * @param taskRegistrar
     * @日期 2019年5月27日
     *//*
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //设定一个定时任务线程池,数量为3
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(3));
    }
}*/
