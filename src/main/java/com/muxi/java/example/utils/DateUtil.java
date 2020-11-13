package com.muxi.java.example.utils;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间工具类
 *
 * @author jl.jiang 2020/10/10
 */
public abstract class DateUtil {

    /**
     * 正则表达式：判断字符串是否为数字的方法的几种方法
     *
     * @param str 检验的字符串
     * @return boolean true:数字 false:非数字
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?[1-9]\\d");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    // 东八区时间
    public static Calendar calendar =
            Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

    /**
     * 获取年份
     *
     * @return Integer 年份
     */
    public static Integer getYear() {
        return calendar.get(Calendar.YEAR);
    }


}


