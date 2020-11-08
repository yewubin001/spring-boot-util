package com.example.springboot.java8.reflect;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectTest {

    public static void main(String[] args) throws Exception {
        UserInfo userInfo = new UserInfo("0424", "叶物滨");
        Class<? extends UserInfo> clazz = userInfo.getClass();
//        Class<?> clazz1 = Class.forName("com.example.springboot.java8.reflect.UserInfo");
//        Class<UserInfo> clazz2 = UserInfo.class;
        Field field = clazz.getDeclaredField("login");
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), clazz);
        //读取属性值
        Method readMethod = propertyDescriptor.getReadMethod();
        System.out.println(readMethod.invoke(userInfo));

        // 写入属性值
        Method writeMethod = propertyDescriptor.getWriteMethod();
        writeMethod.invoke(userInfo, "1243456");
        System.out.println(userInfo);

        // 取得包对象
        Package p = clazz.getPackage();
        System.out.println("包名:" + p.getName());
        // 访问修饰符
        int modifier = clazz.getModifiers();
        System.out.println("类访问修饰符：" + Modifier.toString(modifier));

        Constructor ac = clazz.getConstructor(String.class, String.class);
        Object instance = ac.newInstance("8888", "帅帅");
        System.out.println(instance);

        Method declaredMethod = clazz.getDeclaredMethod("toUpper", new Class[]{String.class, String.class});
        System.out.println(declaredMethod.invoke(userInfo, new String[]{"admin", "admin111"}));

        for (Field f : clazz.getDeclaredFields()) {
            //AccessibleTest类中的成员变量为private,故必须进行此操作
            f.setAccessible(true);
            System.out.println(f.get(userInfo));
        }
    }
}
