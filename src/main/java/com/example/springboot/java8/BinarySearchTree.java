package com.example.springboot.java8;

/**
 * @Auther: 59315
 * @Date: 2019/12/1 23:14
 * @Description:
 */
public class BinarySearchTree {

    private int data;

    private BinarySearchTree left;

    private BinarySearchTree right;

    public BinarySearchTree(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public void insert(BinarySearchTree root, int data) {
        if (root.data < data) {//根节点小于data，插入右子树
            if (root.right == null) {//右边没有值，直接插入
                root.right = new BinarySearchTree(data);
            } else {
                insert(root.right, data);
            }
        } else {
            if (root.left == null) {
                root.left = new BinarySearchTree(data);
            } else {
                insert(root.left, data);
            }
        }

    }

    //中序遍历：左根右
    public void in(BinarySearchTree root) {
        if (root != null) {
            in(root.left);
            System.out.print(root.data + " ");
            in(root.right);
        }
    }

    public static void main(String[] args) {
        int data[] = {5,9,0,1,2,3,10,4};
        BinarySearchTree root = new BinarySearchTree(data[0]);
        for(int i=1; i<data.length; i++){
            root.insert(root, data[i]);
        }
        System.out.println("中序遍历为：");
        root.in(root);

    }

}
