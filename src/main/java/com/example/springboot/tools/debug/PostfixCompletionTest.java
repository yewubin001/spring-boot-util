package com.example.springboot.tools.debug;

import org.junit.Test;

import java.util.ArrayList;


/**
 * @author 59315
 */
public class PostfixCompletionTest {

    private Object object;

    @Test
    public void postfixTest() {

        // 1. for 循环 list.for  fori  forr 可以使用tab键
        ArrayList<Object> list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {

        }
        // 2. 判空（非空）.null .notnull .nn
        Object object = new Object();
        if (object != null) {

        }
        // 3. boolean 取反 flag.if  flag.not.if
        boolean flag = false;
        if (!flag) {

        }

        // 4. 定义变量 .new  .var .val  .field// 生成一个成员变量
        Object o = new Object();
        this.object = new Object();


        // 5. 异常捕获 shitMayHappen();.try 回车
        try {
            shitMayHappen();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 6. 输出  .sout  .soutv .souf .serr
        String s = "this is message";
        System.out.println(s);
        System.out.println("s = " + s);
        System.out.printf("", s);
        System.err.println(s);

        // 7. 抛出异常 与 返回 new RuntimeException().throw  s.return
        // return s;
        throw new RuntimeException();
    }


    public void shitMayHappen() throws Exception {
        throw new RuntimeException("with error");
    }
}
