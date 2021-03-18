package com.muxi.java.example.web;

import com.muxi.java.example.rabbitmq.Sender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * MQ Controller
 * P（Producer）：生产者（寄信人）
 * c（Consumer）：消费者（收信人）
 * x（Exchange）：交换机（邮局）
 * q（Queue）：队列（邮差）
 *
 * @author jl.jiang 2021/3/18
 */
@RestController
public class AmqpController {
    @Resource
    private Sender sender;

    @PostMapping("/producers")
    public void producers(){
        sender.orderSendQueue("Hello World");
    }

    @PostMapping("/batch/producers")
    public void batchProducers(){
        for (int i = 0; i < 100; i++){
            sender.orderSendQueue("Hello World" + i);
        }
    }

    @PostMapping("/send/message")
    public void sendMessage(){
        sender.orderSendExchange("This is project MQ.");
    }
}
