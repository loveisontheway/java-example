package com.muxi.java.example.generator;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 数据库配置
 */
@Data
public class DBSetting {
    private String driverName;
    private String userName;
    private String password;
    private String url;
}
