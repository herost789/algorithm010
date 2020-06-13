package com.mypractice;

import java.util.Arrays;

public class RotateArray {
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    public static void reverseArray(int[] array, int beginIndex, int endIndex) {
        while (beginIndex < endIndex) {
            int temp = array[beginIndex];
            array[beginIndex] = array[endIndex];
            array[endIndex] = temp;
            beginIndex++;
            endIndex--;
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        rotate(a,3);

        System.out.println(Arrays.toString(a));
        System.out.println(3%1);
    }
}
