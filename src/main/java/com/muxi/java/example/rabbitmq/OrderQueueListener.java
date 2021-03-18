package com.muxi.java.example.rabbitmq;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收消息
 *
 * @author jl.jiang 2021/3/18
 */
@Component
public class OrderQueueListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderQueueListener.class);

    /**
     * 接收消息
     *
     * @param message
     */
    @RabbitListener(queues = RabbitMqKey.TRADE_ORDER_QUEUE)
    public void process(Message message) {
        try {
            String msg = new String(message.getBody());
            if (StringUtils.isBlank(msg)) {
                logger.warn("接收的数据为空");
                return;
            }
//            System.out.println(msg);
            System.out.println("服务1接收到的数据：" + msg);
        } catch (Exception e) {
            logger.warn("处理接收到数据，发生异常：{}", e.getMessage());
            e.printStackTrace();
        }
    }
}

