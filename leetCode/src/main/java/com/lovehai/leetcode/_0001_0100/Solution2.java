package com.lovehai.leetcode._0001_0100;

/**
 *
 * @author xiahaifeng
 */

public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode();
        addNode(l1, l2, pre,0);
        return pre.next;
    }

    private void addNode(ListNode l1, ListNode l2, ListNode pre,int remaining ) {
        if (l1 == null && l2 == null && remaining == 0) {
            return;
        }
        int a = 0;
        if (l1 != null) {
            a = l1.val;
            l1 = l1.next;
        }
        int b = 0;
        if (l2 != null) {
            b = l2.val;
            l2 = l2.next;
        }
        int sum = (a + b) + remaining;
        remaining = sum / 10;
        int val = sum % 10;
        pre.next = new ListNode(val);
        addNode(l1, l2, pre.next, remaining);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}