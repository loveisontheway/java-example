package com.muxi.java.example.basic;

import com.muxi.java.example.domain.User;

import java.lang.reflect.Field;

/**
 * reflex
 *
 * @author jjl
 * @date 2022/11/11
 */
public class Reflex {
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        User user = new User();
        user.setName("jack");
        user.setAge(20);
//        Class<?> clazz = user.getClass();
        Class<?> clazz = Class.forName("com.muxi.java.example.domain.User");
        Field[] fields = clazz.getDeclaredFields();
        String s = "(123+12.11)";
        System.out.println((s.length() == s.getBytes().length));
        for (Field field : fields) {
            /**
             如果成员变量是private的,则需要设置accessible为true
             如果成员变量是public的,则不需要设置
             **/
            field.setAccessible(true);
            String name = field.getName();
            if ("name".equals(name)) {
                System.out.println(name + "--" + field.get(user));
            }
        }
    }
}
