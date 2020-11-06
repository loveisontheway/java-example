package com.muxi.java.example.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TaskTest {

    //输出时间格式
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    // 第一次执行前延时5秒启动,每次任务结束后15秒再次启动
//    @Scheduled(initialDelay = 5000, fixedDelay = 15000)
    private void sayHello() {
        System.out.println(format.format(new Date()) + "向宇宙发出了一声问候:Hello World!");
    }

}
