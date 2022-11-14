package com.muxi.java.example.thread;

/**
 * Consumer
 *
 * @author jjl
 * @date 2022/11/2
 */
public class Consumer implements Runnable {

    // 共享资源 obj
    Person p = null;

    public Consumer(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        // 消费 obj
        for (int i = 0; i < 50; i++) {
            p.pop();
        }
    }
}
