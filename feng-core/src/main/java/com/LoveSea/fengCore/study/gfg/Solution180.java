package com.LoveSea.fengCore.study.gfg;

/**
 * @author xiahaifeng
 */

public class Solution180 {
    // ================== one ===================
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int val) {
            data = val;
            left = right = null;
        }
    }
    /**
     * differenceSum : 求两个二叉树的对应节点的差值的绝对值的和
     */
    public int differenceSum(Node root1, Node root2) {
        calculateDifference(root1, root2);
        return sum;
    }
    int sum = 0;
    public void calculateDifference(Node root1, Node root2) {
        if (root1 == null && root2 == null) {
            return;
        }
        if (root1 == null) {
            sum += Math.abs(root2.data);
            calculateDifference(null, root2.left);
            calculateDifference(null, root2.right);
        } else if (root2 == null) {
            sum += Math.abs(root1.data);
            calculateDifference(root1.left, null);
            calculateDifference(root1.right, null);
        } else {
            sum += Math.abs(root1.data - root2.data);
            calculateDifference(root1.left, root2.left);
            calculateDifference(root1.right, root2.right);
        }
    }

    // ================== two ===================

    public static long bestNode(int N, int[] A, int[] P) {
        // code here
        return 0;
    }
}