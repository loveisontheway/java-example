package com.muxi.java.example.basic;

import com.muxi.java.example.utils.PhoneUtil;

/**
 * 手机号脱敏
 *
 * @author jjl
 * @date 2023/5/29
 */
public class Cellphone {

    public static void main(String[] args) {
        String phone = "15122226666";
        String mobile = PhoneUtil.blurPhone(phone);
        System.out.println(phone);
        System.out.println(mobile);
    }
}
