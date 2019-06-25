package com.example.springboot.utils;

import jodd.bean.BeanCopy;
import jodd.bean.BeanUtil;
import jodd.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: yewub
 * @Date: 2019/3/15 13:45
 * @Description:
 */
public class BeanUtils {

    private static final Logger LOG = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * 拷贝相同的属性，如果属性源的属性值为空就不用拷贝。
     * 如果属性名以　_ 或者　$ 开头会被忽略。
     */
    public static void copySameProperties(Object destination, Object source, String... excludeProperties) {
        String[] excludes = getExcludes(source, excludeProperties);
        BeanCopy.beans(source, destination)
                .ignoreNulls(true)
                .exclude(excludes)
                .copy();
    }

    private static String[] getExcludes(Object source, String... excludeProperties) {
        List<String> excludes = new ArrayList<>();
        Field[] declaredFields = source.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            String name = declaredField.getName();
            if (isSystemField(name)) {
                excludes.add(name);
            }
        }

        excludes.addAll(Arrays.asList(excludeProperties));
        return excludes.toArray(new String[excludes.size()]);
    }

    /**
     * Make the first char of string object to lower case.
     */
    public static String lowerCaseFirstChar(String value) {
        if (StringUtil.isEmpty(value)) {
            return "";
        }
        return Character.toLowerCase(value.charAt(0)) + value.substring(1);
    }

    /**
     * Check the field name is start with "_" or "$".
     *
     * @param name the field name.
     * @return true if field name start with "_" or "$", false otherwise.
     */
    public static boolean isSystemField(String name) {
        if (StringUtil.isEmpty(name)) {
            return false;
        }
        return name.startsWith("_") || name.startsWith("$");
    }


    /**
     * 根据指定参数值获取对应的枚举对象
     *
     * @param enumClazz  枚举的class
     * @param paramValue 参数的value
     * @param paramName  具体的参数名字 默认值为"value"
     */
    public static <T> T getEnumByParameterValue(Class<T> enumClazz, String paramValue, String... paramName) {
        String realName = (paramName.length == 0) ? "value" : paramName[0];
        Class<? extends Enum> enumTmp = enumClazz.asSubclass(Enum.class);
        Object[] constants = enumTmp.getEnumConstants();
        for (Object tmp : constants) {
            String value = String.valueOf(BeanUtil.getProperty(tmp, realName));
            if (value.equals(paramValue)) {
                return (T) tmp;
            }
        }
        throw new IllegalArgumentException(paramValue + " is illegal");
    }

}