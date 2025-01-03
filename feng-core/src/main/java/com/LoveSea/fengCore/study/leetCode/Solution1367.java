package com.LoveSea.fengCore.study.leetCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1367 二叉树中的链表
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head
 * 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 *
 * @author xiahaifeng
 */

public class Solution1367 {
    private boolean flag = false;
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        flag |= dfs(head, root);
        if (flag) {
            return true;
        }
        flag = isSubPath(head, root.left);
        if (flag) {
            return true;
        }
        flag = isSubPath(head, root.right);
        return flag;
    }

    boolean dfs(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        return head.val == root.val && (dfs(head.next, root.left) || dfs(head.next, root.right));
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

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}