package com.muxi.java.example.generator;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * 数据库配置
 * </p>
 *
 * @author whh
 */
@Data
public class DBSetting {
    @Value("${spring.datasource.driver-class-name}")
    private String driverName;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.url}")
    private String url;
}
