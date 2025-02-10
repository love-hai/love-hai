package com.LoveSea.fengCore.study.leetCode;

/**
 * 134. 加油站
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost 如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回-1 。如果存在解，则保证它是唯一 的。
 *
 * @author xiahaifeng
 */

public class Solution134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 最低可能的油量，可能为负，表示出发前借的油
        int minBalance = 0;
        // 达到最低油量的加油站索引，在此加油站出发
        // 油量不会低于最低值，所以此加油站即为题目所求
        int minIndex = 0;
        int n = gas.length;
        int balance = 0;
        for (int i = 0; i < gas.length; i++) {
            // 计算后的balance为到达下一加油站 i + 1的油量，如果创了新低则记录之
            balance = balance + gas[i] - cost[i];
            if (balance < minBalance) {
                minBalance = balance;
                minIndex = i + 1;
            }

        }
        // 从第一个加油站出发，如果油量恢复到出发前以上，代表可能跑完一圈
        // 在中间出现负值也没有关系，可以想象出发前向别人借了一些油，最后
        // 能还上就可以了
        return balance >= 0 ? minIndex : -1;
    }
    /*
    虽然不能直接证明中间不存在一段路走不通，但是可以使用反证法来证明。gas[i] - cost[i] = 等于加油量或者耗油量。 1 ---k---n ,假设k 是存量最低的位置，存量为-x,最后通过的话 [k+1,n]之间的和肯定要>=x,而且从k+1开始，油肯定是够的，就是累加的过程中会一直大于0，如果小于0，存量最低的就不会是K（你的疑问应该是这，我也搞不明白这，最后够不代表中间够啊）。 1---k 是否会出现油量不够的情况，1 ---k 任何一个点的存油量>=-x，[k+1,n]的存油量肯定能补上。
     */
}