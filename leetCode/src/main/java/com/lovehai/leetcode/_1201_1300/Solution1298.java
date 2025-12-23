package com.lovehai.leetcode._1201_1300;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xiahaifeng
 */

public class Solution1298 {

    private Map<Integer, TreasureChest> map = new HashMap<>();
    private int ans = 0;
    private int[] status;
    private int[] candies;
    private int[][] keys;
    private int[][] containedBoxes;

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {

    }

    private void openBox(int boxId) {
        if(!map.containsKey(boxId)){
            return;
        }

       if(status[boxId] == 1){
           ans += candies[boxId];
       }
        TreasureChest chest = map.get(boxId);
        ans += chest.candies;
        for (int key : chest.keys) {
            if (map.containsKey(key)) {
                map.get(key).hasKey = true;
                openBox(key);
            }
        }
        for (int box : chest.containedBoxes) {
            openBox(box);
        }
        map.remove(boxId);
    }


    public static class TreasureChest {
        boolean hasKey;
        int candies;
    }
}