package com.muxi.java.example.thread;

/**
 * Producer
 *
 * @author jjl
 * @date 2022/11/2
 */
public class Producer implements Runnable {

    // 共享资源 obj
    Person p = null;

    public Producer(Person p) {
        this.p = p;
    }

    @Override
    public void run() {
        // 生产 obj
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                p.push("Tom", 11);
            } else {
                p.push("Marry", 21);
            }
        }
    }
}
