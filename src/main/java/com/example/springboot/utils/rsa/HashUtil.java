package com.example.springboot.utils.rsa;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.DigestUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 基础加密组件
 */
public abstract class HashUtil {
    public static final String KEY_SHA = "SHA";
    public static final String KEY_SHA_256 = "SHA-256";
    public static final String KEY_MD5 = "MD5";

    /**
     * MAC算法可选以下多种算法
     * 
     * <pre>
     * HmacMD5 
     * HmacSHA1 
     * HmacSHA256 
     * HmacSHA384 
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";

    /**
     * BASE64解密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) {
        return Base64.decodeBase64(key);
    }

    /**
     * BASE64加密
     * 
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) {
        return Base64.encodeBase64String(key);
        //return Base64.encodeBase64URLSafeString(key);
    }

    /**
     * MD5加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();

    }

    /**
     * SHA加密
     * 
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);
        return sha.digest();
    }
    
    /**
     * SHA-256加密
     * 
     * @param data 待hash的二进制数据
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA256(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA_256);
        sha.update(data);
        return sha.digest();
    }

    /**
     * 初始化HMAC密钥
     * 
     * @return
     * @throws Exception
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);
        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     * 
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }

    public static void main(String[] args) throws Exception {
        //MD5加密
        //1、使用JDK自带MessageDigest
        byte[] bytes = encryptMD5("叶物滨".getBytes());
        String str = new BigInteger(1, bytes).toString(16);
        System.out.println(str);

        //2、使用Spring自带的DigestUtils
        String md5Str = DigestUtils.md5DigestAsHex("叶物滨".getBytes());
        System.out.println(md5Str);

        //SHA 加密
        byte[] bytes1 = encryptSHA("叶物滨".getBytes());
        String str1 = new BigInteger(1, bytes1).toString(16);
        System.out.println(str1);


        //base64
        String base64 = encryptBASE64("叶物滨".getBytes());
        byte[] bytes2 = decryptBASE64(base64);
        System.out.println(new String(bytes2));

    }
}

