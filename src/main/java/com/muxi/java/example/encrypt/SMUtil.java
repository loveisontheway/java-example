package com.muxi.java.example.encrypt;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hutool针对Bouncy Castle做了简化包装，
 * 用于实现国密算法中的SM2、SM3、SM4。
 * 国密算法工具封装包括：
 * >  SM2: 非对称加密和签名
 * >  SM3: 摘要签名算法
 * >  SM4: 对称加密
 *
 * @author jjl
 * @date 2023/3/1
 */
public class SMUtil {
    private static final Logger logger = LoggerFactory.getLogger(SMUtil.class);
    /**
     * SM2的公钥和私钥生成之后存起来使用即可，一般会把公钥提供给前端
     **/
    public static String SM2_PRIVATE_KEY = "";
    public static String SM2_PUBLIC_KEY = "";
    /**
     * sm2加密模式有两种：C1C2C3、C1C3C2（开始国密标准使用C1C2C3，新标准使用C1C3C2）
     */
    public static final SM2Engine.Mode SM2ENGINE_MODE = SM2Engine.Mode.C1C3C2;
    private static final String SM2BC_STR = "04";
    private static SM2 sm2 = new SM2();

    /**
     * SM2 加密
     * @param data 待加密文本
     */
    public static String sm2Encrypt(String data) {
        String encryptData = "";
        initSM2();
        encryptData = sm2.encryptBcd(data, KeyType.PublicKey);
        return encryptData;
    }

    /**
     * SM2 解密
     * @param data 密文
     */
    public static String sm2Decrypt(String data) {
        String decryptData = "";
        initSM2();
        // 使用BC库解密需要在密文前面拼上 "04" 字符串
        //  > 前端加密是不会加上04
        //  > 后端加密则会加上04，所以需要做多一层判断
        // 注意：请使用使用StringBuffer不要使用StringBuilder，前者是线程安全，后者非线程安全
        if (!data.startsWith(SM2BC_STR)) {
            data = new StringBuffer(SM2BC_STR).append(data).toString();
        }
        decryptData = sm2.decryptStr(data, KeyType.PrivateKey);
        return decryptData;
    }

    /**
     * 初始化 SM2
     */
    public static void initSM2() {
        ECPrivateKeyParameters privateParam = BCUtil.toSm2Params(SM2_PRIVATE_KEY);
        // 产生的公钥为130字节，实际公钥为128字节，
        // 第一个字节表示是否压缩，这里用不上，
        // 所以从第二个字节开始截取横坐标和纵坐标
        String xhex = SM2_PUBLIC_KEY.substring(2, 66);
        String yhex = SM2_PUBLIC_KEY.substring(66, 130);
        ECPublicKeyParameters publicParam = BCUtil.toSm2Params(xhex, yhex);
        // 设置公钥、私钥
        sm2.setPrivateKeyParams(privateParam);
        sm2.setPublicKeyParams(publicParam);
        // 设置加密模式
        sm2.setMode(SM2ENGINE_MODE);
    }

    /**
     * 生成 SM2 公钥、私钥
     */
    public static void createSm2Key() {
        // 创建sm2 对象
        SM2 create = SmUtil.sm2();
        // 这里会自动生成对应的随机秘钥对 , 注意！ 这里一定要强转，才能得到对应有效的秘钥信息
        byte[] privateKey = BCUtil.encodeECPrivateKey(create.getPrivateKey());
        // 公钥（未压缩）  公钥的第一个字节用于表示是否压缩  getEncoded: false 不压缩  true: 压缩
        byte[] publicKey = ((BCECPublicKey) create.getPublicKey()).getQ().getEncoded(false);
        // 公钥（压缩）
        byte[] publicKeyEc = BCUtil.encodeECPublicKey(create.getPublicKey());
        // 公私秘钥
        SM2_PRIVATE_KEY = HexUtil.encodeHexStr(privateKey);
        SM2_PUBLIC_KEY = HexUtil.encodeHexStr(publicKey);
        System.out.println("私钥: " + HexUtil.encodeHexStr(privateKey));
        System.out.println("公钥: " + HexUtil.encodeHexStr(publicKey));
        System.out.println("压缩后的公钥: " + HexUtil.encodeHexStr(publicKeyEc));
    }

}
