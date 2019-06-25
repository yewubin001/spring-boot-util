package com.example.springboot.utils.rsa;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成密钥位数：512bit、2014bit、2048bit、4096bit
 * 1、对于1024（1024bits）长度的密钥，RSA加密明文最大长度 117【（1024-11）/8】 字节，解密要求密文最大长度为128字节，所以在加密和解密的过程中需要分块进行。
 * 2、对于2048（2048bits）长度的密钥，RSA加密明文最大长度 245【（2048-11）/8】 字节，解密要求密文最大长度为256字节，所以在加密和解密的过程中需要分块进行。
 */
public class RsaDemo {

    private final static Logger LOGGER = LoggerFactory.getLogger(RsaDemo.class);

    /**
     * 验签公钥
     */
    public static String signPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2XrDsYHFTngqY66oAoU6yqOtAGC3VYkcM5SseJ92bC21rT4tRU6L8-cJH904nKN_mj94a1UpbEE1QktHqdB2k6jhGvvpPYT6o6KV3-L1gHGVA56DL-ImYfhXgY3vo81BsgBVoVTe6dNp1m6IWupnIE1Ut7jFhlcR7R26tjy1IXzfNWf3qd7o4tMYM8H_bIabV8B3E9XfRDhYmoir6Kt5l6TH_iMlo4XwfLF-cHPhx1lAhVhits1tMyhCVJz7dsbjxrNw_jcQFmQVbtuGL7c21i3l6ViOh8uoMEnhKzHVt-X_ZMVz99XJhflm1N9pR80h9d--lMRLJQeKiOPO30c7FQIDAQAB";

    /**
     * 加签私钥
     */
    public static String signPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDZesOxgcVOeCpjrqgChTrKo60AYLdViRwzlKx4n3ZsLbWtPi1FTovz5wkf3Tico3-aP3hrVSlsQTVCS0ep0HaTqOEa--k9hPqjopXf4vWAcZUDnoMv4iZh-FeBje-jzUGyAFWhVN7p02nWboha6mcgTVS3uMWGVxHtHbq2PLUhfN81Z_ep3uji0xgzwf9shptXwHcT1d9EOFiaiKvoq3mXpMf-IyWjhfB8sX5wc-HHWUCFWGK2zW0zKEJUnPt2xuPGs3D-NxAWZBVu24YvtzbWLeXpWI6Hy6gwSeErMdW35f9kxXP31cmF-WbU32lHzSH1376UxEslB4qI487fRzsVAgMBAAECggEAcbgU7hO6dV62Y6tnZPH_nQhbJTBtjUeFvJ8IVIalqMl6D13M1EnPOrlbdxL5UEbaKXD46b79SwMJ82Fj1rMP3gRXuQFyo92OJdSVZB3MW96ImfzgAkDR6zatoyvx3AgvSwGLxTGY4lHPLPZdTJT3Wde17d-rQaTz1bZO9I3n9Nrc5GHpu903QJ1dnnvbI7O60fYJRTeIOSTUG8Ns9-TZZ_S8fUrJj6pEhmMsKVAe5bWwOc_E0XgSRPUvxXAmOTN5jFzVY1P9ijsCzQ5zVq4B5zZuxz8A79Dfpso--TvZT1ek-yVgkRBxa6XVMs6YTA5n8_Tm3KvQSIl3mZPDE7va4QKBgQD24nTsIeASOeU6uGXw2BE9Toolnm9gi8a8ZOxpNgB-W5yxUd4nr1BcF6Pd06Q62vnm1GcJluIQgsHtYWxWaylzQO6JUlbvx0VttWl2tLwyQETBxC84aVlW4fIhbndtXnm8eZrAaJ9HNwAHIsfzdA4JfN7f4A0c1gpHgyYOYGbiyQKBgQDhgl9TiB9yDvn3HwAo9-WPW3Ya_Z2OK-eMroNqknE9uqrNsf1cPaTJ6iOX7azU1GNt-_l-WPLTrQjhW3I8jjh816A-GBwmr-LRWTQkwyf0dohsVUltb2OF3LZxwjC1-Mt30y1Nn69UhbUJXS-HYKOB9zU3O3W9x9pxEa78WBGP7QKBgFPXM729A0stPhti47uWpMQdF-0OkORIk4UHAQH5xNEka2IMW1U-xBuCK7P5W5xhINyy5QrsonjY7uKeYxw16C2rr1YTEFL9aSaIpPgbCI5IJRFofmC55g0LTh7sVMJfgxfudA9DeFPG6cuClESo9Ntj_Iwqmklgpr0CXLolIsuBAoGAON5WycrnGy1Zrg8cTDiPpIJNG2tfoy3BO8H6lBKQCiAb2LmNCgjlmKG6DiM61YUl-eN0PH-T5Pua5LHZ8hlvd1QtLduIpdzvFxomwyqP1d9ace9kD_vqHiTVwYRWIl22eeZAY3Xkki0RxWXBiaM-OJUtz1roS06t8JukBDD1ZNUCgYEA8Q6sAkD0gFANCD3uZWJjanDmnMmIlQs_hoL4hjPHqYG4bFWVV6uFP7JmkDSHjJjClBoNq_R1X2jX0U-ziXkp7jZdz4FP7ZD4MoA5qDI4ceOsDNmIdu3FHuihJnh0UTLdszwLQky-yW_BSWSeSWrf3vbA71nXEKqnNTnE6Fs6PWw";

