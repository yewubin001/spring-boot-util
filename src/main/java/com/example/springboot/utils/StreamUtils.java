package com.example.springboot.utils;

import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description: Stream 工具类
 * @author: yewub
 * @date: 2018/11/16 09:52
 */
public class StreamUtils {

    /**
     * 字符串转InputStream
     *
     * @param string
     * @return
     */
    public static InputStream text2InputStream(String string) {
        if (string == null) {
            throw new IllegalArgumentException("input string must not be null");
        }
        return new ByteArrayInputStream(string.getBytes());
    }

    /**
     * base64转InputStream
     *
     * @param base64Img
     * @return
     */
    public static InputStream base642InputStream(String base64Img) {
        BASE64Decoder decoder = new BASE64Decoder();
        final byte[] decoderBytes;
        try {
            decoderBytes = decoder.decodeBuffer(base64Img);
        } catch (IOException e) {
            throw new RuntimeException("base64 to inputStream error");
        }
        return new ByteArrayInputStream(decoderBytes);
    }
}
