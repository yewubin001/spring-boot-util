package com.example.springboot.java8.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Auther: 59315
 * @Date: 2021/2/28 00:02
 * @Description: 
 */

public class FileOutputStreamTest {


    public static void main(String[] args)   {
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            fos = new FileOutputStream("F:\\工作文档\\技术xxxxx.jpg");
            fis = new FileInputStream("F:\\工作文档\\技术.jpg");
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
        } catch (IOException e) {
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fos = null;
                fis = null;
            }
        }

    }

}
