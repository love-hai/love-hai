package com.lovehai.leetcode._0701_0800;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 实现一个程序来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生 三重预订。
 * 事件能够用一对整数 startTime 和 endTime 表示，在一个半开区间的时间 [startTime, endTime) 上预定。
 * 实数 x 的范围为  startTime <= x < endTime。
 * 实现 MyCalendarTwo 类：
 * MyCalendarTwo() 初始化日历对象。
 * boolean book(int startTime, int endTime) 如果可以将日程安排成功添加到日历中而不会导致三重预订，
 * 返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 *
 * @author xiahaifeng
 */

public class Solution731 {
    public static class MyCalendarTwo {
        private final List<TimeInstance> timeInstances;
        private final byte oneDegree = 1;
        private final byte twoDegree = 2;

        public MyCalendarTwo() {
            timeInstances = new ArrayList<>();
        }

        public boolean book(int startTime, int endTime) {
            TimeInstance timeInstance = new TimeInstance(startTime, endTime, oneDegree);
            int index = Collections.binarySearch(timeInstances, timeInstance);
            if (index < 0) {
                index = -index - 1;
            }
            // 查看前一个时间段是否有三重预订
            List<TimeInstance> addTimeInstances = new ArrayList<>();
            List<Integer> removeIndex = new ArrayList<>();
            index = Math.max(0, index - 1);
            while (true) {
                if (index >= timeInstances.size()) {
                    if (startTime < endTime) {
                        addTimeInstances.add(new TimeInstance(startTime, endTime, oneDegree));
                    }
                    break;
                }
                TimeInstance nextTimeInstance = timeInstances.get(index);
                TimeInstance nextCopyTimeInstance = new TimeInstance(nextTimeInstance.startTime, nextTimeInstance.endTime, nextTimeInstance.degree);
                if (nextCopyTimeInstance.endTime <= startTime) {
                    index++;
                    continue;
                }
                if (nextCopyTimeInstance.startTime >= endTime) {
                    if (startTime < endTime) {
                        addTimeInstances.add(new TimeInstance(startTime, endTime, oneDegree));
                    }
                    break;
                }
                if (nextCopyTimeInstance.degree == twoDegree) {
                    return false;
                }
                if (startTime < nextCopyTimeInstance.startTime) {
                    addTimeInstances.add(new TimeInstance(startTime, nextCopyTimeInstance.startTime, oneDegree));
                    startTime = nextCopyTimeInstance.startTime;
                }
                if (nextCopyTimeInstance.startTime < startTime) {
                    addTimeInstances.add(new TimeInstance(nextCopyTimeInstance.startTime, startTime, nextCopyTimeInstance.degree));
                    nextCopyTimeInstance.startTime = startTime;
                }
                if (endTime < nextCopyTimeInstance.endTime) {
                    addTimeInstances.add(new TimeInstance(endTime, nextCopyTimeInstance.endTime, nextCopyTimeInstance.degree));
                    nextCopyTimeInstance.degree = twoDegree;
                    nextCopyTimeInstance.endTime = endTime;
                    if (!nextCopyTimeInstance.equals(nextTimeInstance)) {
                        removeIndex.add(index);
                        addTimeInstances.add(nextCopyTimeInstance);
                    }
                    break;
                } else if (endTime == nextCopyTimeInstance.endTime) {
                    nextCopyTimeInstance.degree = twoDegree;
                    if (!nextCopyTimeInstance.equals(nextTimeInstance)) {
                        removeIndex.add(index);
                        addTimeInstances.add(nextCopyTimeInstance);
                    }
                    break;
                } else {
                    startTime = nextCopyTimeInstance.endTime;
                    nextCopyTimeInstance.degree = twoDegree;
                }
                if (!nextCopyTimeInstance.equals(nextTimeInstance)) {
                    removeIndex.add(index);
                    addTimeInstances.add(nextCopyTimeInstance);
                }
                index++;
            }
            for (int i = removeIndex.size() - 1; i >= 0; i--) {
                timeInstances.remove((int) removeIndex.get(i));
            }
            this.addAddTimeInstances(addTimeInstances);
            return true;
        }

        private void addAddTimeInstances(List<TimeInstance> addTimeInstances) {
            for (TimeInstance addTimeInstance : addTimeInstances) {
                if(addTimeInstance.startTime == addTimeInstance.endTime){
                    continue;
                }
                int index = Collections.binarySearch(timeInstances, addTimeInstance);
                if (index < 0) {
                    index = -index - 1;
                }
                timeInstances.add(index, addTimeInstance);
            }
        }
    }

    public static class TimeInstance implements Comparable<TimeInstance> {
        private int startTime;
        private int endTime;
        private byte degree;

        public TimeInstance(int startTime, int endTime, byte degree) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.degree = degree;
        }

        @Override
        public int compareTo(TimeInstance o) {
            return this.startTime - o.startTime;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj instanceof TimeInstance timeInstance) {
                return this.startTime == timeInstance.startTime && this.endTime == timeInstance.endTime && this.degree == timeInstance.degree;
            }
            return false;
        }
    }

    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        System.out.println(myCalendarTwo.book(10, 20));
        System.out.println(myCalendarTwo.book(50, 60));
        System.out.println(myCalendarTwo.book(10, 40));
        System.out.println(myCalendarTwo.book(5, 15));
        System.out.println(myCalendarTwo.book(5, 10));
        System.out.println(myCalendarTwo.book(25, 55));
    }
}