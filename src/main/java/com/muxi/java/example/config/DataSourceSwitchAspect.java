package com.muxi.java.example.config;

import com.muxi.java.example.enums.DBTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(value = -100)
@Slf4j
@Aspect
public class DataSourceSwitchAspect {
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
