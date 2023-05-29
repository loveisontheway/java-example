package com.muxi.java.example;

import com.muxi.java.example.encrypt.SMUtil;

/**
 * @author zxb
 * @version 1.0
 * @date 2021/05/21 9:56
 * @description 测试加密工具类
 */
public class SMTest {

    public static void main(String[] args) {
        SMTest test = new SMTest();
        test.createSm2KeyTest();
        test.sm2EncryptAndDecryptTest();
    }

    /**
     * 生成sm2公钥和私钥测试(生成密钥对后将密钥存到SMUtil对应的常量中即可)
     */
    public void createSm2KeyTest() {
        SMUtil.createSm2Key();
    }

    /**
     * sm2非对称加密和解密测试
     */
    public void sm2EncryptAndDecryptTest() {
        long start = System.currentTimeMillis();
        String text = "我是一段测试aaaa";
        String sm2Encrypt = SMUtil.sm2Encrypt(text);
        System.out.println("加密结果：" + sm2Encrypt);
        String sm2Decrypt = SMUtil.sm2Decrypt(sm2Encrypt);
        System.out.println("解密结果：" + sm2Decrypt);
        System.out.println(text.equals(sm2Decrypt));
        long end = System.currentTimeMillis();
        System.out.println("加密解密一次所花时间：" + (end - start) + "ms");
    }

    /**
     * sm2非对称加密和解密测试
     */
    public void sm2EncryptAndDecryptTest2() {
        long start = System.currentTimeMillis();
        String text = "我是一段测试aaaa";
        String sm2Encrypt = SMUtil.sm2Encrypt(text);
        System.out.println("加密结果：" + sm2Encrypt);
        String sm2Decrypt = SMUtil.sm2Decrypt(sm2Encrypt);
        System.out.println("解密结果：" + sm2Decrypt);
        System.out.println(text.equals(sm2Decrypt));
        long end = System.currentTimeMillis();
        System.out.println("加密解密一次所花时间：" + (end - start) + "ms");
    }
}

