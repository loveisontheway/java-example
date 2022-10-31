package com.muxi.java.example.basic;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Formula:  String >> JavaScript
 *
 * @author jjl
 * @date 2022/10/31
 */
public class Formula {

    static ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");

    /**
     * @param args
     */
    public static void main(String[] args) {
        // 支持[]符号计算，不支持{}符号计算
//        String str = "1+1*2+(10-(2*(5-3)*(2-1))-4)+10/(5-0)";
        String str = "2.7*10+1-6";
        try {
            System.out.println(jse.eval(str));
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}