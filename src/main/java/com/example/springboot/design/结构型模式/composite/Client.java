package com.example.springboot.design.结构型模式.composite;

/**
 * @version : 1.0
 * @Description: TODO
 * @Auther: ywb
 * @Date: 2020/11/11 17:08
 */
public class Client {

    public static void main(String[] args) {
        // 定义多个Composite组合对象
        Component root = new Composite("服装");
        Component c1 = new Composite("男装");
        Component c2 = new Composite("女装");
        Component c3 = new Composite("母婴");

        // 定义多个Leaf叶子对象
        Component leaf1 = new Leaf("西服");
        Component leaf2 = new Leaf("夹克");
        Component leaf3 = new Leaf("衬衫");
        Component leaf4 = new Leaf("裙子");
        Component leaf5 = new Leaf("套装");
        Component leaf6 = new Leaf("鞋袜");
        Component leaf7 = new Leaf("孕妇装");
        Component leaf8 = new Leaf("婴儿装");

        // 组合成为树形的对象结构
        root.addChild(c1);
        root.addChild(c2);
        root.addChild(leaf6);
        c1.addChild(leaf1);
        c1.addChild(leaf2);
        c1.addChild(leaf3);
        c2.addChild(leaf4);
        c2.addChild(leaf5);
        c2.addChild(c3);
        c3.addChild(leaf7);
        c3.addChild(leaf8);

        // 调用根对象的输出功能输出整棵树
        root.someOperation("");
    }

}
