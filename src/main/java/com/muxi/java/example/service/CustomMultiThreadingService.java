package com.muxi.java.example.service;

import com.muxi.java.example.config.CustomAsyncScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 创建线程任务服务
 */
@Service
public class CustomMultiThreadingService {

    private Logger logger = LoggerFactory.getLogger(CustomMultiThreadingService.class);

    /**
     * 通过@Async注解表明该方法是一个异步方法，
     * 如果注解在类级别上，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     */
    @Async
    public void executeAysncTask1(Integer i){
        logger.info("CustomMultiThreadingService ==> executeAysncTask1 method: 执行异步任务{} ", i);
    }

    /**
     * 通过@Async注解表明该方法是一个异步方法，
     * 如果注解在类级别上，则表明该类所有的方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor作为TaskExecutor
     */
    @Async
    public void executeAysncTask2(Integer i){
        logger.info("CustomMultiThreadingService ==> executeAsyncTask2 method: 执行异步任务{} ", i);
    }

    /**
     * 异步线程调度管理器创建线程任务
     */
    public void executeAsyncTask3(Integer i){
        CustomAsyncScheduler.getInstance().getChnlBackendQueryPool().execute(new Runnable() {
            @Override
            public void run() {
                logger.info("CustomMultiThreadingService ==> executeAsyncTask3 method: 执行异步任务{} ", i);
            }
        });

    }
}
