java-example ![alt tag](https://api.travis-ci.org/phishman3579/java-algorithms-implementation.svg?branch=master)
==============================

Java Example project for Spring Boot. 个人案例项目，基于Spring Boot，结合Java基础、多线程、集合、Netty、MyBatis-Plus（代码自动生成器，支持多数据源）、定时任务、文件上传下载、Kafka消息中间件、Redis分布式锁、Redisson高并发处理。

## Table of Contents
+ [Environment](https://github.com/loveisontheway/java-example#Environment)
+ [Project](https://github.com/loveisontheway/java-example#Project)
+ [Generator](https://github.com/loveisontheway/java-example#Generator)
+ [Solution](https://github.com/loveisontheway/java-example#Solution)

## Environment
+ `JDK:` 1.8+
+ `Tomcat:` 9.0.x
+ `Spring Boot:` 2.2.x
+ `Mybatis-Plus:` 3.3.2
+ `Thymeleaf:` 2.2.x
+ `lombok:` 1.18.x
+ `Redis:` 2.2.x
+ `Redisson:` 3.14.x
+ `Kafka:` 2.3.x

## Project
| `package name` | `description` |
| :------ | :------ |
| com.muxi.java.example.basic | Java基础案例（8大基本数据类型、abstract、interface、装箱拆箱） |
| com.muxi.java.*******.config | 配置信息：定时任务 Scheduler、多线程 MultiThread、代码自动生成器 MybatisPlus |
| com.muxi.java.*******.consts | 常量类 |
| com.muxi.java.*******.domain | 实体类 |
| com.muxi.java.*******.enums | 枚举类 |
| com.muxi.java.*******.exception | 异常类 |
| com.muxi.java.*******.generator | Mybatis-Plus代码自动生成器 |
| com.muxi.java.*******.io | IO流，文件上传下载 |
| com.muxi.java.*******.kafka | 集成kafka，生产者消费者案例 |
| com.muxi.java.*******.list | List集合操作案例 |
| com.muxi.java.*******.mapper | Mapper数据访问持久层 |
| com.muxi.java.*******.netty | 网络编程 |
| com.muxi.java.*******.office | 文档，POI，Spire |
| com.muxi.java.*******.redis | 简单的Redis分布式锁，基于秒杀系统库存数实现 |
| com.muxi.java.*******.redisson | 基于 Redis的分布式锁 Redisson |
| com.muxi.java.*******.service | Service接口 |
| com.muxi.java.*******.service.impl | 业务逻辑处理 |
| com.muxi.java.*******.task | 定时任务 |
| com.muxi.java.*******.thread | 多线程相关案例 |
| com.muxi.java.*******.utils | 工具类 |
| com.muxi.java.*******.web | Controller层 |

## Solution
+ Kubernetes（K8S） ➔ 部署需要大量服务器（适合大型项目）
+ 车辆历史轨迹 ➔ Kafka（中间件）➔ 分发数据，读（Elasticsearch）| 写（MySQL）
+ GPS ➔ Kafka ➔ Elasticsearch | MySQL
+ Redis 解决缓存失效问题，也要注重缓存不断写入问题
+ 秒杀模拟测试地址：http://localhost:8099/test

## Generator
- 在运行`JavaExampleApplication.main()`方法之前，先创建数据库表结构和数据，执行`db.sql`文件即可（库、表、数据自动生成）
- 在`test.java.com.muxi.java.example`包下，`JavaExampleApplicationTests.java`类根据表名逆向生成。
- `sys_role`：表名
```java
@SpringBootTest
class JavaExampleApplicationTests {

	@Test
	void contextLoads() {
		MysqlGenerator generator = new MysqlGenerator();
		generator.generatorDefault("sys_role");
	}

}
```