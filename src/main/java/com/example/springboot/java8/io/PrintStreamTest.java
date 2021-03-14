package com.example.springboot.java8.io;

import java.io.*;
import java.util.Scanner;

/**
 * @Auther: 59315
 * @Date: 2021/2/28 22:00
 * @Description:
 */

public class PrintStreamTest {

    public static void main(String[] args) {
        try (InputStream is = new FileInputStream("F:\\工作文档\\dayinliu.txt")) {
            System.setIn(is);
            Scanner scanner = new Scanner(System.in);
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
