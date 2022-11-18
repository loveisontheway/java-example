package com.muxi.java.example.basic;

import java.util.Arrays;

/**
 * split more
 *
 * @author jjl
 * @date 2022/11/18
 */
public class Split {
    public static void main(String[] args) {
        String str = "czh!han.bb   test@love";
        //用'!','.',空格三个,'@'来分割字符串
        String[] a = str.split("\\s+|\\.|\\!|@");
        System.out.println(Arrays.toString(a));

        String formula = "直接费+间接费+利润+编制基准期价差+乙供设备费不含税+乙供设备运杂费不含税" +
                "+乙供设备配送费不含税-甲供主材费含损耗-甲供主材配送费+计税一笔性费用";
        formula = formula.replaceAll("\\{[()]}","");
        String[] arr = formula.split("\\+|\\-|\\×|\\/");
        System.out.println(Arrays.toString(arr));
    }
}