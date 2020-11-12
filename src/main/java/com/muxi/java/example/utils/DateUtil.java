package com.muxi.java.example.utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * 时间工具类
 *
 * @author jl.jiang 2020/10/10
 */
public abstract class DateUtil {
    // 东八区时间
    public static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));

    /**
     * 获取年份
     *
     * @return Integer 年份
     */
    public static Integer getYear() {
        return calendar.get(Calendar.YEAR);
    }
}
