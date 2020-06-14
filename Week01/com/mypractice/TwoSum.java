package com.mypractice;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == target - nums[i]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> arrayMap = new HashMap<>();
        for (int i : nums) {
            arrayMap.put(nums[i], i);
        }
        for (int i : nums) {
            int anotherSum = target - nums[i];
            if (arrayMap.containsKey(anotherSum) && arrayMap.get(anotherSum) != i) {
                int j = arrayMap.get(anotherSum);
                return new int[]{i,j};
            }
        }
        return new int[]{-1,-1};
    }
}
