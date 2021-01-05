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
| com.muxi.****.*******.config | 配置信息：定时任务 Scheduler、多线程 MultiThread、代码自动生成器 MybatisPlus |
| com.muxi.****.*******.consts | 常量类 |
| com.muxi.****.*******.domain | 实体类 |
| com.muxi.****.*******.enums | 枚举类 |
| com.muxi.****.*******.generator | Mybatis-Plus代码自动生成器 |
| com.muxi.****.*******.io | IO流，文件上传下载 |
| com.muxi.****.*******.kafka | 集成kafka，生产者消费者案例 |
| com.muxi.****.*******.list | List集合操作案例 |
| com.muxi.****.*******.mapper | Mapper数据访问持久层 |
| com.muxi.****.*******.netty | 网络编程 |
| com.muxi.****.*******.office | 文档，POI |
| com.muxi.****.*******.redis | 简单的Redis分布式锁，基于秒杀系统库存数实现 |
| com.muxi.****.*******.redisson | 基于 Redis的分布式锁 Redisson |
| com.muxi.****.*******.service | Service接口 |
| com.muxi.****.*******.service.impl | 业务逻辑处理 |
| com.muxi.****.*******.task | 定时任务 |
| com.muxi.****.*******.thread | 多线程相关案例 |
| com.muxi.****.*******.utils | 工具类 |
| com.muxi.****.*******.web | Controller层 |

## Solution
+ Kubernetes（K8S） ➔ 部署需要大量服务器（适合大型项目）
+ 车辆历史轨迹 ➔ Kafka（中间件）➔ 分发数据，读（Elasticsearch）| 写（MySQL）
+ GPS ➔ Kafka ➔ Elasticsearch | MySQL
+ Redis 解决缓存失效问题，也要注重缓存不断写入问题

## Generator
- 在运行`JavaExampleApplication.main()`方法之前，先创建数据库表结构和数据，执行`db.sql`文件即可（库、表、数据自动生成）
- 在`com.muxi.java.example`包下，`JavaExampleApplicationTests.java`类根据表名逆向生成。
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