package com.lovehai.leetcode._2001_2100;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author xiahaifeng
 */

public class Solution2054 {
    public int maxTwoEvents(int[][] events) {
        List<Event> eventList = new ArrayList<>();
        for (int[] e : events) {
            eventList.add(new Event(e[0], false, e[2]));
            eventList.add(new Event(e[1], true, e[2]));
        }
        Collections.sort(eventList);
        int ans = 0;
        int bestFirstValue = 0;
        for (Event event : eventList) {
            if (event.isEnd) {
                bestFirstValue = Math.max(bestFirstValue, event.value);
            } else {
                ans = Math.max(ans, bestFirstValue + event.value);
            }
        }
        return ans;
    }

    public static class Event implements Comparable<Event> {
        public Event(int time, boolean isEnd, int value) {
            this.time = time;
            this.isEnd = isEnd;
            this.value = value;
        }

        private final int time;
        private final boolean isEnd;
        private final int value;

        @Override
        public int compareTo(Event o) {
            if (time != o.time) {
                return Integer.compare(time, o.time);
            }
            return Boolean.compare(isEnd, o.isEnd);
        }
    }
}