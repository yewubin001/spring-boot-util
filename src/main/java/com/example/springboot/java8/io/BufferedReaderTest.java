package com.example.springboot.java8.io;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther: 59315
 * @Date: 2021/2/28 14:35
 * @Description:
 * 1->北国风光，千里冰封，万里雪飘。
 * 2->望长城内外，惟余莽莽；大河上下，顿失滔滔。
 * 3->山舞银蛇，原驰蜡象，欲与天公试比高。
 * 4->须晴日，看红装素裹，分外妖娆。
 * 5->江山如此多娇，引无数英雄竞折腰。
 * 6->惜秦皇汉武，略输文采；唐宗宋祖，稍逊风骚。
 */

public class BufferedReaderTest {


    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("F:\\test\\毛泽东.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("F:\\test\\毛泽东-copy.txt"))) {
            String line;
            // TreeMap<Integer, String> map = new TreeMap<>();
            // 為什麼HashMap这里也是有序的，因为Integer的hash值等于本身，也是有序的。
            Map<Integer, String> map = new HashMap<>();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] split = line.split("->");
                map.put(Integer.valueOf(split[0]), split[1]);
            }
            for (Map.Entry<Integer, String> entry : map.entrySet()) {
                String value = entry.getValue();
                bw.write(value);
                bw.newLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
