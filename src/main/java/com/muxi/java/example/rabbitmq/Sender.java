package com.muxi.java.example.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 发送消息类
 *
 * @author jl.jiang 2021/3/18
 */
@Component
public class Sender {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 如果rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
     * 需手动注入
     */
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 订单信息（发送至交换器）
     *
     * @param payload
     * @return
     */
    public String orderSendExchange(Object payload){
        return baseSend(RabbitMqKey.TRADE_ORDER_EXCHANGE, "", payload, null, null);
    }

    /**
     * 订单信息（发送至队列）
     *
     * @param payload
     * @return
     */
    public String orderSendQueue(Object payload){
        return baseSend("", RabbitMqKey.TRADE_ORDER_QUEUE, payload, null, null);
    }

    /**
     * MQ 发送数据基础方法
     *
     * @param exchange  交换器名
     * @param routingKey  队列名
     * @param payload 消息信息
     * @param uniqueMessageId  标示id，不传可自动生成
     * @param messageExpirationTime  持久化时间
     * @return 消息编号
     */
    public String baseSend(String exchange, String routingKey, Object payload, String uniqueMessageId, Long messageExpirationTime) {
        // 生成消息ID
        String finalUniqueMessageId = uniqueMessageId;
        if (StringUtils.isBlank(uniqueMessageId)) {
            uniqueMessageId = UUID.randomUUID().toString();
        }
        logger.info("SEND --- unique message id：{}", uniqueMessageId);

        // 消息属性
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 消息属性中写入消息编号
                message.getMessageProperties().setMessageId(finalUniqueMessageId);
                // 消息持久化时间
                if (!StringUtils.isEmpty(String.valueOf(messageExpirationTime))) {
                    logger.info("设置消息持久化时间：{}", messageExpirationTime);
                    String expiration = messageExpirationTime == null ? null : Long.toString(messageExpirationTime);
                    message.getMessageProperties().setExpiration(expiration);
                }
                // 设置持久化模式
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                return message;
            }
        };

        logger.info("SEND --- messagePostProcessor：{}", messagePostProcessor);

        // 消息
        Message message = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(payload);
            logger.info("发送消息：{}", payload.toString());
            // 转换数据格式
            MessageProperties messageProperties = new MessageProperties();
            messageProperties.setContentEncoding(MessageProperties.CONTENT_TYPE_JSON);
            message = new Message(json.getBytes(), messageProperties);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // correlationData
        CorrelationData correlationData = new CorrelationData(uniqueMessageId);

        /**
         * convertAndSend(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor, CorrelationData correlationData)
         * exchange: 路由
         * routingKey: 绑定key
         * message: 发送消息
         * messagePostProcessor: 消息属性处理类
         * correlationData: 对象内部只有一个 id 属性，用来表示当前消息唯一性
         */
        rabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor, correlationData);

        return finalUniqueMessageId;
    }

}

