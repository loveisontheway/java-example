package com.muxi.java.example.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskTest2 {

    private final Logger log = LoggerFactory.getLogger(TaskTest2.class);

    //输出时间格式
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    /**
     * 方案1：添加以下代码块,可放置在任意一个类中,整个工程只需要添加一个即可
     */
/*    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        // 设置scheduler执行线程为3个
        scheduler.setPoolSize(3);
        return scheduler;
    }*/

    @Scheduled(cron = "0/55 * * * * ? ")
    private void sayHello(){
        String dateTime = format.format(new Date());
        log.info("{} 向宇宙发出了一声问候: Hello World!", dateTime);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/56 * * * * ? ")
    private void sayHello2(){
        String dateTime = format.format(new Date());
        log.info("{} 向宇宙发出了一声问候: 你好,世界", dateTime);
    }

}
