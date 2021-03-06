package com.muxi.java.example.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 确认信息
 *
 * @author jl.jiang 2021/3/18
 */
@Component
public class RabbitAck implements RabbitTemplate.ConfirmCallback {

    private final static Logger logger = LoggerFactory.getLogger(RabbitAck.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        // 指定 ConfirmCallback
        // rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("ACK --- MQ message id: {}" + correlationData);
        if (ack) {
            logger.info("ACK --- Message sent confirmation success！");
        } else {
            logger.info("ACK --- MQ message id: {}", correlationData.getId());
            logger.info("ACK --- MQ confirmetion: {}", ack);
            logger.info("ACK --- Message sending confirmation failed, reason for failure:" + cause);
        }
    }
}

