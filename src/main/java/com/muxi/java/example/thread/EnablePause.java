package com.muxi.java.example.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Enable Pause
 *
 * @author jjl
 * @date 2023/2/17
 */
public class EnablePause extends JFrame {

    public static void main(String[] args) {
        new EnablePause();
    }
    // 显示数字中的标签
    private JLabel label;
    String[] numArr = {"15180691681", "13870225947", "13870261079", "12345671111", "1397995240"};

    public EnablePause() {
        setTitle("手机号抽奖");
        setBounds(200, 200, 400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MyThread myThread = new MyThread();
        label = new JLabel("0");
        myThread.start();

        // 设置文字居中
        label.setHorizontalAlignment(SwingConstants.CENTER);
        // 设置字体
        label.setFont(new Font("宋体", Font.BOLD, 42));
        getContentPane().add(label, BorderLayout.CENTER);

        JButton jButton = new JButton("暂停");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String btn = jButton.getText();
                if (btn.equals("暂停")) {
                    myThread.toSuspend();
                    jButton.setText("继续");
                } else {
                    myThread.toResume();
                    jButton.setText("暂停");
                }
            }
        });
        getContentPane().add(jButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    class MyThread extends Thread {
        private boolean suspend = false;
        public synchronized void toSuspend() {
            suspend = true;
        }
        public synchronized void toResume() {
            notify();   // 当前等待的线程继续执行
            suspend = false;
        }
        @Override
        public void run() {
            while (true) {
                synchronized (this) {
                    while (suspend) {
                        try {
                            wait(); // 让线程进入等待状态
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                // 获取数组随机索引
                int index = new Random().nextInt(numArr.length);
                String phone = numArr[index];
                label.setText(phone);
            }
        }
    }
}