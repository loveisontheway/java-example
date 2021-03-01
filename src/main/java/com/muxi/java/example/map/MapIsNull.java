package com.muxi.java.example.map;

import com.google.common.collect.ArrayListMultimap;
import org.thymeleaf.util.MapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Map is null exception
 *
 * @author jl.jiang 2021/3/1
 */
public class MapIsNull {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "muxi");
        map.put("job", "it");
        // 可能存在 java.lang.NullPointerException
//        System.out.println(map.get("address").toUpperCase());


//        String value = map.get("address");
        // 第一种 if 判空
/*        if (!Objects.isNull(value)) {
            System.out.println(value.toUpperCase());
        }*/

        // 第二种 条件运算符
//        value = Objects.isNull(value) ? "" : value;

        // 第三种 JDK8特性使用 Map#getOrDefault，等同第二种
//        String value = map.getOrDefault("address","");

        // 第四种 Apache Common-Lang3 提供的工具类 MapUtils
        boolean flag = MapUtils.containsKey(map, "address");

        /**
         *  一个key映射多个value -> Map<K, list<V>>
         */
        Map<String, List<String>> mapList = new HashMap<>();
/*        List<String> classify = mapList.get("java");
        if (Objects.isNull(classify)) {
            classify = new ArrayList<>();
            classify.add("Spring");
            mapList.put("java", classify);
        } else {
            classify.add("Spring");
        }*/

        // 上面代码比较繁琐，JDK8，Map新增一个 computeIfAbsent 方法
        mapList.computeIfAbsent("java", key -> new ArrayList<>()).add("Spring");
        // 错误：当 Map 中 key 对应 value 不存在的时候，putIfAbsent将会直接返回 null。
        // 报 java.lang.NullPointerException
//        mapList.putIfAbsent("java", new ArrayList<>()).add("Spring");

        // Google Guava 提供的新集合类型 Multiset
        ArrayListMultimap<Object, Object> multiset= ArrayListMultimap.create();
        multiset.put("java","Spring");
        multiset.put("java","Mybatis");

    }
}
