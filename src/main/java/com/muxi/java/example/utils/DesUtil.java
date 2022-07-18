package com.muxi.java.example.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;


/**
 * DES + 密钥向量 （加密 / 解密）
 *
 * @author jjl
 * @date 2022/7/18
 */
public class DesUtil {
    private static final String PDK = "ising@ws";
    // 设置密钥
    private static final byte[] DES_KEY = PDK.getBytes();
    // 设置向量
    private static final byte[] DES_IV = {(byte) 0x12, (byte) 0x56, (byte) 0x34, (byte) 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF};

    // 加密算法的参数接口，IvParameterSpec是它的一个实现
    private static AlgorithmParameterSpec iv = null;
    private static Key key = null;

    public DesUtil() throws Exception {
        // 密钥参数
        DESKeySpec keySpec = new DESKeySpec(DES_KEY);
        // 密钥向量
        iv = new IvParameterSpec(DES_IV);
        // 密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 密钥对象
        key = keyFactory.generateSecret(keySpec);
    }

    public String encode(String data) throws Exception {
        //得到加密对象Cipher
        Cipher enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        //设置工作模式为加密模式，给出密钥和向量
        enCipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] pasByte = enCipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(pasByte);
    }

    public String decode(String data) throws Exception {
        Cipher deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        deCipher.init(Cipher.DECRYPT_MODE, key, iv);
        BASE64Decoder base64Decoder = new BASE64Decoder();
        byte[] pasByte = deCipher.doFinal(base64Decoder.decodeBuffer(data));
        return new String(pasByte, StandardCharsets.UTF_8);
    }

    public static void main(String[] args) throws Exception {
        String value = "admin123";
        DesUtil util = new DesUtil();
        String ec = util.encode(value);
//        String ec = "BvRZPPYKtCo37/Zw0qPjpQ==";
        System.out.println("encode: "+ec);
        System.out.println("decode: " + util.decode(ec));
    }

}