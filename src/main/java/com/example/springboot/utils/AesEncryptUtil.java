package com.example.springboot.utils;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @description: 使用AES-128-CBC加密模式，key需要为16位,key和iv可以相同！
 * @author: yewubin
 * @date: 2021/3/23 15:28
 * @version: v1.0
 */
public class AesEncryptUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AesEncryptUtil.class);
    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * RSA公钥加密AES密钥明文
     *
     * @param aesKey    AES密钥明文
     * @param publicKey RSA公钥
     * @return 密文
     */
    public static String encrypt(String aesKey, String publicKey) {
        String outStr = null;
        try {
            //base64编码的公钥
            byte[] decoded = Base64.decodeBase64(publicKey);
            RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
            //RSA加密
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            outStr = Base64.encodeBase64String(cipher.doFinal(aesKey.getBytes("UTF-8")));
        } catch (Exception e) {
            LOGGER.error("RSA公钥加密AES密钥异常：", e);
        }
        return outStr;
    }

    /**
     * RSA私钥解密AES密钥明文
     *
     * @param aesKey AES密钥明文
     * @param priv   RSA私钥
     * @return 密文
     */
    public static String decode(String aesKey, String priv) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] decodedKey = Base64.decodeBase64(priv.getBytes());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] dataBytes = Base64.decodeBase64(aesKey);
            int inputLen = dataBytes.length;
            int offset = 0;
            byte[] cache;
            int i = 0;
            // 对数据分段解密
            while (inputLen - offset > 0) {
                if (inputLen - offset > MAX_DECRYPT_BLOCK) {
                    cache = cipher.doFinal(dataBytes, offset, MAX_DECRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(dataBytes, offset, inputLen - offset);
                }
                out.write(cache, 0, cache.length);
                i++;
                offset = i * MAX_DECRYPT_BLOCK;
            }
            byte[] decryptedData = out.toByteArray();
            // 解密后的内容
            return new String(decryptedData, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("RSA私钥解密AES密钥异常：", e);
        }
        return null;
    }

    /**
     * 加密方法
     *
     * @param data 要加密的数据
     * @param key  加密key
     * @param iv   加密iv
     * @return 加密的结果
     */
    public static String encrypt(String data, String key, String iv) {
        try {
            //"算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            // 加密
            byte[] encrypted = cipher.doFinal(plaintext);
            //通过Base64转码返回
            return new Base64().encodeToString(encrypted);

        } catch (Exception e) {
            LOGGER.error("加密方法异常:", e);
            return null;
        }
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv   解密iv
     * @return 解密的结果
     */
    public static String desEncrypt(String data, String key, String iv) {
        try {
            byte[] encrypted = new Base64().decode(data);
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
            byte[] original = cipher.doFinal(encrypted);
            String originalString = new String(original);
            return originalString;
        } catch (Exception e) {
            LOGGER.error("解密方法异常:", e);
            return null;
        }
    }

    public static void main(String[] args) {
        // 东营公钥
        String PUBLICKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCK0o0ZJ+hqLs54cxSBEyghd2qqvFeBfHkxPv3+VaBJzn55h09IgonvYyOpVFI4yUPvbeTwck2cIsboivi5xNcLqVpBpmHwXLI1pU30Qxyl7uFcwNZ7KD9qWeoKNWNcqDuvQtfJNnkXjbfxICZbcUdcw3XTDX8sS/8p/hnc96t50QIDAQAB";
        // 偏移量iv
        String IV = "7763164515579839";
        // AES 密钥
        String KEY = "SCgdGQcUHGItg6vF";

        // 我方公钥
        String pub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkTso0qWxf5cIic/gAutfFX9F44g7rjTkLnzMGrtqqwbKTtyPaH0/ndW//J64iDtfJqgA6T7/L+9aiOwnR7aKkPImKK87folxzFN+Jq7BNbBu9nr7rIqGIqitHAMbUBbR1D5tluEnYZvM9vgJj55siKGQZQ3FfN2h1ib/yMCdQBQIDAQAB";
        // 我方私钥
        String priv = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKROyjSpbF/lwiJz+AC618Vf0XjiDuuNOQufMwau2qrBspO3I9ofT+d1b/8nriIO18mqADpPv8v71qI7CdHtoqQ8iYorzt+iXHMU34mrsE1sG72evusioYiqK0cAxtQFtHUPm2W4Sdhm8z2+AmPnmyIoZBlDcV83aHWJv/IwJ1AFAgMBAAECgYBbwTweSHai3vWYs9ZM4gpYyHd74BGg7Q5sNQ0TsFWggeatoto8lrvSb7yJaqlNj6hlj6/s4FglG4/yrLGBMAlgdPUD54KSVsFr65j0JmxOrm4lEy9YwT+sIe07K+TC0gFrCBgiCpmWYuBXaK9dOaNbZmgefysZhuB9rDbSTW2WQQJBAM4RSHrnP7x3JV4eemIQOMupCR+4sCprYN15zgxmdzChbE6tUGLHAWg+0cJ1QWzTp4EJu9y8qHUrlLr+DOxx8SkCQQDMHxRRfaN/hRARSk1AwKe5hqwJepH7cLF58qS4jm0Y3g+5VuFXuV2EmK2PTnmPL7I1gc9TTgmiexK37CtS5vd9AkAksz9NZFIdprqpAJSbUnhLDVfqpmH5rBYcW80uuDESIC3B9WwTq428mzOQhoGYeDmk6a7jqVX3SEO86fIzSjfBAkEAi3DHzouHf3uE6Grbr1psub6YE7mZtCzd8LKjeDhDqvM3xqLlOao1inlKR1WSkRBCK29MFFqBFnAA74f0aKPDeQJADG1ZzXYVLTrXnpNMLXdvAGfhPlMb9EvB8y4JCPOtz2qeoUtSWyvjXEDocTaElyn0IjdTNnn2+gC4qM9UMBqFIg==";

        String json = "{\"custNo\":\"475997455355002880\",\"occurType\":\"01\"}";

        // AES 加密
        String encrypt = AesEncryptUtil.encrypt(KEY, pub);
        System.out.println(encrypt);

        // AES 加密内容
        String encrypt1 = AesEncryptUtil.encrypt(json, KEY, IV);
        System.out.println(encrypt1);

        String decode = AesEncryptUtil.decode(encrypt, priv);
        System.out.println("key:" + decode);

        String s = AesEncryptUtil.desEncrypt(encrypt1, KEY, IV);
        System.out.println("解密完成的内容是：" + s);
    }

}
