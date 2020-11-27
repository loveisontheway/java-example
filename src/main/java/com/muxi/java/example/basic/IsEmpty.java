package com.muxi.java.example.basic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * JAVA判断各种类型数据是否为空
 *
 * @author jl.jiang 2020/11/20
 */
public class IsEmpty {

    public static void main(String[] args) {
        /**
         * List Empty
         */
        List<Object> list = new ArrayList<Object>();
        if (list != null && list.size() == 0) {
        }
        if (list != null && !list.isEmpty()) {
        }
        // list!=null：判断是否存在list，null表示这个list不指向任何的东西，如果这时候你调用它的方法，那么就会出现空指针异常。
        // list.isEmpty()：判断list里是否有元素存在
        // list.size()：判断list里有几个元素
        // 所以判断list里是否有元素的最佳的方法是:
        if (list != null && !list.isEmpty()) {
            // list存在且里面有元素
        }

        /**
         * String Empty
         */
        String str = "ABC";
//        直接用
//        if( s.equals(""))，
//        if( !s.isEmpty())，
//        if(s.length()>0)来判断：
//        忽略了s为null的情况，s指向不确定的对象，无法调用一个确定的Sting对象的方法
        if (str == null) ;
        if ("".equals(str)) ;
        if (str.length() <= 0) ;
        if (str.isEmpty()) ;

        /**
         * Date Empty
         */
        Date date = new Date();
        if (date == null) {
            System.out.println("date为空");
        } else {
            System.out.println("date不为空");
        }

    }

}