    /**
     * 解密私钥
     */
    private static String encryptPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDKNT4tclwf_Vt_6020yihQNR7uWniZ_i-9Xs4rB-4MP1OVVooQMLsh8rUNxoX2BVG5vhmzPeg1OU3xgvMBpSuO7ifmPqw0xYGfdVbBSobxKZ-wv9oSlPksNmX2NvuNM8BVXgGJSUt4qqANtMtbkM1_Q0KDfDPwU0cZVRLAh8T4UlAyd9frhbKyuoGybktaqTUvUrAPh7cq1rZaqMK6EVFM-4_6JQdeft57Qp8FWKnM-L12KCXLf3IDKeU48sOkNbzegtJ8HyH9AoFfbp6RwWGvL8-YNBxZ0iYwI-pz8P9PSglEXgI-gyAMsY6l4T7VlShoIgHnO7kcGLxYfGXdIrWTAgMBAAECggEAERfXHbhhGUMtjO6zLuk35y_WYYe8UigumO9-thyBfv-LbUBXg27bIs-FhpiVkmp4sh7jxUcibf1eHWBYcncn63kuXkRGAl__yR7-KnDGX3SJT0Ooh5GYTqh6jgmv0gvh4lWEA7ryakziIsZ5uk_PT0a8RKknKtWfWQDcCPJxMBx-mxkxgn1pPcDySnuHot7pWm7TtGAYembvCCWg_HGlBRKaPmdvjp3t-w6X8LURL3NsibvtUpHUjsoMX-lJNYD916eN7fNO-zrOZNtevjeEbaxl5dx3MUmpTXFazhQ6A_hmSk2zIrcXENv3KntpREjrOB_jqqzmN8OlhNiH_sznQQKBgQD2y-euXiigTxExm4wpQatgRzwbk4XbmrNveTRQl_IM_LHvPtgv0mBjIJI8NfxuU8dHdVuX6wUr_Bnb3GcAghEfSJRpy0b97N-YwZRX-3-_1VbVG-duPtCDM2cAyguRHfSTQNCCYPpMhz7iuHVhk-kaeh2vup9HHwLHlqgIj4OdyQKBgQDRv6oC74_qIiC3QU_UK4raNGrztmzW06F0ZKiz1vTfFPjirBDdLrUYyk3p9WPodRk_bCsMvrEGmYS1K3y9FvN6nxvOSCtGU1Wp-OVibJBAM6PzLCfZRsX_0S2m9qYG3AMalEfvHk9CbRgZm5qjyT3qBebxBBsEiLyeyPGHqXG2ewKBgB8HpCmYTSNazZ6fIBBP4WxJiJUvBzWFTepGIv2_HP_lBbo-_I-Gs_fCsKBBaWu1vOiPKOUA97WADQBHxeuabuywA3kJxfNHRRW_wRcO2BEWkcRZrc9-tljIrbq7rJiiW43oKE88Qjw9BPscliWeo0Bg-jqODFi03XSazKXG5ZWBAoGAN31XQvORxUVGFnUm42CpmVSovtUSMjtGyX4fy4QgwCvSNFWFGJ7Utjw_JJgQ2FyyUz8QnIyOVMqkezGIDJZXz6bsOkSJfk9pqE_6j3FdMD75LsoNuMOqeiUiyLsT7VijUZLM_QutuEsYRx4NaStCP_lTwYtugmKSVAehLly7gqUCgYEAwHKpGSWtXWfL9uf1J8t_JilcIMwCstmOeXFxzf4QTwGkYDIzfqN1G_iYzc1hMUzImdgj-do48eQ_4EDSMx_xP3ZWdmEdaDJZDVDtfH5omkYIO__lj6ECPxlCc5VqM7oTj43iefLe-Zb0DX6xG97ibYyfZTxaDD77LfwT8yGwSsA";

