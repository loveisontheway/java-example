package com.muxi.java.example.map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * JDK1.8
 * - ConcurrentHashMap没有用“锁分段”来实现线程安全，
 * - 而是使用CAS算法和synchronized来确保线程安全（每个数组的元素一把锁）
 * - 但是底层segment并没有被删除的。
 * JDK1.7
 * - ConcurrentHashMap使用锁分段技术提升并发访问率
 * - 容器里有很多把锁，每一把锁用于锁容器中其中一部分数据，
 * - 那么当多线程访问容器里不同数据段的数据时，线程间就不会存在锁竞争，
 * - 从而可以有效提高并发访问效率，这就是ConcurrentHashMap所使用的锁分段技术。
 *
 * ConcurrentHashMap的结构
 * - ConcurrentHashMap是由Segment数组结构和HashEntry数据结构组成。
 * - Segment是一种可重入锁（ReentrantLock），在ConcurrentHashMap里扮演锁的角色；
 * - HashEntry则用于存储键值对数据。
 * get操作
 * - Segment的get操作实现非常简单和高效。
 * - 先经过一次再散列，然后使用这个散列值通过散列运算定位到Segment，
 * - 再通过散列算法定位到元素
 * put操作
 * - 由于put操作方法里需要对共享变量进行写操作，
 * - 所以为了线程安全，在操作共享变量时必须加锁。
 * size操作
 * - 如果要统计整个ConcurrentHashMap里元素的大小，
 * - 就必须统计所有Segment里的元素的大小后求和。
 *
 * @author jl.jiang 2021/2/24
 */
public class Concurrent implements Runnable {

    // ConcurrentHashMap可以保证多线程读写操作时的安全
    private static Map<String, Integer> map = new ConcurrentHashMap<>();

    @Override
    public void run() {
        /**
         * 错误写法
         * 这种写法会导致多线程情况下value是线程不安全的，
         * 和ConcurrentHashMap无关
         */
        /*for (int i = 0; i < 1000; i++) {
            int value = map.get("tom");
            value++;
            map.put("tom",value);
        }*/

        /**
         * 正确写法
         * 使用CAS算法操作方式对其进行优化
         */
        for (int i = 0; i < 1000; i++) {
            while (true) {
                int oldValue = map.get("tom");
                int newValue = oldValue + 1;
                boolean flag = map.replace("tom", oldValue, newValue);
                if (flag) break;
            }
        }
    }

    public static void main(String[] args) {
        // 两个线程分别进行++操作，总共加2000次，核对输出结果是否是2000；
        map.put("tom", 0);
        Concurrent concurrent = new Concurrent();
        Thread t1 = new Thread(concurrent);
        Thread t2 = new Thread(concurrent);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sum: " + map.get("tom"));
    }
}