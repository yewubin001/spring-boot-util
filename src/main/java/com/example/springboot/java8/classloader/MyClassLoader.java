package com.example.springboot.java8.classloader;

import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * @Author yewubin
 * 自定义类加载器
 * Date 2021-04-15 23:10
 * <p>
 * 从上面对于java.lang.ClassLoader的loadClass(String name, boolean resolve)方法的解析来看，可以得出以下2个结论：
 * 1、如果不想打破双亲委派模型，那么只需要重写findClass方法即可
 * 2、如果想打破双亲委派模型，那么就重写整个loadClass方法
 */
public class MyClassLoader extends ClassLoader {

    //class文件全路径
    private String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = new File(path);
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            Class<?> c = this.defineClass(name, bytes, 0, bytes.length);
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }
}
