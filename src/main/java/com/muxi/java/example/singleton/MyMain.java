package com.muxi.java.example.singleton;

import java.math.BigDecimal;

/**
 * @author jjl
 * @date 2023/8/16
 */
public class MyMain {

    private GlobalObject globalObject = GlobalObject.getInstance();

    public void set() {
        globalObject.setName("jack");
        globalObject.setAge(30);
        globalObject.setBool(true);
        globalObject.setPrice(new BigDecimal(6.66));
        globalObject.setAddr("hangzhou");
    }

    public String get() {
        return globalObject.toString();
    }

    public void clear() {
        globalObject.clearValues();
    }

    public static void main(String[] args) {
        MyMain myMain = new MyMain();
        myMain.set();
        String str = myMain.get();
        System.out.println(str);
        myMain.clear();
        String str2 = myMain.get();
        System.out.println(str2);
    }
}
