package com.muxi.java.example.array;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Java Array
 * type[] arrayName;    // 数据类型[] 数组名;
 * type arrayName[];    // 数据类型 数组名[];
 *
 * @author jl.jiang 2022/3/21
 */
public class Array {

    public static void main(String[] args) {
        int[] prices = new int[5]; // 声明数组并分配空间
        Scanner input = new Scanner(System.in); // 接收用户从控制台输入的数据
        for (int i = 0; i < prices.length; i++) {
            System.out.println("请输入第" + (i + 1) + "件商品的价格：");
            prices[i] = input.nextInt(); // 接收用户从控制台输入的数据
        }
        System.out.println(Arrays.toString(prices));
        System.out.println("第 3 件商品的价格为：" + prices[2]);
    }
}
