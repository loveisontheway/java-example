package com.muxi.java.example.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Map集合的遍历
 *
 * @author jl.jiang 2021/2/22
 */
public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> hm = new HashMap<String, String>();
        hm.put("bwh002", "i");
        hm.put("bwh001", "love");
        hm.put("bwh003", "you");
        /** 方式一 键找值 */
        Set<String> set = hm.keySet();
        System.out.println("*****遍历Map键值*****");
        // 迭代器遍历
        Iterator<String> iterator = set.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            String value = hm.get(key);
            System.out.println("key:"+key+"\tvalue:"+value);
        }
        System.out.println("---------------------");
        // 增强for遍历
        for (String key : set) {
            String value = hm.get(key);
            System.out.println("key:"+key+"\tvalue:"+value);
        }

        /** 方式二 键值对对象找键和值（推荐） */
        Set<Map.Entry<String, String>> entrySet = hm.entrySet();
        System.out.println("*****遍历Map对象*****");
        // 迭代器遍历
        Iterator<Map.Entry<String, String>> entryIterator = entrySet.iterator();
        while (entryIterator.hasNext()){
            Map.Entry<String, String> entry = entryIterator.next();
            String key = entry.getKey();
            String value = hm.get(key);
            System.out.println("key:"+key+"\tvalue:"+value);
        }
        System.out.println("---------------------");
        // 增强for遍历
        for (Map.Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = hm.get(key);
            System.out.println("key:"+key+"\tvalue:"+value);
        }
    }
}
