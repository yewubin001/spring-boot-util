package com.example.springboot;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.hibernate.validator.internal.util.privilegedactions.GetResource;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yewubin
 * @date: 2021/8/13 11:58
 * @version: v1.0
 */
public class StringTester  {

    // 1、连接器[Joiner]
    @Test
    public void joiner() {
        HashMap<String, String> map = Maps.newHashMap();
        map.put("name", "周杰伦");
        map.put("age", "36");
        List<Map.Entry<String, String>> list = new ArrayList<>(map.entrySet());
        List<Map.Entry<String, String>> collect = list.stream()
                .sorted(Comparator.comparing(Map.Entry<String, String>::getKey))
                .collect(Collectors.toList());

        // 将排序后的字段使用“&”连接，形成一个字符k1=v1&k2=v2&kN=vN
        System.out.println(Joiner.on("&").withKeyValueSeparator("=").join(collect));


        Joiner joiner = Joiner.on("; ").skipNulls(); //直接忽略null
        System.out.println(joiner.join("Harry", null, "Ron", "Hermione"));

        System.out.println(Joiner.on(",").join(Arrays.asList(1, 5, 7)));

        Joiner aDefault = Joiner.on("; ").useForNull("default"); //空值替换
        System.out.println(aDefault.join("Harry", null, "Ron", "Hermione"));
    }

    //2、拆分器[Splitter]
    @Test
    public void splitter(){
        System.out.println(Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .split("foo,bar,,   qux")); 


    }

    @Test
    public void test() throws IOException {
        String fileNameOk = "CJR330304198612157514.ok";
        String fileDir = GetResource.class.getClassLoader().getResource("templates/").getPath() + "CLA202205060000054046";
        String okFilePath = fileDir.concat(File.separator).concat(fileNameOk);
        File file = new File(okFilePath);
        file.createNewFile();
        System.out.println(file.getAbsolutePath());
    }
}
