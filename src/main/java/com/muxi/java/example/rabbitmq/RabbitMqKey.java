package com.muxi.java.example.rabbitmq;

/**
 * 定义队列、交换器等名称（这里有新增了订单相关队列和交换器）
 *
 * @author jl.jiang 2021/3/18
 */
public class RabbitMqKey {

    /**
     * 订单-队列
     */
    public static final String TRADE_ORDER_QUEUE = "trade-order-queue";

    /**
     * 订单-交换器
     */
    public static final String TRADE_ORDER_EXCHANGE = "trade-order-exchange";

}

