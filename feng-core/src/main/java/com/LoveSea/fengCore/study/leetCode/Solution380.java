package com.LoveSea.fengCore.study.leetCode;

import java.util.*;

/**
 * 380. O(1) 时间插入、删除和获取随机元素
 * 实现RandomizedSet 类：
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * @author xiahaifeng
 */

public class Solution380 {
    class RandomizedSet {
        private final   List<Integer> list;
        private final Map<Integer, Integer> map;

        public RandomizedSet() {
            list = new ArrayList<>();
            map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if(!map.containsKey(val)){
                return false;
            }
            int deleteIndex = map.get(val);
            if(deleteIndex != list.size() - 1){
                list.set(deleteIndex, list.get(list.size() - 1));
                map.put(list.get(deleteIndex), deleteIndex);
                deleteIndex = list.size() - 1;
            }
            list.remove(deleteIndex);
            map.remove(val);
            return true;
        }
        private final Random random = new Random();

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

}