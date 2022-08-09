package com.muxi.java.example.redisson;

import com.baomidou.mybatisplus.extension.api.R;
import org.redisson.Redisson;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Redisson Example
 *
 * @author jjl
 * @date 2022/8/8
 */
public class RedissonExample {

    public static void main(String[] args) throws InterruptedException {
        /** 使用 RList 操作 Redis 列表 */
/*        // 默认：127.0.0.1:6379
        RedissonClient client = Redisson.create();

        // RList extends List
        RList<String> nameList = client.getList("nameList");
        nameList.clear();
        nameList.add("bingo");
        nameList.add("yanglbme");
        nameList.add("https://github.com/yanglbme");
        nameList.remove(-1);

        boolean contains = nameList.contains("yanglbme");
        System.out.println("List size: " + nameList.size());
        System.out.println("Is list contains name 'yanglbme': " + contains);
        nameList.forEach(System.out::println);

        client.shutdown();*/

        /** 使用 RMap 操作 Redis 哈希 */
/*        // 默认连接上127.0.0.1:6379
        RedissonClient client = Redisson.create();
        // RMap 继承了 java.util.concurrent.ConcurrentMap 接口
        RMap<String, String> map = client.getMap("personalInfo");
        map.put("name", "yanglbme");
        map.put("address", "Shenzhen");
        map.put("link", "https://github.com/yanglbme");

        boolean contains = map.containsKey("link");
        System.out.println("Map size: " + map.size());
        System.out.println("Is map contains key 'link': " + contains);
        String value = map.get("name");
        System.out.println("Value mapped by key 'name': " + value);
        boolean added = map.putIfAbsent("link", "https://doocs.github.io") == null;
        System.out.println("Is value mapped by key 'link' added: " + added);
        client.shutdown();*/

        /** 使用 RLock 实现 Redis 分布式锁 */
/*        // 默认连接上127.0.0.1:6379
        RedissonClient client = Redisson.create();
        // RLock 继承了 java.util.concurrent.locks.Lock 接口
        RLock lock = client.getLock("lock");

        lock.lock();
        System.out.println("lock acquired");

        Thread t = new Thread(() -> {
            RLock lock1 = client.getLock("lock");
            lock1.lock();
            System.out.println("lock acquired by thread");
            lock1.unlock();
            System.out.println("lock released by thread");
        });

        t.start();
        t.join(1000);

        lock.unlock();
        System.out.println("lock released");
        t.join();

        client.shutdown();*/

        /** 使用 RAtomicLong 实现 Redis 原子操作 */
        // 默认连接上127.0.0.1:6379
        RedissonClient client = Redisson.create();
        RAtomicLong atomicLong = client.getAtomicLong("myLong");

        System.out.println("Init value: " + atomicLong.get());

        atomicLong.incrementAndGet();
        System.out.println("Current value: " + atomicLong.get());

        atomicLong.addAndGet(10L);
        System.out.println("Final value: " + atomicLong.get());

        client.shutdown();


        /** Redis Lock */
        String lockKey = "lockKey";
        String clientId = UUID.randomUUID().toString();
        RedisTemplate redisTemplate = new RedisTemplate();
        try {
            Boolean result = redisTemplate.opsForValue().setIfAbsent(lockKey, clientId, 30, TimeUnit.SECONDS);
            /*
          	判断是否新增了key 如果没有新增 则表明有线程在占用这个接口，接口暂时不可用，
          	直接返回错误；如果有新增，则表明没有线程占用接口，可继续执行。
            */
            if (!result) {
//                R.failed().setMsg("请勿频繁刷新");
                System.out.println("请勿频繁刷新");
            }
            System.out.println("这里执行业务代码");
        } finally {
            if (clientId.equals(redisTemplate.opsForValue().get(lockKey))) {
                redisTemplate.delete(lockKey);
            }
        }

    }
}
