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
    public static void main(String[] args) throws IllegalAccessException {
        User user = new User();
        user.setName("jack");
        user.setAge(20);
        Class<?> clazz = user.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            /**
             如果成员变量是private的,则需要设置accessible为true
             如果成员变量是public的,则不需要设置
             **/
            field.setAccessible(true);
            System.out.println(field + "--" + field.get(user));
        }
    }
}
