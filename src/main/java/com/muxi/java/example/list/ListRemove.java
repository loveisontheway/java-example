package com.muxi.java.example.list;

import java.util.*;

/**
 * List Remove (移除元素)
 * 用 for 循环移除list中的元素，报异常Exception in thread "main" java.util.ConcurrentModificationException
 * 使用迭代器方式移除，则不会抛异常
 */
public class ListRemove {
    public static void main(String[] args) {
        ListRemove test = new ListRemove();

        List<String> names1 = new ArrayList<String>();
        names1.add("Google ");
        names1.add("Runoob ");
        names1.add("Taobao ");
        names1.add("Baidu ");
        names1.add("Sina ");
        test.sortUsingJava7(names1);
        System.out.println(names1);


        List<Integer> names2 = new ArrayList<Integer>();
        names2.add(9);
        names2.add(1);
        names2.add(5);
        names2.add(20);
        names2.add(88);
        test.sortUsingJava8(names2);
        System.out.println(names2);

        Lama str = name -> System.out.println(name);

        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            String item = iterator.next();
            if("2".equals(item)){
                iterator.remove();
            }
        }
        System.out.println("===="+list.toString());

        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        for (String item : list2){
            if ("2".equals(item)){
                list2.remove(item);
            }
        }
        System.out.println(">>>>"+list2.toString());
    }

    public interface Lama{
        void func(String name);
    }

    private void sortUsingJava7(List<String> names) {
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    private void sortUsingJava8(List<Integer> names) {
        Collections.sort(names, (s1, s2) -> s1.compareTo(s2));
    }
}
