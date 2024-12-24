package com.LoveSea.fengCore.study.leetCode;

import java.util.*;

/**
 * 吃苹果的最大数目
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i]
 * 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。
 * 也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 *
 * @author xiahaifeng
 */

public class Solution1705 {
    public int eatenApples(int[] apples, int[] days) {
        TreeSet<AppleInfo> appleInfos = new TreeSet<>();
        int result = 0;
        int day = -1;
        outer:
        while (true){
            day++;
            if(day<apples.length){
                int appleNum = apples[day];
                if(0 != appleNum ){
                    int expireDay = days[day] + day;
                    AppleInfo appleInfo = new AppleInfo(appleNum, expireDay);
                    appleInfos.add(appleInfo);
                }
            }
            while (true){
                if(appleInfos.isEmpty()){
                    if(day >= apples.length){
                        break outer;
                    }
                    break;
                }
                AppleInfo appleInfo = appleInfos.first();
                if(appleInfo.expireDay <= day){
                    appleInfos.pollFirst();
                }else{
                    appleInfo.appleNum--;
                    result++;
                    if(0 == appleInfo.appleNum){
                        appleInfos.pollFirst();
                    }
                    break;
                }
            }
        }
        return result;
    }
    public static class AppleInfo implements Comparable<AppleInfo>{
        private int appleNum;
        private final int expireDay;

        public AppleInfo(int appleNum, int expireDay) {
            this.appleNum = appleNum;
            this.expireDay = expireDay;
        }
        @Override
        public int compareTo(AppleInfo o) {
            if(this.expireDay == o.expireDay){
                return 1;
            }
            return this.expireDay - o.expireDay;
        }
    }
}