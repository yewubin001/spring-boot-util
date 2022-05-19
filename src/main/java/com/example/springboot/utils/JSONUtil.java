package com.example.springboot.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @Auther: yewub
 * @Date: 2018/11/22 17:14
 * @Description: json工具类
 */
public class JSONUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JSONUtil.class);
    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        //忽略空属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        //设置转换模式
        mapper.enable(MapperFeature.USE_STD_BEAN_NAMING);
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        mapper.enable(SerializationFeature.WRITE_NULL_MAP_VALUES);
        mapper.enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS);
    }

    /**
     * 把Java对象转为JSON字符串
     *
     * @param obj the object need to transfer into json string.
     * @return json string.
     */
    public static String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            LOG.error("LK-PC0019a: to json exception.", e);
            throw new JSONException("把对象转换为JSON时出错了", e);
        }
    }

    /**
     * 把JSON转换为Java对象
     *
     * @param json  the json string
     * @param clazz will convert into class
     * @param <T>   return type
     * @return java object.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            LOG.error("LK-PC00186: from json exception", e);
            throw new JSONException(e.getLocalizedMessage());
        }
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param jsonStr         json字符串
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类型
     */
    public static <T> T fromJson(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) {
        JavaType javaType = mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
        try {
            return mapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException("convert json error:" + e.getLocalizedMessage());
        }
    }

    /**
     * 把Object对象转换为Class类型的对象
     *
     * @param o
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T convert(Object o, Class<T> tClass) {
        return mapper.convertValue(o, tClass);
    }

    /**
     * 把json字符串 转换为 Map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        return fromJson(json, Map.class);
    }
}
