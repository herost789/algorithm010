package com.mypractice;

import java.util.*;

public class RemoveDuplicates {
    public static int removeDuplicates1(int[] nums) {
        if(nums.length==0) return 0;
        //使用set存储数组元素保证唯一
        Set<Integer> numSet=new TreeSet<Integer>();
        //遍历数组，如果元素不set中，则放入set
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }
        //然后将set中的元素放入数组的前n个位置
        Iterator<Integer> iterator=numSet.iterator();
        int i=0;
        while (iterator.hasNext()) {
            nums[i]=iterator.next();
            i++;
        }
        //分析时间复杂度为元素个数N加上非重复元素数M即O(n+m)
    return numSet.size();

    }

    public static int removeDuplicates2(int[] nums) {
        //交换是数组中既节省空间有节省时间的算法
        if(nums.length==0) return 0;
        int i=0;
        for (int j = 1; j < nums.length; j++) {
            //使用两个下标，i标记唯一元素，j遍历数组
            if (nums[i] != nums[j]) {
                //要先加再交换，否则会把0号元素移除
                i++;
                nums[i]=nums[j];
            }

        }
        return i+1;
    }
    public static void main(String[] args) {
        int[] a=new int[] {1,2,3,0,1,2,4};
        System.out.println(removeDuplicates1(a));
        System.out.println(Arrays.toString(a));
        System.out.println(removeDuplicates2(a));
        System.out.println(Arrays.toString(a));


    }
}
