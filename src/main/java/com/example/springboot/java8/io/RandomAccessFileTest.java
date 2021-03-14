package com.example.springboot.java8.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Auther: 59315
 * @Date: 2021/2/28 22:23
 * @Description:
 */
public class RandomAccessFileTest {

    public static void main(String[] args) {

        try (RandomAccessFile raf = new RandomAccessFile("F:\\工作文档\\出师表.txt", "r")) {
            // 文件指针 初始值为0
            System.out.println("初始指针位置："+raf.getFilePointer());
            raf.seek(100);
            int len;
            byte[] bytes = new byte[1024];
            while((len = raf.read(bytes))!=-1){
                String str = new String (new String(bytes, 0, len).getBytes("gbk"), "utf-8");
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
