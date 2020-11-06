java-example ![alt tag](https://api.travis-ci.org/phishman3579/java-algorithms-implementation.svg?branch=master)
==============================

Java Example project for Spring Boot. 个人案例项目，基于Spring Boot，结合Java基础、多线程、集合、Netty、MyBatis-Plus（代码自动生成器）、定时任务。

## Table of Contents
+ [Environment](https://github.com/loveisontheway/java-example#Environment)
+ [Project](https://github.com/loveisontheway/java-example#Project)
+ [SSL](https://github.com/loveisontheway/java-example#Generator)

## Environment
+ `JDK:` 1.8+
+ `Tomcat:` 9.0.x
+ `Spring Boot:` 2.2.x
+ `Mybatis-Plus:` 3.3.2
+ `lombok:` 1.18.x

## Project
| `package name` | `description` |
| :------ | :------ |
| com.muxi.java.example.basic | Java基础案例（8大基本数据类型、abstract、interface、装箱拆箱） |
| com.muxi.java.example.generator | Mybatis-Plus代码自动生成器 |
| com.muxi.java.example.config | 配置信息：定时任务 Scheduler、多线程 MultiThread、代码自动生成器 MybatisPlus |
| com.muxi.java.example.list | List集合操作案例 |
| com.muxi.java.example.mapper | Mapper数据访问持久层 |
| com.muxi.java.example.model | 实体类Bean |
| com.muxi.java.example.netty | 网络编程 |
| com.muxi.java.example.service | Service接口 |
| com.muxi.java.example.service.impl | 业务逻辑处理 |
| com.muxi.java.example.task | 定时任务 |
| com.muxi.java.example.thread | 多线程相关案例 |
| com.muxi.java.example.utils | 工具包类 |
| com.muxi.java.example.web | Controller层 |

## Generator
在`com.muxi.java.example`包下，`JavaExampleApplicationTests.java`类根据表名逆向生成。
```java
@SpringBootTest
class JavaExampleApplicationTests {

	@Test
	void contextLoads() {
		MysqlGenerator generator = new MysqlGenerator();
		generator.generator("user_mp");
	}

}
```