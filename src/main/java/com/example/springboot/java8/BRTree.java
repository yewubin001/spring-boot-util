package com.example.springboot.java8;

/**
 * @Auther: 59315
 * @Date: 2021/3/21 15:19
 * @Description: 1、创建BRTree，定义颜色
 * 2、创建BRNode
 * 3、辅助方法定义：parentOf(node),isRed(node),isBlack(node),setRed(node),setBlack(node),inOrderPrint()
 * 4、左旋定义方法：leftRotate(node)
 * 5、右旋定义方法：rightRotate(node)
 * 6、公开插入方法定义：insert(K key, V value)
 * 7、内部插入方法定义：insert(RBNode node)
 * 8、修正插入导致红黑树失衡的方法定义：insertFixUp(RBNode node)
 * 9、测试红黑树的正确性
 */
public class BRTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBNode root;

    public RBNode getRoot() {
        return root;
    }

    /**
     * 获取当前节点的父节点
     *
     * @param node
     * @return
     */
    public RBNode parentOf(RBNode node) {
        if (node != null) {
            return node.getParent();
        }
        return null;

    }

    /**
     * 节点是否为红色
     *
     * @param node
     * @return
     */
    public boolean isRed(RBNode node) {
        if (node != null) {
            return node.color == RED;
        }
        return false;
    }

    /**
     * 设置节点为红色
     *
     * @param node
     */
    public void setRed(RBNode node) {
        if (node != null) {
            node.setColor(RED);
        }
    }

    /**
     * 节点是否为黑色
     *
     * @param node
     * @return
     */
    public boolean isBlack(RBNode node) {
        if (node != null) {
            return node.color == BLACK;
        }
        return false;
    }

    /**
     * 设置节点为黑色
     *
     * @param node
     */
    public void setBlack(RBNode node) {
        if (node != null) {
            node.setColor(BLACK);
        }
    }


    /**
     * 中序打印红黑树
     */
    public void inOrderPrint() {
        inOrderPrint(this.root);
    }

    private void inOrderPrint(RBNode root) {
        if (root != null) {
            inOrderPrint(root.left);
            System.out.println("key:" + root.key + ",value:" + root.value);
            inOrderPrint(root.right);
        }
    }

    /**
     * 左旋:
     * 左旋示意图：左旋x节点
     *      P                            P
     *      |                            |
     *      x                            y
     *    /  \          ----->         /  \
     *   lx   y                       x   ry
     *       / \                    /  \
     *     ly   ry                lx   ly
     *
     * 1、将x的右子节点更新为y的左子节点（ly），将y的左子节点的父节点更新为x
     * 2、当x的父节点不为空时，更新y的父节点指向x的父节点，并将x的父节点 指定为 y
     * 3、将x的父节点更新为 y，将y的左子节点更新为 x
     */
    public void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        if (x.parent != null) {
            y.parent = x.parent;
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            this.root = y;
            this.root.parent = null;
        }

        x.parent = y;
        y.left = x;
    }


    /**
     * 右旋:
     * 右旋示意图：右旋y节点
     *       P                       P
     *       |                       |
     *       y                       x
     *     /  \      ------->      /  \
     *    x   ry                  lx   y
     *  /  \                          / \
     * lx   ly                       ly   ry
     *
     * 1、将y的左子节点更新为x的右子节点，并将x的右子节点的父节点更新为 y
     * 2、当y的父节点不为空时，更新x的父节点指向y的父节点，并将y的父节点 指定为 x
     * 3、将y的父节点更新为 x，将x的右子节点更新为 y
     */
    public void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }

        if (y.parent != null) {
            x.parent = y.parent;
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        } else {
            this.root = x;
            this.root.parent = null;
        }

        y.parent = x;
        x.right = y;
    }

    /**
     * 公共的插入方法
     *
     * @param key
     * @param value
     */
    public void insert(K key, V value) {
        RBNode node = new RBNode();
        node.setKey(key);
        node.setValue(value);
        node.setColor(RED);

        insert(node);
    }

    private void insert(RBNode node) {
        // 第一步，查找当前节点的父节点
        RBNode parent = null;
        RBNode x = this.root;
        while (x != null) {
            parent = x;
            int cmp = node.key.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp == 0) {
                x.setValue(node.getValue());
                return;
            } else {
                x = x.left;
            }
        }
        node.parent = parent;

        if (parent != null) {
            int cmp = node.key.compareTo(parent.key);
            if (cmp > 0) {
                parent.right = node;
            } else {
                parent.left = node;
            }
        } else { // 第一次插入parent是null
            this.root = node;
        }

        // 需要调用修复红黑树平衡方法
        // insertFixup();
    }


    /**
     * 红黑树平衡修复方法
     *     |---情景1：红黑树为空树，将根节点染色为黑色
     *     |---情景2：插入节点的key已经存在，不需要处理
     *     |---情景3：插入节点的父节点为黑色，黑色节点没有变化，所以红黑树依然平衡，不需要处理
     *
     *     |---情景4：需要自己处理
     *         |---情景4.1：叔叔节点存在，且为红色（父-叔 双红），将爸爸节点和叔叔节点染色为黑色，爷爷节点染色为红色，并且再以爷爷节点为当前节点 进行下一轮处理
     *         |---情景4.2：叔叔节点不存在，或者为黑色，父节点为爷爷节点的左子树
     *             |---情景4.2.1：插入节点为其父节点的左子节点（LL情况），将爸爸染色为黑色，爷爷节点染色为红色，然后以爷爷节点右旋
     *             |---情景4.2.2：插入节点为其父节点的右子节点（LR情况），
     *                           以爸爸节点进行一次左旋，得到LL双红的情景（4.2.1），然后指定爸爸节点为当前节点进行下一轮处理
     *         |---情景4.3：叔叔节点不存在，或者为黑色，父节点为爷爷节点的右子树
     *             |---情景4.3.1：插入节点为其父节点的右子节点（RR情况），将爸爸染色为黑色，爷爷节点染色为红色，然后以爷爷节点左旋
     *             |---情景4.3.2：插入节点为其父节点的右子节点（LR情况），
     *                           以爸爸节点进行一次右旋，得到RR双红的情景（4.3.1），然后指定爸爸节点为当前节点进行下一轮处理
     *
     *
     * @param node
     */
    public void insertFixup(RBNode node) {




    }


    /**
     * 创建BRNode
     *
     * @param <K>
     * @param <V>
     */
    static class RBNode<K extends Comparable<K>, V> {
        private RBNode left;
        private RBNode right;
        private RBNode parent;
        private K key;
        private V value;
        private boolean color;

        public RBNode() {
        }

        public RBNode(RBNode left, RBNode right, RBNode parent, K key, V value, boolean color) {
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.key = key;
            this.value = value;
            this.color = color;
        }

        public RBNode getLeft() {
            return left;
        }

        public void setLeft(RBNode left) {
            this.left = left;
        }

        public RBNode getRight() {
            return right;
        }

        public void setRight(RBNode right) {
            this.right = right;
        }

        public RBNode getParent() {
            return parent;
        }

        public void setParent(RBNode parent) {
            this.parent = parent;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }
    }


}
