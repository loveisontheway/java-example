package com.muxi.java.example.thread;

import java.util.concurrent.TimeUnit;

/**
 * volatile解决的两个问题是：
 * - volatile修饰的变量在线程间可见。
 * - volatile修饰的变量的操作指令不能被重排序。
 *
 * 产生这两个问题的原因是
 * - JMM内存模型导致了线程间变量的不可可见性。
 * - CPU指令乱序执行提高执行执行效率。
 *
 * @author jl.jiang 2021/3/5
 */
public class VolatileDemo {
    // 公共变量
    private static volatile int x = 3;

    public static void main(String[] args) throws InterruptedException {
        // 线程A 循环的访问变量
        new Thread(() -> {
            System.out.println("线程A启动");
            while (true) {
                if (x == 4) {
                    break;
                }
            }
            System.out.println("线程A退出");
        }).start();
        // 线程B稍等10毫秒就修改变量x为4
        TimeUnit.MICROSECONDS.sleep(10);
        new Thread(() -> {
            x = 4;
            System.out.println("线程B修改x为：" + x);
        }).start();
    }
}

