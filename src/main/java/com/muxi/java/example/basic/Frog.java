package com.muxi.java.example.basic;

/**
 * 计算题：
 * 青蛙爬井问题一只青蛙在一口11米深的井底向上爬，
 * 白天向上爬3米，晚上向下滑2米，总共需要几天可以爬出？
 *
 * @author jl.jiang 2021/3/10
 */
public class Frog {
    public static void main(String[] args) {
        int i = 0;
        int j = 0;
        for (; ; j++) {
            i = i + 3;
            if (i > 11) {
                break;
            }
            i = i - 2;
        }
        System.out.println(j);
    }
}
