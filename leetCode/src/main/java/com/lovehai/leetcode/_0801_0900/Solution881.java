package com.lovehai.leetcode._0801_0900;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiahaifeng
 */

/**
 * 给定数组 people 。people[i]表示第 i 个人的体重 ，船的数量不限，每艘船可以承载的最大重量为 limit。
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为 limit。
 * 返回 承载所有人所需的最小船数 。
 */
public class Solution881 {

    public int numRescueBoats(int[] people, int limit) {
        if (0 == people.length) {
            return 0;
        }
        heapSort(people);
        int left = 0;
        int right = people.length - 1;
        int num = 0;
        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            num++;
        }
        return num;
    }

    // 快速排序
    public void heapSort(int[] arr) {
        int n = arr.length;

        // 构建最大堆
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 一个一个从堆中取出元素
        for (int i = n - 1; i > 0; i--) {
            // 移动当前根到末尾
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // 调整最大堆
            heapify(arr, i, 0);
        }
    }

    // 调整堆
    private void heapify(int[] arr, int n, int i) {
        int largest = i; // 初始化最大值为根
        int left = 2 * i + 1; // 左子节点
        int right = 2 * i + 2; // 右子节点

        // 如果左子节点比根大
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // 如果右子节点比根大
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // 如果最大值不是根
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // 递归调整堆
            heapify(arr, n, largest);
        }
    }

    public int numRescueBoats1(int[] people, int limit) {
        if (null == people || 0 == people.length) {
            return 0;
        }
        if (0 >= limit) {
            return 0;
        }
        List<Integer> list = new ArrayList<>();
        for (int person : people) {
            list.add(person);
        }
        list.sort((o1, o2) -> o2 - o1);
        int i = 0;
        // 用于记录剩余的重量
        int remainder = limit;
        // 用于记录船的数量
        int num = 1;
        while (!list.isEmpty()) {
            if (i >= list.size()) {
                i = 0;
                remainder = limit;
                num++;
            }
            if (0 == remainder || remainder < list.get(list.size() - 1)) {
                i = 0;
                remainder = limit;
                num++;
            }
            if (remainder >= list.get(i)) {
                remainder -= list.get(i);
                list.remove(i);
                continue;
            }
            if (remainder < list.get(i)) {
                i++;
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Solution881 solution881 = new Solution881();
        int[] people = {3, 2, 2, 1};
        int limit = 3;
        System.out.println(solution881.numRescueBoats(people, limit));
    }

}