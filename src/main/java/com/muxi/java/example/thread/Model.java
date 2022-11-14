package com.muxi.java.example.thread;

/**
 * 生产消费模式
 *
 * @author jjl
 * @date 2022/11/2
 */
public class Model {
    public static void main(String[] args) {
        Person p = new Person();
        Producer producer = new Producer(p);
        Consumer consumer = new Consumer(p);
        Thread t1 = new Thread(producer, "t1");
        Thread t2 = new Thread(consumer, "t2");
        t1.start();
        t2.start();
    }
}
