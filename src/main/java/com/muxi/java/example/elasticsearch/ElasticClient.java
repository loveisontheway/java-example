package com.muxi.java.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * elasticsearch client
 *
 * @author jl.jiang 2021/3/19
 */
@Configuration
public class ElasticClient {

    /**
     * ES地址,ip:port
     */
    @Value("${spring.elasticsearch.rest.uris}")
    String uris;

    @Bean
    public RestClientBuilder restClientBuilder() {
        return RestClient.builder(makeHttpHost(uris));
    }

    @Bean(name = "highLevelClient")
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        restClientBuilder.setMaxRetryTimeoutMillis(60000);
        return new RestHighLevelClient(restClientBuilder);
    }

    private HttpHost makeHttpHost(String uris) {
        String[] address = uris.split(":");
        String ip = address[0];
        int port = Integer.parseInt(address[1]);
        return new HttpHost(ip, port, "http");
    }
}
