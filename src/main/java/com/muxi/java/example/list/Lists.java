package com.muxi.java.example.list;

import java.util.ArrayList;
import java.util.List;

/**
 * List的并集 交集 差集 去重复并集
 */
public class Lists {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        System.out.println("list1: "+list1);

        List<String> list2 = new ArrayList<String>();
        list2.add("C");
        list2.add("B");
        list2.add("D");
        System.out.println("list2: "+list2);
        // 并集
        list1.addAll(list2);
        System.out.println("并集"+list1);
        // 去重复并集
//        list2.removeAll(list1);
//        list1.addAll(list2);
//        System.out.println("去重复并集"+list1);
        // 交集
//        list1.retainAll(list2);
//        System.out.println("交集"+list1);
        // 差集
//        list1.removeAll(list2);
//        System.out.println("差集"+list1);
    }
}
