package com.example.springboot.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * @authoer: luokui
 * @date: 2019-05-16
 */
public class Util {
    /**
     * 根据相对路径获取文件内容
     */
    public static String getTxt(String fileName) {
        Resource resource = new ClassPathResource(fileName);
        try {
            File file = resource.getFile();
            return FileUtils.readFileToString(file, "utf-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
