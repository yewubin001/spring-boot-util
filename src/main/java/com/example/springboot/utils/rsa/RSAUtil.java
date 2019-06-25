package com.example.springboot.utils.rsa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;


/**
 * RSA安全编码组件
 */
public abstract class RSAUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(RSAUtil.class);

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key  Base64编码格式的公钥
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key)
            throws Exception {
        byte[] encryptedData = null;
        // 对公钥解密
        byte[] keyBytes = HashUtil.decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        int maxEncryptBlockSize = getMaxEncryptBytesByPublicKey(keyFactory, publicKey);

        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            int dataLength = data.length;
            for (int i = 0; i < data.length; i += maxEncryptBlockSize) {
                int encryptLength = dataLength - i < maxEncryptBlockSize ? dataLength - i
                        : maxEncryptBlockSize;
                byte[] doFinal = cipher.doFinal(data, i, encryptLength);
                bout.write(doFinal);
            }
            encryptedData = bout.toByteArray();
        } finally {
            if (bout != null) {
                bout.close();
            }
        }

        return encryptedData;
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data       加密数据
     * @param privateKey Base64编码格式的私钥
     * @return 经过Base64编码的字符串
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = HashUtil.decryptBASE64(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return HashUtil.encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data      加密数据
     * @param publicKey 公钥
     * @param sign      数字签名
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {

        // 解密由base64编码的公钥
        byte[] keyBytes = HashUtil.decryptBASE64(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(HashUtil.decryptBASE64(sign));
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key  Base64编码格式的私钥
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key)
            throws Exception {
        byte[] decryptedData = null;

        // 对密钥解密
        byte[] keyBytes = HashUtil.decryptBASE64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        int maxDecryptBlockSize = getMaxDencryptBytesByPrivate(keyFactory, privateKey);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            int dataLength = data.length;
            for (int i = 0; i < dataLength; i += maxDecryptBlockSize) {
                int decryptLength = dataLength - i < maxDecryptBlockSize ? dataLength - i : maxDecryptBlockSize;
                byte[] doFinal = cipher.doFinal(data, i, decryptLength);
                bout.write(doFinal);
            }
            decryptedData = bout.toByteArray();
        } finally {
            if (bout != null) {
                bout.close();
            }
        }

        return decryptedData;

    }


    /**
     * 获取公钥加密可加密的最大数据字节长度
     *
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getMaxEncryptBytesByPublicKey(KeyFactory keyFactory, Key key) {
        return getPublicKeyBitLength(keyFactory, key) / 8 - 11;
    }

    /**
     * 获取私钥解密每块的字节长度
     *
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getMaxDencryptBytesByPrivate(KeyFactory keyFactory, Key key) {
        return getPrivateKeyBitLength(keyFactory, key) / 8;
    }

    /**
     * 获取公钥的字节长度
     *
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getPublicKeyBitLength(KeyFactory keyFactory, Key key) {
        try {
            RSAPublicKeySpec publicKeySpec = keyFactory.getKeySpec(key, RSAPublicKeySpec.class);
            return publicKeySpec.getModulus().bitLength();
        } catch (Exception e) {
            throw new RuntimeException("获取公钥的字节长度异常", e);
        }
    }

    /**
     * 获取私钥的字节长度
     *
     * @param keyFactory
     * @param key
     * @return
     */
    private static int getPrivateKeyBitLength(KeyFactory keyFactory, Key key) {
        try {
            RSAPrivateKeySpec publicKeySpec = keyFactory.getKeySpec(key, RSAPrivateKeySpec.class);
            return publicKeySpec.getModulus().bitLength();
        } catch (Exception e) {
            throw new RuntimeException("获取私钥的字节长度异常", e);
        }
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() {
        Map<String, Object> keyMap;
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator
                    .getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(2048);

            KeyPair keyPair = keyPairGen.generateKeyPair();

            // 公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            String publicKeys = HashUtil.encryptBASE64(publicKey.getEncoded());
            LOGGER.info("公钥：{}", publicKeys);
            // 私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            String privateKeys = HashUtil.encryptBASE64(privateKey.getEncoded());
            LOGGER.info("私钥：{}", privateKeys);
            keyMap = new HashMap<>(2);

            keyMap.put(PUBLIC_KEY, publicKeys);
            keyMap.put(PRIVATE_KEY, privateKeys);
        } catch (Exception e) {
            throw new RuntimeException("初始化密钥异常", e);
        }
        return keyMap;
    }

}

