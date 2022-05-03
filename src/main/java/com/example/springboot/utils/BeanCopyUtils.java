package com.example.springboot.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Dto，Vo，entity 转换工具类
 */
public class BeanCopyUtils<V, E> {

    /**
     * BeanCopier的缓存
     */
    static final ConcurrentHashMap<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    /**
     * dot ,do ,entity 相互转换（使用cglib）
     * @param oldClass 原数据--Dto，Vo，entity
     * @param newClass 转换为--Dto，Vo，entity
     * @return
     */
    public static <E> E convert(Object oldClass, Class<E> newClass) {
        if (oldClass == null) {
            return null;
        }
        if (newClass == null) {
            return null;
        }
        E newInstance = null;
        try {
            newInstance = newClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        String key = oldClass.getClass().getName() + ":" + newClass.getName();
        BeanCopier copier = null;
        if (BEAN_COPIER_CACHE.containsKey(key)) {
            copier = BEAN_COPIER_CACHE.get(key);
        } else {
            copier = BeanCopier.create(oldClass.getClass(), newInstance.getClass(), false);
            BEAN_COPIER_CACHE.put(key, copier);
        }
        copier.copy(oldClass, newInstance, null);
        return newInstance;
    }

    /**
     * list<Entity> 集合对象转list<Vo> ( list 循环)
     *
     * @param oldList
     * @param v
     */
    public static <T, V> List<V> listVo(List<T> oldList, Class<V> v) {
        if (oldList == null) {
            return null;
        }
        List<V> voList = new ArrayList<>();
        oldList.forEach(i -> voList.add(BeanCopyUtils.convert(i, v)));
        return voList;
    }

    /**
     * list<Entity> 集合对象转list<Vo> （Stream 方式）
     *
     * @param oldList
     * @param v
     */
    public static <T, V> List<V> listVoStream(List<T> oldList, Class<V> v) {
        if (oldList == null || oldList.size() == 0) {
            return null;
        }
        return oldList.stream().map(item -> (V) BeanCopyUtils.convert(item, v)).collect(Collectors.toList());
    }
}
