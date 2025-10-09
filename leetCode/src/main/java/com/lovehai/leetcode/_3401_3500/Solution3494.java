package com.lovehai.leetcode._3401_3500;

import java.util.Arrays;

/**
 *
 * @author xiahaifeng
 */

public class Solution3494 {
    public long minTime(int[] skill, int[] mana) {
        if (skill.length == 1) {
            return (long) skill[0] * (long) Arrays.stream(mana).sum();
        }
        // 前一个人完成了，下一个人手上必须不能有任务
        // 前一个人完成了，下一个人还没有完成
        long[] finishedTime = new long[skill.length];
        // 没开始前所有人的结束时间都是0
        long sum = 0;
        for (int i = 0; i < skill.length; i++) {
            finishedTime[i] = sum += (long) mana[0] * skill[i];
        }
        for (int j = 1; j < mana.length; j++) {
            sum = 0;
            for (int i = 0; i < skill.length - 1; i++) {
                int needTime = skill[i] * mana[j];
                sum += needTime;
                long nextFinishTime = Math.max(finishedTime[i] + needTime, sum);
                finishedTime[i] = Math.max(nextFinishTime, finishedTime[i + 1]);
                sum = finishedTime[i];
            }
            long needTime = (long) skill[skill.length - 1] * mana[j];
            sum += needTime;
            long nextFinishTime = Math.max(finishedTime[skill.length - 2] + needTime, sum);
            finishedTime[skill.length - 1] = nextFinishTime;
            sum = finishedTime[skill.length - 1];
            for (int i = skill.length - 1; i >= 0; i--) {
                finishedTime[i] = sum;
                sum -= (long) skill[i] * mana[j];
            }
        }
        return finishedTime[skill.length - 1];
    }
}