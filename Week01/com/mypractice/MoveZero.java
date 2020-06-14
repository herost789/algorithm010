package com.mypractice;

import java.util.Arrays;

public class MoveZero {
    public static void moveZeroes(int[] nums)  {

        if (nums == null ||  nums.length == 0) {
            return;
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            //当前元素!=0，就把其交换到左边，等于0的交换到右边
            if (nums[i] != 0) {
                if (i > j) {// #1它避免了数组开头是非零元素的交换也就是阻止（i==j）时交换
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
    public static void main(String[] args) {
        int[] test={0,1,0,3,12};
        System.out.println("Before move");
        System.out.println(Arrays.toString(test));
        moveZeroes(test);
        System.out.println("After move");
        Arrays.toString(test);
        System.out.println(Arrays.toString(test));
    }
}
