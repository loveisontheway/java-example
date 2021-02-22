package com.muxi.java.example.map;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * 案例（统计字符串中字符出现的次数）
 * 需求：
 *      获取一个字符串出现的次数
 * 分析：
 *      A：定义一个字符串（或者键盘录入）
 *      B: 定义一个TreeMap集合
 *              键：Character
 *              值：Integer
 *      C：把字符串转换为字符数组
 *      D: 遍历字符数组，得到每一个字符
 *      E: 拿刚才得到的字符作为键去集合中找，看返回值
 *              是 null：说明该键不存在,就把该字符串作为键，1作为值去存储
 *              不是 null：说明该键存在，就把值加 1 然后重写存储该键和值
 *      F: 定义字符串缓冲区变量
 *      G：遍历集合，得到该建和值，按照要求拼接
 *      H：最后把字符串缓冲区转换为字符串输出
 *
 * @author jl.jiang 2021/2/22
 */
public class CountDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要统计的数据");
        String line = sc.nextLine();

        Map<Character, Integer> treeMap = new TreeMap<>();

        char[] chs = line.toCharArray();
        for (char ch : chs) {
            Integer i = treeMap.get(ch);
            if (i == null) {
                treeMap.put(ch, 1);
            } else {
                i++;
                treeMap.put(ch, i);
            }
        }

        StringBuilder sb = new StringBuilder();
        Set<Character> set = treeMap.keySet();
        for (Character key : set) {
            Integer value = treeMap.get(key);
            sb.append(key).append("(").append(value).append(")").append(" ");
        }
        String str = sb.toString();
        System.out.println("result: "+str);
    }
}
