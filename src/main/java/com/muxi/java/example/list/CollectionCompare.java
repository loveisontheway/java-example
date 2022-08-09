package com.muxi.java.example.list;

import com.muxi.java.example.domain.User;
import org.springframework.util.StopWatch;

import java.util.*;

/**
 * for > iterator > foreach （性能：for最优，iterator其次，foreach最差）
 *
 * 1. for遍历
 *      for(int i=0; i<arr.size(); i++){...}
 *
 * 2. iterator遍历（用于Map、Set）
 *      Iterator it = arr.iterator();
 *      while(it.hasNext()){ object o =it.next(); ...}
 *
 * 3. foreach遍历
 *      for(int　i : arr){...}
 *
 * @author jjl
 * @date 2022/8/9
 */
public class CollectionCompare {

    public static void main(String[] args) {
        StopWatch sw = new StopWatch();
        List<String> list = new ArrayList<>();
        list.add("jack");
        list.add("lily");
        list.add("mono");

        /** for */
        sw.start("for");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        sw.stop();

        System.out.println("-------------- for: " + sw.getLastTaskTimeMillis() + " --------------");

        /** Iterator */
        // Map
        Map<String, String> map = new HashMap<>();
        map.put("name", "luna");
        map.put("sex", "female");
        map.put("age", "28");

        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> str = iterator.next();
            System.out.println(str);
        }

        // Set
        Set<String> set = new HashSet<>();
        set.add("世界军事");
        set.add("兵器知识");
        set.add("舰船知识");
        set.add("汉和防务");
        Iterator<String> iter = set.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        Iterator<String> it = list.iterator();
        sw.start("iterator");
        while (it.hasNext()) {
            Object obj = it.next();
            if ("mono".equals(obj)) {
                it.remove();
            }
        }
        it = list.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        sw.stop();

        System.out.println("-------------- iterator: " + sw.getLastTaskTimeMillis() + " --------------");

        /** foreach */
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(new User("Lisa", "female", 20));
        coll.add(new String("Tom"));
        coll.add(false);

        // 内部仍然调用了迭代器。
        sw.start("foreach");
//        coll.forEach(obj -> System.out.println(obj));
        coll.forEach(str -> System.out.println(str));
        sw.stop();
        System.out.println("-------------- foreach: " + sw.getLastTaskTimeMillis() + " --------------");
        int[] arr = new int[]{1, 3, 5, 11, 6, 9, 8};
        // for(集合元素的类型 局部变量 : 集合对象)
        for (int i : arr) {
            System.out.println(i);
        }

    }
}
