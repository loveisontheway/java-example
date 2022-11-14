package com.muxi.java.example.thread;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ————————————————
 * 实现目标：生产者-消费者
 * <p>
 * 一、两类线程
 * > 生产者线程生产数据
 * > 消费者线程消费数据
 * <p>
 * 二、共享的数据区域
 * > 如果共享数据区已满，阻塞生产者继续生产数据放置入内；
 * > 如果共享数据区为空，阻塞消费者继续消费数据；
 * > 在实现生产者消费者问题时，可以采用三种方式：
 * <p>
 * 1. 使用 Object 的 wait/notify 的消息通知机制；√
 * 2. 使用 Lock 的 Condition 的 await/signal 的消息通知机制；
 * 3. 使用 BlockingQueue 实现。
 * ————————————————
 *
 * @author jjl
 * @date 2022/11/2
 */
public class ObjectWaitNotify {

    static class Producer implements Runnable {

        private LinkedList<Integer> list;
        private int maxLength;
        private Random random;

        public Producer(LinkedList<Integer> list, int maxLength) {
            this.list = list;
            this.maxLength = maxLength;
            random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        while (list.size() == maxLength) {
                            System.out.println("生产者" + Thread.currentThread().getName() + " list达到最大，进行wait()");
                            list.wait();
                            System.out.println("生产者" + Thread.currentThread().getName() + " wait()结束");
                        }
                        int p = random.nextInt();
                        System.out.println("生产者" + Thread.currentThread().getName() + "生产了" + p);
                        list.add(p);
                        list.notifyAll();
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                }
            }
        }

    }

    static class Consumer implements Runnable {

        private LinkedList<Integer> list;

        public Consumer(LinkedList<Integer> linkedList) {
            this.list = linkedList;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (list) {
                    try {
                        while (list.isEmpty()) {
                            System.out.println("消费者" + Thread.currentThread().getName() + " list为空，进行wait()");
                            list.wait();
                            System.out.println("消费者" + Thread.currentThread().getName() + " wait()结束");
                        }
                        Integer p = list.remove(0);
                        System.out.println("消费者" + Thread.currentThread().getName() + "消费了" + p);
                        list.notifyAll();
                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        ExecutorService service = Executors.newFixedThreadPool(15);
        for (int i = 0; i < 5; i++) {
            service.submit(new Producer(list, 10));
        }

        for (int i = 0; i < 10; i++) {
            service.submit(new Consumer(list));
        }
    }
}
