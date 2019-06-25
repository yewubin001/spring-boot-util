package com.example.springboot.books.condition;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Auther: yewub
 * @Date: 2019/4/17 11:38
 * @Description: 条件注解@Conditional
 * 　在之前的学习中，通过活动的profile，我们可以获得不同的Bean。Spring4提供了一个更通用的基于条件的Bean的创建，即使用@Conditional注解。
 * 　@Conditional 根据满足某一个特定条件创建一个特定的Bean。比方说，当某一个jar包在一个类路径下的时候，自动配置一个或多个Bean；
 * 或者只有某个Bean被创建才会创建另外一个Bean。总的来说，就是根据特定条件来控制Bean的创建行为，这样我们可以利用这个特性进行一些自动的配置。
 * 下面示例将以不同的操作系统为条件，我们通过实现Condition接口，并重写其matches方法来构造判断条件。若在Windows系统下运行程序，
 * 则输出列表命令为dir；若在Linux系统下运行程序，则输出列表命令为ls。
 */
public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ConditionConfig.class);

        ListService listService = context.getBean(ListService.class);

        System.out.println(context.getEnvironment().getProperty("os.name") + "系统下的列表命令为：" + listService.showListCmd());
    }

}
