package com.example.springboot.configuration;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: yewub
 * @Date: 2019/2/14 13:35
 * @Description:
 */
@SpringBootTest
public class AppTest {

    @Test
    public void test1() {
        AnnotationConfigApplicationContext acac =
                new AnnotationConfigApplicationContext(AppConfig.class);
        //上面的代码等同于：
        //AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
        //context.register(AppConfig.class);
        Book book = (Book) acac.getBean("book");
        System.out.println(book);
    }

    @Test
    public void test2() {
        //把AppTest的注解放开
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext();
        context.register(AppService.class);
        context.refresh();//很重要
        System.out.println(context.getBean(AppService.class).getClass().getSimpleName());
    }

    @Test
    public void test3() {
        //把AppTest的注解注释掉
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.registerBean("myService", AppService.class, () -> new AppService("hello world"), z -> {
            z.setScope("prototype");
        });
        context.refresh();
        System.out.println(context.getBean("myService").getClass().getSimpleName());
        System.out.println(context.getBeanDefinition("myService").getScope());
    }
}
