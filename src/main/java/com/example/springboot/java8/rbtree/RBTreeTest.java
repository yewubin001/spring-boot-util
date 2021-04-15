package com.example.springboot.java8.rbtree;


import java.util.Scanner;

/**
 * @Auther: 59315
 * @Date: 2021/3/25 23:22
 * @Description:  红黑树测试类
 */
public class RBTreeTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RBTree<Integer, Object> rbTree = new RBTree<>();
        while(true) {
            System.out.println("请输入key:");
            Integer key = scanner.nextInt();
            System.out.println();
            rbTree.insert(key, null);
            //rbTree.inOrderPrint();  中序遍历
            TreeOperation.show(rbTree.getRoot());
        }
    }
}
