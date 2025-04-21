package com.lovehai.leetcode._0701_0800;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * 日程可以用一对整数 startTime 和 endTime 表示，这里的时间是半开区间，即 [startTime, endTime),
 * 实数 x 的范围为，  startTime <= x < endTime 。
 * 实现 MyCalendar 类：
 * MyCalendar() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致重复预订，
 * 返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 * @author xiahaifeng
 */

public class Solution729 {
    public static class MyCalendar{
        List<WorkTime> workTimes;
        public MyCalendar() {
            workTimes = new ArrayList<>();
        }

        public boolean book(int startTime, int endTime) {
            WorkTime workTime = new WorkTime(startTime, endTime);
            int index = Collections.binarySearch(workTimes, workTime);
            if(index < 0){
                index = -index - 1;
            }
            // 判断是否有重复预订
            // 先看一下前一个时间段是否有重复预订
            if(index > 0 && workTimes.get(index - 1).endTime > startTime){
                return false;
            }
            // 再看一下后一个时间段是否有重复预订
            if(index < workTimes.size() && workTimes.get(index).startTime < endTime){
                return false;
            }
            workTimes.add(index, workTime);
            return true;
        }
    }

    public static class WorkTime implements Comparable<WorkTime>{
        int startTime;
        int endTime;

        public WorkTime(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(WorkTime o) {
            return this.startTime - o.startTime;
        }
    }
}