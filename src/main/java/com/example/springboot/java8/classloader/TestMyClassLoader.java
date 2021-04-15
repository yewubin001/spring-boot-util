package com.example.springboot.java8.classloader;

/**
 * @Author yewubin
 * <p>
 * Date 2021-04-15 23:16
 *
 *
 */
public class TestMyClassLoader {
    /**
     * 个人的经验来看，最容易出问题的点是第二行的打印出来的是"sun.misc.Launcher$AppClassLoader"。造成这个问题的关键在于MyEclipse是
     * 自动编译的，Person.java这个类在ctrl+S保存之后或者在Person.java文件不编辑若干秒后，MyEclipse会帮我们用户自动编译Person.java，
     * 并生成到CLASSPATH也就是bin目录下。在CLASSPATH下有Person.class，那么自然是由Application ClassLoader来加载这个.class文件了。
     * 解决这个问题有两个办法：
     *
     * 1、删除CLASSPATH下的Person.class，CLASSPATH下没有Person.class，Application ClassLoader就把这个.class文件交给下一级用户
     * 自定义ClassLoader去加载了
     *
     * 2、TestMyClassLoader类的第5行这么写"MyClassLoader mcl = new MyClassLoader(ClassLoader.getSystemClassLoader().
     * getParent());"， 即把自定义ClassLoader的父加载器设置为Extension ClassLoader，这样父加载器加载不到Person.class，
     * 就交由子加载器MyClassLoader来加载了
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        MyClassLoader mcl = new MyClassLoader("D:/Person.class");
        //即把自定义ClassLoader的父加载器设置为Extension ClassLoader，这样父加载器加载不到Person.class，就交由子加载器MyClassLoader来加载了
        //MyClassLoader mcl = new MyClassLoader(ClassLoader.getSystemClassLoader().getParent());
        Class<?> c1 = Class.forName("com.example.springboot.java8.classloader.Person", true, mcl);
        Object obj = c1.newInstance();
        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());
    }
}
