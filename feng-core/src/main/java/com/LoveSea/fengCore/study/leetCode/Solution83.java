package com.LoveSea.fengCore.study.leetCode;

/**
 * @author xiahaifeng
 */

public class Solution83 {

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

    public ListNode deleteDuplicates(ListNode head) {
        if( null ==head|| null == head.next) {
            return head;
        }
        ListNode curNode = head;
        while (curNode.next != null) {
            int cur = curNode.val;
            if(curNode.next.val == cur) {
                curNode.next = curNode.next.next;
            }else {
                curNode = curNode.next;
            }
        }
        return head;
    }
}