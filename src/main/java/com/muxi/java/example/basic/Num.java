package com.muxi.java.example.basic;

/**
 * 数值区间
 *
 * @author jjl
 * @date 2022/9/26
 */
public class Num {

    public static void main(String[] args) {
        double current = 30;
        if (range(current, 5, 50)) {
            System.out.println(">>>>>>>>>>true");
        } else {
            System.out.println("==========false");
        }
    }

    private static boolean range(double current, double min, double max) {
        double a = Math.max(current, min);
        double b = Math.min(current, max);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        return Math.max(current, min) == Math.min(current, max);
    }
}
