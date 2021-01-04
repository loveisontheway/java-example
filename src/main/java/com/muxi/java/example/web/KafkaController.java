package com.muxi.java.example.web;

import com.muxi.java.example.kafka.Producer;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description
 *
 * @author jl.jiang 2021/1/4
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Resource
    private Producer producer;

    @GetMapping("send")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public void send() {
        producer.send("this is a test kafka topic message!");
    }

}
