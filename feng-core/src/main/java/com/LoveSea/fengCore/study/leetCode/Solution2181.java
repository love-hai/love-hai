package com.LoveSea.fengCore.study.leetCode;

/**
 * 2181. 合并零之间的节点
 * 给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。链表的 开端 和 末尾 的节点都满足 Node.val == 0 。
 * 对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。
 * 然后将所有 0 移除，修改后的链表不应该含有任何 0 。
 * 返回修改后链表的头节点 head 。
 *
 * @author xiahaifeng
 */

public class Solution2181 {
    public ListNode mergeNodes(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode traverse = head;
        while (traverse != null) {
            if (traverse.val == 0) {
                if (cur.val != 0) {
                    if (null != pre) {
                        pre.next = cur;
                    }
                    pre = cur;
                    cur = traverse;
                }
            } else {
                cur.val += traverse.val;
            }
            traverse = traverse.next;
        }
        if (pre != null) {
            pre.next = null;
        }
        return head;
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