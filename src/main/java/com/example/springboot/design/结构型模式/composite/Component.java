package com.example.springboot.design.结构型模式.composite;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/11 16:20
 */
public abstract class Component {

    public abstract void someOperation(String preStr);

    public void addChild(Component child) {
        // 缺省的实现，抛出异常，因为叶子对象没有这个功能，或子类未实现这个功能
        throw new UnsupportedOperationException("对象不支持此功能");
    }

    public void removeChild(Component child) {
        // 缺省的实现，抛出异常，因为叶子对象没有这个功能，或子类未实现这个功能
        throw new UnsupportedOperationException("对象不支持此功能");
    }

    public Component getChildren(int index) {
        // 缺省的实现，抛出异常，因为叶子对象没有这个功能，或子类未实现这个功能
        throw new UnsupportedOperationException("对象不支持此功能");
    }
}
