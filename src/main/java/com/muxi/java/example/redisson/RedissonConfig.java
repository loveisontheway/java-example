package com.muxi.java.example.redisson;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redis连接
 *
 * @author jl.jiang 2020/12/25
 */
@Configuration
@EnableCaching
public class RedissonConfig {

    @Value("${spring.redis.host}")
    String REDIS_HOST;

    @Value("${spring.redis.port}")
    String REDIS_PORT;

    @Value("${spring.redis.password}")
    String REDIS_PASSWORD;

    @Value("${spring.redis.database}")
    int REDIS_DATABASE;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        SingleServerConfig r = config.useSingleServer();
        r.setAddress("redis://" + REDIS_HOST + ":" + REDIS_PORT)
                .setDatabase(REDIS_DATABASE)
                .setConnectionMinimumIdleSize(5)
                .setConnectionPoolSize(10);
        if (!StringUtils.isEmpty(REDIS_PASSWORD)) {
            r.setPassword(REDIS_PASSWORD);
        }
        return Redisson.create(config);
    }

}

