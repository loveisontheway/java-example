package com.muxi.java.example.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Object ->  null
 *
 * @author jjl
 * @date 2022/7/28
 */
public abstract class ObjUtil {
    /**
     * 判断对象是否为空，且对象的所有属性都为空
     * ps: boolean类型会有默认值false 判断结果不会为null 会影响判断结果
     * 序列化的默认值也会影响判断结果
     *
     * @param obj object
     * @return boolean
     */
    public static boolean isNull(Object obj) {
        Class clazz = (Class) obj.getClass(); // 类对象
        Field fields[] = clazz.getDeclaredFields(); // 所有属性
        boolean flag = true; // 定义返回结果，默认为true
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(obj); // 属性值
                Type fieldType = field.getGenericType();// 属性类型
                String fieldName = field.getName(); // 属性名
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fieldValue != null) {  // 只要有一个属性值不为null 就返回false 表示对象不为null
                flag = false;
                break;
            }
        }
        return flag;
    }
}
