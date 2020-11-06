package com.muxi.java.example.list;

import java.util.*;

/**
 * List 去重排序 (Integer / String)
 */
public class ListSetSort {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<Integer>();

        Random random = new Random();
        int s = 0;
        while (s < 10) {
            list.add(random.nextInt(10));
            s++;
        }
        System.out.println("排序前" + list);

        Collections.sort(list);
        System.out.println("排序后" + list);
        Set<Integer> set = new HashSet<>(list);
        System.out.println("去重后" + set);
        System.out.println("--------------------------");
        List<String> strList = new ArrayList<>();
        strList.add("B");
        strList.add("B");
        strList.add("F");
        strList.add("A");
        strList.add("D");
        strList.add("E");
        strList.add("C");
        strList.add("A");
        System.out.println("排序前" + strList);
        Collections.sort(strList);
        System.out.println("排序后" + strList);
        Set<String> strSet = new HashSet<>(strList);
        System.out.println("去重后" + strSet);
    }
}
