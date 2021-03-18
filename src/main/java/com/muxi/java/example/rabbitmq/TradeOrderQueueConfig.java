package com.muxi.java.example.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 初始化队列、交换器等并绑定关系
 *
 * @author jl.jiang 2021/3/18
 */
@Component
public class TradeOrderQueueConfig {


    private final static Logger logger = LoggerFactory.getLogger(TradeOrderQueueConfig.class);

    /**
     * 创建队列
     * Queue 可以有4个参数
     * String name: 队列名
     * boolean durable: 持久化消息队列，rabbitmq 重启的时候不需要创建新的队列，默认为 true
     * boolean exclusive: 表示该消息队列是否只在当前的connection生效，默认为 false
     * boolean autoDelete: 表示消息队列在没有使用时将自动被删除，默认为 false
     * Map<String, Object> arguments:
     *
     * @return
     */
    @Bean(name = "queue")
    public Queue queue() {
        logger.info("queue : {}", RabbitMqKey.TRADE_ORDER_QUEUE);
        // 队列持久化
        return new Queue(RabbitMqKey.TRADE_ORDER_QUEUE, true);
    }

    /**
     * 创建一个 Fanout 类型的交换器
     * <p>
     * rabbitmq中，Exchange 有4个类型：Direct，Topic，Fanout，Headers
     * Direct Exchange：将消息中的Routing key与该Exchange关联的所有Binding中的Routing key进行比较，如果相等，则发送到该Binding对应的Queue中；
     * Topic Exchange：将消息中的Routing key与该Exchange关联的所有Binding中的Routing key进行对比，如果匹配上了，则发送到该Binding对应的Queue中；
     * Fanout Exchange：直接将消息转发到所有binding的对应queue中，这种exchange在路由转发的时候，忽略Routing key；
     * Headers Exchange：将消息中的headers与该Exchange相关联的所有Binging中的参数进行匹配，如果匹配上了，则发送到该Binding对应的Queue中；
     *
     * @return
     */
    @Bean(name = "fanoutExchange")
    public FanoutExchange fanoutExchange() {
        logger.info("exchange : {}", RabbitMqKey.TRADE_ORDER_EXCHANGE);
        return new FanoutExchange(RabbitMqKey.TRADE_ORDER_EXCHANGE);
    }

    /**
     * 把队列（Queue）绑定到交换器（Exchange）
     * topic 使用路由键（routingKey）
     *
     * @return
     */
    @Bean
    Binding fanoutBinding(@Qualifier("queue") Queue queue,
                          @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
}

