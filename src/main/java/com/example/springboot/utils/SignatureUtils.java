package com.example.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Auther: yewub
 * @Date: 2019/5/27 16:31
 * @Description:
 */
public class SignatureUtils {

    private static final Logger logger = LoggerFactory.getLogger(SignatureUtils.class.getName());

    /**
     * 加密算法RSA
     */
    public static final String DEFAULT_KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    public static final String DEFAULT_SIGNATURE_ALGORITHM = "SHA1withRSA";
    public static final String DSA_KEY_ALGORITHM = "DSA";

    /**
     * @encryptStr 摘要
     * @signature 签名
     * @pubCerPath 公钥路径
     * 验签
     */
    public static boolean verifySignature(String pubCerPath, String encryptStr, String signature) throws Exception {
        PublicKey publicKey = RsaReadUtil.getPublicKeyFromFile(pubCerPath);
        return verify(encryptStr.getBytes(StandardCharsets.UTF_8), publicKey.getEncoded(), signature);
    }

    /**
     * @encryptStr 摘要
     * @pfxPath pfx证书路径
     * @priKeyPass 私钥
     * @charset 编码方式
     * 签名
     */
    public static String encryptByRSA(String encryptStr, String pfxPath, String priKeyPass) {
        PrivateKey privateKey = RsaReadUtil.getPrivateKeyFromFile(pfxPath, priKeyPass);
        return sign(encryptStr.getBytes(StandardCharsets.UTF_8), privateKey.getEncoded());
    }

    /**
     * 校验数字签名
     *
     * @param data     已加密数据
     * @param keyBytes 公钥
     * @param sign     数字签名
     * @throws Exception
     */
    public static boolean verify(byte[] data, byte[] keyBytes, String sign) throws Exception {
        return verify(data, keyBytes, FormatUtil.hex2Bytes(sign), DEFAULT_SIGNATURE_ALGORITHM);
    }


    /**
     * 用私钥对信息生成数字签名
     * <p>
     * 使用默认的 “SHA1withRSA”算法
     *
     * @param data 已加密数据
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, byte[] keyBytes) {
        return FormatUtil.byte2Hex(sign(data, keyBytes, DEFAULT_SIGNATURE_ALGORITHM));
    }

    /**
     * 用私钥对信息生成数字签名，可以自定义签名算法，但签名算法局限于RSA算法
     *
     * @param data               需要加签的数据
     * @param keyBytes           秘钥字节码
     * @param signatureAlgorithm 签名算法，包括SHA1withRSA、SHA224withRSA、SHA256withRSA、SHA384withRSA、SHA512withRSA
     * @return 签名结果
     */
    public static byte[] sign(byte[] data, byte[] keyBytes, String signatureAlgorithm) {
        return sign(data, keyBytes, signatureAlgorithm, DEFAULT_KEY_ALGORITHM);
    }


    /**
     * 用私钥对信息生成数字签名，可以自定义签名算法，但签名算法局限于DSA算法
     *
     * @param data               需要加签的数据
     * @param keyBytes           秘钥字节码
     * @param signatureAlgorithm 签名算法，包括：SHA1withDSA, SHA224withDSA, SHA256withDSA
     * @return 签名结果
     */
    public static byte[] signByDSA(byte[] data, byte[] keyBytes, String signatureAlgorithm) {
        return sign(data, keyBytes, signatureAlgorithm, DSA_KEY_ALGORITHM);
    }

    /**
     * 用私钥对信息进行签名
     *
     * @param data               需要加签的数据
     * @param keyBytes           私钥字节码
     * @param signatureAlgorithm 签名算法
     * @param keyAlgorithm       公私钥算法
     * @return 签名结果
     */
    public static byte[] sign(byte[] data, byte[] keyBytes, String signatureAlgorithm, String keyAlgorithm) {
        try {
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
            PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(signatureAlgorithm);
            signature.initSign(privateK);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            logger.error("sign error: {}", e.getMessage());
            throw new RuntimeException("数据加签失败", e);
        }
    }

    /**
     * 验证签名，签名算法局限于RSA
     *
     * @param data               需要验签的数据
     * @param keyBytes           公钥数据
     * @param sign               签名摘要
     * @param signatureAlgorithm 验签算法
     * @return 验证结果
     */
    public static boolean verify(byte[] data, byte[] keyBytes, byte[] sign, String signatureAlgorithm) {
        return verify(data, keyBytes, sign, signatureAlgorithm, DEFAULT_KEY_ALGORITHM);
    }

    /**
     * 验证签名，签名算法局限于DSA
     *
     * @param data               需要验签的数据
     * @param keyBytes           公钥数据
     * @param sign               签名摘要
     * @param signatureAlgorithm 验签算法
     * @return 验证结果
     */
    public static boolean verifyByDSA(byte[] data, byte[] keyBytes, byte[] sign, String signatureAlgorithm) {
        return verify(data, keyBytes, sign, signatureAlgorithm, DSA_KEY_ALGORITHM);
    }

    /**
     * 验证签名，签名算法局限于RSA
     *
     * @param data               需要验签的数据
     * @param keyBytes           公钥数据
     * @param sign               签名摘要
     * @param signatureAlgorithm 验签算法，包括：SHA1withDSA, SHA224withDSA, SHA256withDSA
     * @param keyAlgorithm       公私钥算法
     * @return 验证结果
     */
    public static boolean verify(byte[] data, byte[] keyBytes, byte[] sign, String signatureAlgorithm, String keyAlgorithm) {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
            PublicKey publicK = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(signatureAlgorithm);
            signature.initVerify(publicK);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            logger.error("verify error: {}", e.getMessage());
            throw new RuntimeException("签名验证失败", e);
        }
    }

}
