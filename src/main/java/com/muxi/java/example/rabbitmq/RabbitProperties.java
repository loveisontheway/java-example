package com.muxi.java.example.rabbitmq;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq连接信息
 *
 * @author jl.jiang 2021/3/18
 */
@Data
@ToString
@Configuration
public class RabbitProperties {

    /**
     * rabbitmq 服务器地址
     */
    @Value("${spring.rabbitmq.host}")
    private String host;

    /**
     * rabbitmq 服务器端口
     */
    @Value("${spring.rabbitmq.port}")
    private int port;

    /**
     * rabbitmq 账号
     */
    @Value("${spring.rabbitmq.username}")
    private String username;

    /**
     * rabbitmq 密码
     */
    @Value("${spring.rabbitmq.password}")
    private String password;
}

