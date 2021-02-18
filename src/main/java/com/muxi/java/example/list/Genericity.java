package com.muxi.java.example.list;

import javafx.scene.Parent;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 泛型
 * 1、解决元素存储的安全性问题
 * 2、解决获取数据元素时，需要类型强转的问题
 *
 * - 要从泛型类取数据时，用extends；
 * - 要往泛型类写数据时，用super；
 * - 既要取又要写，就不用通配符（即extends与super都不用）。
 * 注：泛型中只有通配符可以使用super关键字，类型参数不支持 这种写法
 *
 * @author jl.jiang 2021/2/18
 */
public class Genericity {

    // 普通方法
    public void testValue(String s) {
    }

    // 泛型方法
    public <T> void testValue(T t) {
    }

    // 未指定上限
    public static class Test1<T> {
        T t;
        public T getValue() {
            return t;
        }
        public void setVale(T t) {
            this.t = t;
        }
    }
    // 指定上限
    public static class Test2<T extends List> {
        T t;
        public T getT() {
            return t;
        }
        public void setT(T t) {
            this.t = t;
        }
    }

    public static class Test5<T> {
        T t;
        public T getValue() {
            return t;
        }
        public void setVale(T t) {
            this.t = t;
        }
    }

    /**
     * 泛型晋级使用（泛型与继承的关系）
     * 继承关系
     * 即设置泛型上限，传入的泛型必须是Parent类型或者是他的子类
     */
    public <T extends Parent> void testType(T t) {
    }

    /**
     * 依赖关系的使用
     * 泛型间可以存在依赖关系，比如下面的C是继承自E。即传入的类型是E类型或者是E类型的子类
     */
    public <E, C extends E> void testDependys(E e, C c) {
    }

    // 无限通配符
    public void testV(List<?> list) {}

    /**
     * 注: 无限通配符只能读的能力，没有写的能力。
     */
    public void testAny(List<?> list) {
        Object o = list.get(0);
        // 编译器不允许该操作
        // List<?>为无限通配符，他只能使用get()获取元素，
        // 但不能使用add()方法添加元素。（即使修改元素也不被允许）
//        list.add("jaljal");
    }

    // 定义了上限，其只有读的能力。此方式表示参数化的类型可能是所指定的类型，或者是此类型的子类。
    // t1要么是Test2，要么是Test2的子类
    public void testC(Test1<? extends Test2> t1) {
        Test2 value = t1.getValue();
        System.out.println("testC中的：" + value.getT());
    }
    // 定义了下限，有读的能力以及部分写的能力，子类可以写入父类。此方式表示参数化的类型可能是指定的类型，或者是此类型的父类
    // t1要么是Test5,要么是Test5的父类
    public void testB(Test1<? super Test5> t1) {
        // 子类代替父类
        Test2 value = (Test2) t1.getValue();
        System.out.println(value.getT());
    }

    // 通配符不能用作返回值
    // 如果返回值依赖类型参数，不能使用通配符作为返回值。可以使用类型参数返回方式
    public <T> T testA(T t, Test1<T> test1) {
        System.out.println("这是传入的T:" + t);
        t = test1.t;
        System.out.println("这是赋值后的T:" + t);
        return t;
    }

    /**
     * 通配符形式和类型参数经常配合使用
     * 类型参数的形式都可以替代通配符的形式
     * 能用通配符的就用通配符,因为通配符形式上往往更为简单、可读性也更好。
     * 类型参数之间有依赖关系、返回值依赖类型参数或者需要写操作，则只能用类型参数。
     * 注：源码中对泛型的使用可以参考 Collections 类的一些方法
     */
    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        list.sort(null);
    }
    public static <T> void sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
    }

    // LinkedHashMap可以用来实现固定大小的LRU缓存，当LRU缓存已经满 了的时候，
    // 它会把最老的键值对移出缓存。LinkedHashMap提供了一个称为removeEldestEntry()的方法，
    // 该方法会被put() 和putAll()调用来删除最老的键值对。
/*    class LRUCacheLinkedHashMap<K, V> extends LinkedHashMap<K, V> {​
        private int cacheSize;​
        public LRUCacheLinkedHashMap(int cacheSize) {​
            // true 表示让 linkedHashMap按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部。
            super(16, 0.75F, true);
            this.cacheSize = cacheSize;
        }​
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {​
            // 当map中的数据量大于指定的缓存个数的时候，就自动删除最老的数据。
            // return size()> cacheSize;
        }
    }*/

    public static void main(String[] args) {
        /** 通过反射调用获取他们的属性类型 */
        // 未指定上限
        Test1<String> test1 = new Test1<>();
        test1.setVale("11111");
        Class<? extends Test1> aClass = test1.getClass();
        for (Field field : aClass.getDeclaredFields()) {
            System.out.println("Test1属性:" + field.getName() + "\t类型:" + field.getType().getName());
        }
        // 指定上限
        Test2 test2 = new Test2();
        test2.setT(new ArrayList());
        Class<? extends Test2> aClass2 = test2.getClass();
        for (Field field : aClass2.getDeclaredFields()) {
            System.out.println("test2属性:" + field.getName() + "\t类型:" + field.getType().getName());
        }

    }

}
