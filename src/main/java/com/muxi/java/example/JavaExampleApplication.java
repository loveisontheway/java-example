package com.muxi.java.example;

import com.muxi.java.example.netty.server.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetSocketAddress;
import java.util.Scanner;

@MapperScan({"com.muxi.java.example.mapper", "com.muxi.java.example.mapper.other"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling   // 开启定时任务的配置
public class JavaExampleApplication {

    public static void main(String[] args) {

/*        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入Tomcat端口号: ");
        String port = scanner.nextLine();
        new SpringApplicationBuilder(JavaExampleApplication.class)
                .properties("server.port=" + port).run(args);*/

        SpringApplication.run(JavaExampleApplication.class, args);
        // 启动Netty服务端
        NettyServer nettyServer = new NettyServer();
        nettyServer.start(new InetSocketAddress("127.0.0.1", 8090));
        // 模拟启动Netty客户端（com.muxi.java.example.netty.client相关放到其它项目代码进行调用）
//        NettyClient nettyClient = new NettyClient();
//        nettyClient.start();
    }

}
