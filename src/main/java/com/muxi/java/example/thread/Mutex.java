package com.muxi.java.example.thread;

/**
 * 互斥：线程A访问了一组数据，线程BCD就不能同时访问这些数据，直到A停止访问了
 * 同步：ABCD这些线程要约定一个执行的协调顺序。比如D要执行，B和C必须都得做完，而B和C要开始，A必须先得做完。
 * - 锁的目的就是避免多个线程对同一个共享的数据并发修改带来的数据混乱。
 *
 * 锁的实现要处理的大概就只有这4个问题：
 * 1、“谁拿到了锁“这个信息存哪里（可以是当前class，当前instance的markword，还可以是某个具体的Lock的实例）
 * 2、谁能抢到锁的规则（只能一个人抢到 - Mutex；能抢有限多个数量 - Semaphore；自己可以反复抢 - 重入锁；读可以反复抢到但是写独占 - 读写锁……）
 * 3、抢不到时怎么办（抢不到玩命抢；抢不到暂时睡着，等一段时间再试/等通知再试；或者二者的结合，先玩命抢几次，还没抢到就睡着）
 * 4、如果锁被释放了还有其他等待锁的怎么办（不管，让等的线程通过超时机制自己抢；按照一定规则通知某一个等待的线程；通知所有线程唤醒他们，让他们一起抢……）
 *
 * @author jl.jiang 2021/3/5
 */
public class Mutex {

    static class Boo {

        public synchronized void methodA() {
            Thread t = Thread.currentThread();
            try {
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(t.getName()+":运行A方法完毕！");
        }

        public void methodB() {
            synchronized(this) {
                Thread t = Thread.currentThread();
                System.out.println(t.getName()+":正在运行方法B...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(t.getName()+":运行B方法完毕！");
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 互斥锁
         * 当使用synchroinzed锁住多段不同的代码片段，
         * 但是这些同步块使用的同步监视器对象是同一个时，那么这些代码
         * 片段之间就是互斥的。多个线程不能同时执行他们。
         */
        final Boo boo = new Boo();
        Thread t1 = new Thread() {
            public void run() {
                boo.methodA();
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                boo.methodB();
            }
        };
        t1.start();
        t2.start();
    }

}
