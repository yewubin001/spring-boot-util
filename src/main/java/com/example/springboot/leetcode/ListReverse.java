package com.example.springboot.leetcode;

import java.util.List;

/**
 * @Author yewubin
 * 链表反转
 * Date 2021-04-19 22:39
 */
public class ListReverse {

    static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        ListNode listNode5 = new ListNode(5, null);
        ListNode listNode4 = new ListNode(4, listNode5);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);

//        ListNode iterate = iterate(listNode1);
        ListNode recursion = recursion(listNode1);

        while(recursion!=null){
            System.out.print(recursion.value + "\t");
            recursion = recursion.next;
        }
    }

    /**
     * 循环
     *
     * @param head
     * @return
     */
    public static ListNode iterate(ListNode head) {
        ListNode prev = null, next;
        ListNode curr = head;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public static ListNode recursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = recursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

}
