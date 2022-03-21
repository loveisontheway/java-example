package com.muxi.java.example;

import com.muxi.java.example.netty.server.NettyServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.List;
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

    /**
     * 第一步：注入bean
     * 必须 new 一个RestTemplate并放入spring容器当中,否则启动时报错
     * ------------------------
     * 第二步：调用方法
     * 调用第三方接口的controller里面注入我们RestTemplate了
     * @Autowired
     * RestTemplate restTemplate;
     */
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(30 * 1000);
        httpRequestFactory.setConnectTimeout(30 * 3000);
        httpRequestFactory.setReadTimeout(30 * 3000);
        restTemplate.setRequestFactory(httpRequestFactory);

        // 修改字符集
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter){
                ((StringHttpMessageConverter)
                        httpMessageConverter).setDefaultCharset(Charset.forName("utf-8"));
                break;
            }
        }
        restTemplate.setRequestFactory(httpRequestFactory);
        return restTemplate;
    }


}
