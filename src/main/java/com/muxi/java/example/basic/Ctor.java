package com.muxi.java.example.basic;

/**
 * Constructor 构造函数
 *
 * > 编写一个类时没有添加无参构造方法，那么编译器会自动添加无参构造方法；
 *   (如果自己添加构造函数，无论有参数或是没参数，默认构造函数都将无效)
 * > 编写时添加了有参构造方法而未添加无参构造方法，
 *   那么编译器只认有参构造方法而不会默认添加无参构造方法！
 * > 如果需要使用无参构造方法，一定要在类里面添加
 * ---------------------------------------------------------------
 * 类 Person1 不添加 无参 and 有参 构造方法    （实例化对象时：编译通过）
 * 类 Person2 添加 无参 构造方法              （实例化对象时：编译通过）
 * 类 Person3 添加 有参 构造方法              （实例化对象时：不通过 ）
 * 类 Person4 添加 无参 and 有参 构造方法      （实例化对象时：编译通过）
 * ---------------------------------------------------------------
 *
 * @author jjl
 * @date 2022/10/14
 */
public class Ctor {
    public static void main(String[] args) {
        // 编译通过；①实例化Person对象  ②自动调用构造方法Person()
        Person1 person1 = new Person1();
        // 编译通过；打印: 无参构造方法被调用
        Person2 person2 = new Person2();
        // 这样写，编译器会报错，原因是系统默认的无参构造方法被有参构造方法覆盖，编译器不能再提供无参构造方法
//        Person3 person3 = new Person3();
        // 编译通过；打印: 无参构造方法被调用
        //Person4 person4 = new Person4();
        Person4 person4 = new Person4("qzz", "man", 18);//编译通过；
    }
}

/**
 * 定义类Person1   自己不手动添加任何无参或有参数构造方法
 */
class Person1 {
    private int age;
    private String name;
    private String sex;
}

/**
 * 定义类Person2   自己添加无参的构造方法
 */
class Person2 {
    private int age;
    private String name;
    private String sex;

    public Person2() {
        System.out.println("无参构造方法被调用");
    }
}

/**
 * 定义类Person3   有参数的构造方法
 */
class Person3 {
    private int age;
    private String name;
    private String sex;

    public Person3(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}

/**
 * 定义类Person4   自己添加无参的构造方法,和有参数的构造方法
 */
class Person4 {
    private int age;
    private String name;
    private String sex;

    // 不带参数的构造函数，可以被重载
    public Person4() {
        System.out.println("无参构造方法被调用");
    }

    // 带参数对的构造函数
    public Person4(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
}
