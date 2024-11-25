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

    /**
     * 给定一个以节点 1 为根的树。该树以数组 P 的形式给出，其中 Pi 表示节点 i 的父节点，另外 P1 =-1，
     * 因为 1 是根节点。每个节点 i 都有一个与之关联的值 A:。
     * 首先，您必须选择任意一个节点作为起点，之后您可以从一个节点转到其任何子节点。您必须继续移动到子节点，
     * 直到到达叶节点。每次到达新节点时，您都会写入其值。假设您路径中的整数序列为 B。
     * 让我们定义一个函数 f，定义如下：
     * f(B)=B1- B2+B3- B4+ B... + (-1)(k-1)Bk。
     * 您必须找到 f(B) 的最大可能值。
     */
    public static long bestNode(int N, int[] A, int[] P) {
        // code here
        return 0;
    }
}