package com.muxi.java.example.config;

import com.muxi.java.example.enums.DBTypeEnum;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = -100)
@Aspect
public class DataSourceSwitchAspect {

    private static final Logger log = LoggerFactory.getLogger(DataSourceSwitchAspect.class);

    @Pointcut("execution(* com.muxi.java.example.mapper..*.*(..))")
    private void defaultDBAspect() {
    }

    @Pointcut("execution(* com.muxi.java.example.mapper.other..*.*(..))")
    private void otherDBAspect() {
    }

    @Before("defaultDBAspect()")
    public void db1() {
        log.info("切换到defaultDB 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.DEFAULTDB);
    }

    @Before("otherDBAspect()")
    public void db2() {
        log.info("切换到otherDB 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.OTHERDB);
    }

}
