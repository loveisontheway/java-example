package com.muxi.java.example.web;

import com.muxi.java.example.service.CustomMultiThreadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义多线程Controller
 */
@Controller
@RequestMapping(value = "/multithreading")
public class CustomMultiThreadingController {

    @Autowired
    private CustomMultiThreadingService customMultiThreadingService;

    /**
     * 基于 Spring 多线程调用
     */
    @ResponseBody
    @RequestMapping(value = "/dotask")
    public String doTask() {
        for (int i = 0; i < 10; i++) {
            customMultiThreadingService.executeAysncTask1(i);
            customMultiThreadingService.executeAysncTask2(i);
        }
        return "success";
    }

    /**
     * 基于 Java 多线程调用
     */
    @ResponseBody
    @RequestMapping(value = "/dojob")
    public String doJob() {
        for (int i = 0; i < 10; i++) {
            customMultiThreadingService.executeAysncTask1(i);
            customMultiThreadingService.executeAysncTask2(i);
            customMultiThreadingService.executeAsyncTask3(i);
        }
        return "success";
    }

}
