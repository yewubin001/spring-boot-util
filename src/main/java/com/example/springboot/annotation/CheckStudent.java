package com.example.springboot.annotation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

/**
 * @Auther: yewub
 * @Date: 2018/11/9 10:42
 * @Description:
 */
public class CheckStudent {

    public static void main(String[] args) throws IllegalAccessException {
        StudentVO studentVO = new StudentVO();
        //studentVO.setName("ywb");
        studentVO.setTel("17521519023");
        studentVO.setSex(20);

        checkParamters(studentVO);
    }

    public static void checkParamters(StudentVO studentVO) throws RuntimeException, IllegalAccessException {
        Class clazz = StudentVO.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(CustomAnnotation.class)) {
                CustomAnnotation annotation = field.getAnnotation(CustomAnnotation.class);
                if (annotation.require()) {
                    // 成员变量为private,故必须进行此操作
                    field.setAccessible(true);
                    Object value = field.get(studentVO);
                    if (field.getGenericType().toString() instanceof String) {
                        if (value == null || "".equals(value)) {
                            throw new RuntimeException(annotation.info());
                        }
                    }
                }
            }
        }

    }
}
