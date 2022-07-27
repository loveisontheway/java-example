package com.muxi.java.example.array;

/**
 * ...  ->  0~多个
 * []   ->  必须传参
 *
 * @author jjl
 * @date 2022/7/27
 */
public class Points {
    void t1(String... a) {
        System.out.println("t1");
        for (String s : a) {
            System.out.print(" " + s);
        }
        System.out.println();
    }

    void t2(String[] a) {
        System.out.println("t2");
        for (String s : a) {
            System.out.print(" " + s);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] arr = {"a" , "b" , "d" , "e" , "f" , "g"};
        Points point = new Points();
        point.t1(arr);
        point.t2(arr);
        // 区别
        point.t1();// 可不传
//        point.t2();//必须传参数,否则报错
        point.t1("1" , "2" , "3" , "4");// 也可以一个一个的传,t2则不可以
    }
}