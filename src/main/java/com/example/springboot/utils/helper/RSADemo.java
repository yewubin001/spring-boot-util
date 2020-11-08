package com.example.springboot.utils.helper;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import java.util.Map;

public class RSADemo {

    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String ENCODE_ALGORITHM = "SHA-256";
    public static final String PLAIN_TEXT = "test string";

    public static void main(String[] args) {
        // 公私钥对
        Map<String, byte[]> keyMap = RSA.generateKeyBytes();
        PublicKey publicKey = RSA.restorePublicKey(keyMap.get(RSA.PUBLIC_KEY));
        PrivateKey privateKey = RSA.restorePrivateKey(keyMap.get(RSA.PRIVATE_KEY));

        System.out.println(new String(Base64.getEncoder().encode(keyMap.get(RSA.PUBLIC_KEY))));
        System.out.println("--------------------------------------");
        System.out.println(new String(Base64.getEncoder().encode(keyMap.get(RSA.PRIVATE_KEY))));

        // 签名
        byte[] sing_byte = sign(privateKey, PLAIN_TEXT);
        // 验签
        verifySign(publicKey, PLAIN_TEXT, sing_byte);

    }

    /**
     * SHA-256加密 + 签名
     *
     * @param privateKey 私钥
     * @param plain_text 明文
     * @return
     */
    public static byte[] sign(PrivateKey privateKey, String plain_text) {
        MessageDigest messageDigest;
        byte[] signed = null;
        try {
            messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            messageDigest.update(plain_text.getBytes());
            byte[] outputDigest_sign = messageDigest.digest();
            System.out.println("SHA-256加密后-----》" + bytesToHexString(outputDigest_sign));
            //byte[] outputDigest_sign = plain_text.getBytes("utf-8");
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
            Sign.update(outputDigest_sign);
            signed = Sign.sign();
            System.out.println("SHA256withRSA签名后-----》" + bytesToHexString(signed));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signed;
    }

    /**
     * SHA-256加密 + 验签
     *
     * @param publicKey  公钥
     * @param plain_text 明文
     * @param signed     签名
     */
    public static boolean verifySign(PublicKey publicKey, String plain_text, byte[] signed) {

        MessageDigest messageDigest;
        boolean SignedSuccess = false;
        try {
            messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            messageDigest.update(plain_text.getBytes());
            byte[] outputDigest_verify = messageDigest.digest();
            //byte[] outputDigest_verify = plain_text.getBytes("utf-8");
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(outputDigest_verify);
            SignedSuccess = verifySign.verify(signed);
            System.out.println("验证成功？---" + SignedSuccess);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return SignedSuccess;
    }

    /**
     * bytes[]换成16进制字符串
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
