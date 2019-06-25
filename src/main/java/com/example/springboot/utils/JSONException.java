package com.example.springboot.utils;

/**
 * @Auther: yewub
 * @Date: 2018/11/22 17:16
 * @Description:
 */
public final class JSONException extends RuntimeException {
    public JSONException(final String message) {
        super(message);
    }

    public JSONException(final String message,final Throwable cause) {
        super(message, cause);
    }
}