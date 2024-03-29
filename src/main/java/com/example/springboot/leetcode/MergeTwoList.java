package com.example.springboot.leetcode;

/**
 * @description: 将两个升序链表合并为一个新的升序链表并返回。
 * 新链表是通过拼接给定的两个链表的所有节点组成的。
 * @author: yewubin
 * @date: 2021/4/20 16:58
 * @version: v1.0
 */
public class MergeTwoList {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode node3 = new ListNode(4, null);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);

        ListNode n3 = new ListNode(4, null);
        ListNode n2 = new ListNode(3, n3);
        ListNode n1 = new ListNode(1, n2);

        ListNode listNode = mergerTwoList(node1, n1);

        while (listNode != null) {
            System.out.print(listNode.val + "\t");
            listNode = listNode.next;
        }
    }

    /**
     * 思路是新建一个带头节点的链表，然后根据比较两个节点的值得大小，
     * 把小的那个存到新建的链表中，之后判断是否两个链表中有一个没有遍历完，
     * 把它添加到尾部即可，然后返回头节点的下一个节点即可。
     *
     * @param list1 链表一
     * @param list2 链表二
     * @return 新建的链表
     */
    public static ListNode mergerTwoList(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0, null);
        ListNode result = head;
        while (list1 != null && list2 != null) {
            if (list1.val >= list2.val) {
                head.next = list2;
                head = head.next;
                list2 = list2.next;
            } else {
                head.next = list1;
                head = head.next;
                list1 = list1.next;
            }
        }
        if (list1 == null) {
            head.next = list2;
        }
        if (list2 == null) {
            head.next = list1;
        }

        return result.next;
    }

}
