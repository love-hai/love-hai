package com.lovehai.leetcode._0801_0900;

import java.util.TreeSet;

/**
 * @author xiahaifeng
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。
 * 如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），
 * 代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。
 * 每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上
 */

public class Solution855 {

    public static class ExamRoom {
        private final TreeSet<SeatDistance> seatSet;
        private final int n;

        public ExamRoom(int n) {
            seatSet = new TreeSet<>();
            seatSet.add(new SeatDistance(-1, n, n));
            this.n = n;
        }

        public int seat() {
            SeatDistance maxDistance = seatSet.first();
            int start = maxDistance.start;
            int end = maxDistance.end;
            if (start == -1) {
                seatSet.remove(maxDistance);
                maxDistance.start = 0;
                if (end == n) {
                    maxDistance.distanceDiv2 = n - 2;
                } else {
                    maxDistance.distanceDiv2 = (maxDistance.end - 2) >> 1;
                }
                seatSet.add(maxDistance);
                return 0;
            } else if (end == n) {
                seatSet.remove(maxDistance);
                maxDistance.end = n - 1;
                maxDistance.distanceDiv2 = (maxDistance.end - maxDistance.start - 2) >> 1;
                seatSet.add(maxDistance);
                return n - 1;
            }
            seatSet.remove(maxDistance);
            int seat = start + maxDistance.distanceDiv2 + 1;
            seatSet.add(new SeatDistance(start, seat, (seat - start - 2) >> 1));
            maxDistance.start = seat;
            maxDistance.distanceDiv2 = (end - seat - 2) >> 1;
            seatSet.add(maxDistance);
            return seat;
        }

        public void leave(int p) {
            SeatDistance left = null;
            SeatDistance right = null;
            for (SeatDistance seatDistance : seatSet) {
                if (seatDistance.end == p) {
                    left = seatDistance;
                }
                if (seatDistance.start == p) {
                    right = seatDistance;
                }
                if (left != null && right != null) {
                    break;
                }
            }
            if (null == left && null == right) {
                return;
            }
            if (left == null) {
                seatSet.remove(right);
                right.start = -1;
                right.distanceDiv2 = (right.end - 1);
                seatSet.add(right);
                return;
            }
            if (right == null) {
                seatSet.remove(left);
                left.end = n;
                left.distanceDiv2 = (n - left.start - 2);
                seatSet.add(left);
                return;
            }
            seatSet.remove(left);
            seatSet.remove(right);
            right.start = left.start;
            if (right.start == -1 || right.end == n) {
                right.distanceDiv2 = (right.end - right.start - 2);
            } else {
                right.distanceDiv2 = (right.end - right.start - 2) >> 1;
            }
            seatSet.add(right);
        }
    }

    public static class SeatDistance implements Comparable<SeatDistance> {
        int start;
        int end;
        int distanceDiv2;

        public SeatDistance(int start, int end, int distanceDiv2) {
            this.start = start;
            this.end = end;
            this.distanceDiv2 = distanceDiv2;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (!(object instanceof SeatDistance seatDistance)) {
                return false;
            }
            return this.start == seatDistance.start
                    && this.end == seatDistance.end && this.distanceDiv2 == seatDistance.distanceDiv2;
        }

        @Override
        public int compareTo(SeatDistance o) {
            if (this.distanceDiv2 == o.distanceDiv2) {
                // 如果距离相等，start小的在前面
                return this.start - o.start;
            }
            return o.distanceDiv2 - this.distanceDiv2;
        }
    }

    public static void main(String[] args) {
        String test = "[10],[],[],[],[0],[4],[],[],[],[],[],[],[],[],[],[0],[4],[],[],[7],[],[3],[],[3],[],[9],[],[0],[8],[],[],[0],[8],[],[],[2]";
        String[] split = test.split(",");
        ExamRoom examRoom = null;
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("[]")) {
                System.out.println(examRoom.seat());
                continue;
            }
            int num = Integer.parseInt(split[i].substring(1, split[i].length() - 1));
            if (i == 0) {
                examRoom = new ExamRoom(num);
            } else {
                examRoom.leave(num);
            }
            System.out.print("null");
        }
    }
}