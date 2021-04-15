package com.example.springboot.utils;

import cn.magfin.util.Base58;

import java.util.UUID;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/3/25 14:17
 * @version: v1.0
 */
public class UUIDUtil {

    /**
     * 根据字符串生成固定UUID
     *
     * @param name
     * @return
     */
    public static synchronized String getUUID(String name) {
        UUID uuid = UUID.nameUUIDFromBytes(Base58.base58Uuid(UUID.randomUUID()).getBytes());
        String str = uuid.toString().toUpperCase();
        String uuidStr = str.replace("-", "");
        return name.concat(uuidStr.substring(3));
    }

}
