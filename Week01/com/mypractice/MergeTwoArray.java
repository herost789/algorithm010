package com.mypractice;

import java.util.Arrays;

public class MergeTwoArray {

    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        //将nums2数组从0号位置拷贝n个元素拷贝到nums1的最后，从nums1的m号位置开始拷贝
        System.arraycopy(nums2, 0, nums1, m, n);
        //将两个数组拼接，拼接后，直接排序完成合并操作
        Arrays.sort(nums1);
    }

    public void merge2(int[] nums1, int m, int[] nums2, int n) {

    }
}