    /**
     * 加密公钥
     */
    public static String encryptPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyjU-LXJcH_1bf-tNtMooUDUe7lp4mf4vvV7OKwfuDD9TlVaKEDC7IfK1DcaF9gVRub4Zsz3oNTlN8YLzAaUrju4n5j6sNMWBn3VWwUqG8SmfsL_aEpT5LDZl9jb7jTPAVV4BiUlLeKqgDbTLW5DNf0NCg3wz8FNHGVUSwIfE-FJQMnfX64WysrqBsm5LWqk1L1KwD4e3Kta2WqjCuhFRTPuP-iUHXn7ee0KfBVipzPi9digly39yAynlOPLDpDW83oLSfB8h_QKBX26ekcFhry_PmDQcWdImMCPqc_D_T0oJRF4CPoMgDLGOpeE-1ZUoaCIB5zu5HBi8WHxl3SK1kwIDAQAB";

    public static void main(String[] args) {
        // 加密加签
        encode("上海磁金融");
        // 解签解密
        LOGGER.info("解签后数据---->{}", decode());
    }

    // 参数容器
    static Map<String, String> paramMap = new HashMap<String, String>();

    private static String charset = "utf-8";

    public static void encode(String bizParam) {
        try {
            // 使用网金社公钥对业务参数进行加密
            String bizParamEncrypt = HashUtil
                    .encryptBASE64(RSAUtil.encryptByPublicKey(bizParam.getBytes(charset), encryptPublicKey));
            paramMap.put("bizParams", bizParamEncrypt);

            // 计算并组装签名
            String sign = RSAUtil.sign(bizParam.getBytes(charset), signPrivateKey);
            paramMap.put("sign", URLEncoder.encode(sign, charset));
        } catch (Exception e) {
            throw new RuntimeException("加密加签字符串[" + bizParam + "]时遇到异常", e);
        }
    }

    public static String decode() {
        // 解密参数
        String bizParams = paramMap.get("bizParams");
        String bizParamsJson = null;
        try {
            bizParamsJson = new String(
                    RSAUtil.decryptByPrivateKey(HashUtil.decryptBASE64(bizParams), encryptPrivateKey));
        } catch (Exception e) {
            throw new RuntimeException("解密字符串[" + bizParams + "]时遇到异常", e);
        }
        // 验签
        checkSign(bizParamsJson, signPublicKey, paramMap.get("sign"));

        return bizParamsJson;
    }

    private static void checkSign(String data, String publicKey, String sign) {
        boolean verify;
        try {
            verify = RSAUtil.verify(data.getBytes(charset), publicKey, sign);
        } catch (Exception e) {
            throw new RuntimeException("验签发生异常", e);
        }
        if (!verify) {
            LOGGER.debug("验签失败");
        } else {
            LOGGER.debug("验签成功");
        }

    }

    /**
     * 获取初始化密钥
     */
    @Test
    public void initKey() {
        RSAUtil.initKey();
    }

}
