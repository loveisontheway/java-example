package com.muxi.java.example.thread;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Exception in thread "main" java.util.ConcurrentModificationException
 * 异常的原因很简单，一个线程修改了list的modCount导致另外一个线程迭代时modCount与该迭代器的expectedModCount不相等
 * 采用CopyOnWriteArrayList，解决了多线程问题，同时可以add、clear等操作
 */
public class MultiThreadRemove {

    static List<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        new Thread() {
            public void run() {
                Iterator<String> iterator = list.iterator();

                while (iterator.hasNext()) {
                    System.out.println(Thread.currentThread().getName()
                            + ":" + iterator.next());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            public synchronized void run() {
                Iterator<String> iterator = list.iterator();
                while (iterator.hasNext()) {
                    String element = iterator.next();
                    System.out.println(Thread.currentThread().getName()
                            + ":" + element);
                    if (element.equals("c")) {
                        list.remove(element);
                    }
                }
            }
        }.start();

    }
}
