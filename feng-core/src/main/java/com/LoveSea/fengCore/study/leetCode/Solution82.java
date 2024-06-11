package com.LoveSea.fengCore.study.leetCode;

import java.util.List;

/**
 * @author xiahaifeng
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表
 */

public class Solution82 {
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
        ListNode newHead = new ListNode(-1000);
        newHead.next = head;

        ListNode preNode = newHead;
        ListNode curNode = head;
        ListNode dupPreNode = newHead;
        boolean isDup = false;
        while (null != curNode) {
            if(preNode.val == curNode.val) {
                curNode = curNode.next;
                isDup = true;
            }else {
                if(isDup) {
                    dupPreNode.next = curNode;
                }else {
                    dupPreNode = preNode;
                }
                preNode = curNode;
                curNode = curNode.next;
                isDup = false;
            }
        }
        if(isDup) {
            dupPreNode.next = null;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        Solution82 solution82 = new Solution82();
        int [] nums = {1,2,3,3,4,4,5};
        ListNode head = new ListNode(nums[0]);
        ListNode curNode = head;
        for (int i = 1; i < nums.length; i++) {
            curNode.next = new ListNode(nums[i]);
            curNode = curNode.next;
        }
        ListNode result = solution82.deleteDuplicates(head);
    }
}