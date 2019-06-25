package com.example.springboot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Enumeration;

/**
 * @Auther: yewub
 * @Date: 2019/5/27 16:32
 * @Description: 公司钥读取工具
 */
public final class RsaReadUtil {
    private static final Logger log = LoggerFactory.getLogger(RsaReadUtil.class);

    /**
     * 根据Cer文件读取公钥
     *
     * @param pubCerPath
     * @return
     */
    public static PublicKey getPublicKeyFromFile(String pubCerPath) {
        try (FileInputStream pubKeyStream = new FileInputStream(pubCerPath)) {
            byte[] reads = new byte[pubKeyStream.available()];
            pubKeyStream.read(reads);
            return getPublicKeyByText(new String(reads));
        } catch (FileNotFoundException e) {
            log.error("公钥文件不存在", e);
        } catch (IOException e) {
            log.error("公钥文件读取失败", e);
        }
        return null;
    }

    /**
     * 根据公钥Cer文本串读取公钥
     *
     * @param pubKeyText
     * @return
     */
    public static PublicKey getPublicKeyByText(String pubKeyText) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X509");
            BufferedReader br = new BufferedReader(new StringReader(pubKeyText));
            String line = null;
            StringBuilder keyBuffer = new StringBuilder();
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("-")) {
                    keyBuffer.append(line);
                }
            }
            Certificate certificate = certificateFactory.generateCertificate(new ByteArrayInputStream(Encodes.decodeBase64(keyBuffer.toString())));
            return certificate.getPublicKey();
        } catch (Exception e) {
            log.error("解析公钥内容失败:", e);
        }
        return null;
    }

    /**
     * 根据私钥路径读取私钥
     *
     * @param pfxPath
     * @param priKeyPass
     * @return
     */
    public static PrivateKey getPrivateKeyFromFile(String pfxPath, String priKeyPass) {

        try (InputStream priKeyStream = new FileInputStream(pfxPath)) {
            byte[] reads = new byte[priKeyStream.available()];
            priKeyStream.read(reads);
            return getPrivateKeyByStream(reads, priKeyPass);
        } catch (Exception e) {
            log.error("解析文件，读取私钥失败:", e);
        }
        return null;
    }

    /**
     * 根据PFX私钥字节流读取私钥
     *
     * @param pfxBytes
     * @param priKeyPass
     * @return
     */
    public static PrivateKey getPrivateKeyByStream(byte[] pfxBytes, String priKeyPass) {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            char[] charPriKeyPass = priKeyPass.toCharArray();
            ks.load(new ByteArrayInputStream(pfxBytes), charPriKeyPass);
            Enumeration<String> aliasEnum = ks.aliases();
            String keyAlias = null;
            if (aliasEnum.hasMoreElements()) {
                keyAlias = aliasEnum.nextElement();
            }
            return (PrivateKey) ks.getKey(keyAlias, charPriKeyPass);
        } catch (IOException e) {
            // 加密失败
            log.error("解析文件，读取私钥失败:", e);
        } catch (KeyStoreException e) {
            log.error("私钥存储异常:", e);
        } catch (NoSuchAlgorithmException e) {
            log.error("不存在的解密算法:", e);
        } catch (CertificateException e) {
            log.error("证书异常:", e);
        } catch (UnrecoverableKeyException e) {
            log.error("不可恢复的秘钥异常", e);
        }
        return null;
    }
}
