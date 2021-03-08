package com.muxi.java.example.basic;

import java.util.HashMap;
import java.util.Map;

/**
 * Java自动拆箱空指针异常
 *
 * @author jl.jiang 2021/3/5
 */
public class BoxTest {
    public static void main(String[] args) {
        Map<String,Object> result = httpRequest();
        // long 改成 Long 则可接收 null
        // Java中null是一个特殊的值，可以赋值给任何引用类型，也可以转化为任何引用类型
        long userId = (Long) result.get("userId");
    }

    // 模拟一个HTTP请求
    private static Map<String,Object> httpRequest(){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",null);
        return map;
    }
}
