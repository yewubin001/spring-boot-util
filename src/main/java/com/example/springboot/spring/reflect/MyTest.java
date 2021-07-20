package com.example.springboot.spring.reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @Auther: 59315
 * @Date: 2020/5/24 21:35
 * @Description:
 */
public class MyTest {

    /**
     * 使用反射给对象属性注入值
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        UserController userController = new UserController();

        Class<? extends UserController> clazz = userController.getClass();

        UserService userService = new UserService();

        System.out.println(userService);

        Field filed = clazz.getDeclaredField("userService");

        String name = filed.getName();

        name = name.substring(0, 1).toUpperCase() + name.substring(1);

        Method method = clazz.getDeclaredMethod("set" + name, UserService.class);
        method.setAccessible(true);
        method.invoke(userController, userService);

        System.out.println(userController.getUserService());
    }

    /**
     * 使用自定义注解注入属性值
     * 不需要setUserService方法
     */
    @Test
    public void test2() throws Exception {
        UserController userController = new UserController();

        Class<? extends UserController> clazz = userController.getClass();

        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            AutoWire annotation = field.getAnnotation(AutoWire.class);
            if (Objects.nonNull(annotation)) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                try {
                    Object o = type.newInstance();
                    field.set(userController, o);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println(userController.getUserService());
    }

}


