package com.muxi.java.example.basic;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

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

        // 参数:
        // regex -- 正则表达式分隔符
        // limit -- 分割的份数
        String str2 = "xx yy zz";
        String[] strArr = str2.split(" ",2);
        System.out.println(JSONUtil.toJsonStr(strArr));

        AtomicInteger sn = new AtomicInteger(0);
        for (int i = 0; i < 10; i++) {
            sn.incrementAndGet();
            System.out.println(sn);
        }
    }
}
