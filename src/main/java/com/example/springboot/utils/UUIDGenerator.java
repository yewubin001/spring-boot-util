package com.example.springboot.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * @Auther: yewub
 * @Date: 2019/5/27 15:45
 * @Description:
 */
public class UUIDGenerator {
    /**
     * 根据传入的参数生成一个UUID
     *
     * @param params
     * @return
     */
    public static String generateUUID(String[] params) {
        String originString = StringUtils.join(params);
        UUID uuid = UUID.nameUUIDFromBytes(originString.getBytes());
        return Base58.base58Uuid(uuid);
    }

    public static String randomUUID() {
        return Base58.base58Uuid(UUID.randomUUID());
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }


}
