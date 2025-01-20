package com.LoveSea.fengCore.study.leetCode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 3066. 超过阈值的最少操作数 II
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * 一次操作中，你将执行：
 * 选择 nums 中最小的两个整数 x 和 y 。
 * 将 x 和 y 从 nums 中删除。
 * 将 min(x, y) * 2 + max(x, y) 添加到数组中的任意位置。
 * 注意，只有当 nums 至少包含两个元素时，你才可以执行以上操作。
 * 你需要使数组中的所有元素都大于或等于 k，请你返回需要的最少操作次数。
 *
 * @author xiahaifeng
 */

public class Solution3066 {
    public int minOperations1(int[] nums, int k) {
        Arrays.sort(nums);
        int fusionIndex = -1;
        int fusionMaxIndex = -1;
        int curIndex = 0;
        int curMaxIndex = nums.length - 1;
        int result = 0;
        if (nums[0] >= k) {
            return 0;
        } else {
            result++;
            nums[++fusionIndex] = 2 * nums[curIndex++] + nums[curIndex++];
            fusionMaxIndex++;
        }
        // fusionIndex = 0;index = 2;
        int first;
        int second;
        while (true) {
            // 输入保证答案一定存在，也就是说一定存在一个操作序列使数组中所有元素都大于等于 k 。
             /* if (fusionLength - 1 - fusionIndex + nums.length - 1 - index < 2) {
                return -1;
            }*/
            if (nums[fusionIndex] >= k && (curIndex > curMaxIndex || nums[curIndex] >= k)) {
                break;
            }
            result++;
            if (nums[curIndex] < nums[fusionIndex]) {
                first = nums[curIndex];
                curIndex++;
            } else {
                first = nums[fusionIndex];
                fusionIndex++;
            }
            if (curIndex > curMaxIndex) {
                second = nums[fusionIndex];
                fusionIndex++;
            } else if (fusionIndex > fusionMaxIndex) {
                second = nums[curIndex];
                curIndex++;
            } else {
                if (nums[curIndex] < nums[fusionIndex]) {
                    second = nums[curIndex];
                    curIndex++;
                } else {
                    second = nums[fusionIndex];
                    fusionIndex++;
                }
            }
            if (curIndex > curMaxIndex) {
                curIndex = fusionIndex;
                curMaxIndex = fusionMaxIndex;
                fusionIndex = 0;
                fusionMaxIndex = 0;
                nums[0] = 2 * first + second;
                if (nums[0] < 0) {
                    nums[0] = k;
                }
            } else {
                nums[++fusionMaxIndex] = 2 * first + second;
                if (nums[fusionMaxIndex] < 0) {
                    nums[fusionMaxIndex] = k;
                }
            }
        }
        return result;
    }

    public int minOperations(int[] nums, int k) {
        // 创建最小堆
        MinHeap minHeap = new MinHeap(nums, k);
        int operations = 0;
        // 当堆中元素大于等于 2 且最小元素小于 k 时，继续操作
        int x;
        int y;
        while (minHeap.size() > 0 && (x = minHeap.poll()) < k) {
            if (minHeap.size() == 0) {
                return ++operations;
            }
            y = minHeap.poll(); // 取出第二小元素
            // 计算新的元素并加入堆中
            int newElement = 2 * x + y;
            minHeap.add(newElement); // 将新元素插入堆中
            operations++; // 增加操作次数
        }
        // 返回操作次数
        return operations;
    }


    public static class MinHeap {
        int[] heap;
        int start;
        int end;
        int k;

        public MinHeap(int[] nums, int k) {
            this.heap = nums;
            Arrays.sort(nums);
            int kIndex = Arrays.binarySearch(nums, k);
            if (kIndex < 0) {
                kIndex = -kIndex - 1;
            }
            this.start = 0;
            this.end = Math.min(kIndex, nums.length);
            this.k = k;
        }

        public int poll() {
            return heap[start++];
        }

        public int peek() {
            return heap[start];
        }

        public int size() {
            return end - start;
        }

        public void add(int num) {
            if (num >= k || num < 0) {
                return;
            }
            int index = Arrays.binarySearch(heap, start, end, num);
            if (index < 0) {
                index = -index - 1;
            }
            index = Math.min(index, end);
            System.arraycopy(heap, start, heap, start-1, index - start);
            heap[index] = num;
            start--;
        }
    }

    public static void main(String[] args) {
        Solution3066 solution3066 = new Solution3066();
        int[] nums = {1, 1, 2, 4, 9};
        int k = 20;
        System.out.println(solution3066.minOperations(nums, k));
    }
}