package com.muxi.java.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟 10个线程 处理 1000个数据
 */
public class MultiThread {

    public static void main(String[] args) throws Exception {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            intList.add(i);
        }
        int threadNum = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        int perSize = intList.size() / threadNum;
        for (int j = 0; j < threadNum; j++) {
            MultiThreadSub thread = new MultiThreadSub();
            thread.setIdList(intList.subList(j * perSize, (j + 1) * perSize));
            thread.setCountDownLatch(countDownLatch);
            executorService.submit(thread);
        }
        countDownLatch.await();
        executorService.shutdown();

    }
}

class MultiThreadSub extends Thread {
    private List<Integer> idList;

    private CountDownLatch countDownLatch;

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            System.out.println(this.idList);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }
}


