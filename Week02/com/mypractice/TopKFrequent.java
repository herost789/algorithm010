package com.mypractice;

import java.util.*;

public class TopKFrequent {
    public int[] topKFrequent(int[] nums, int k) {

        //将数字作为key，数字出现的次数作为value，放入hashmap中
        HashMap<Integer,Integer> count=new HashMap<>();
        for (int n : nums) {
            count.put(n,count.getOrDefault(n,0)+1);
        }
        //构造大根堆
        PriorityQueue<Integer> bigheap = new PriorityQueue<>(Comparator.comparingInt(count::get));

        //将大根堆的中排前k个的元素保留，其他元素出队
        for (int n : count.keySet()) {
            bigheap.add(n);
            if (bigheap.size() > k) {
                bigheap.poll();
            }
        }

        //将结果集存入目标的数组中
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i]=bigheap.poll();
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequent topKFrequent =new TopKFrequent();
        int[] nums=new int[]{1,2,1,45,6,7,8};
        int k =2;
        int[] result = topKFrequent.topKFrequent(nums, k);
        System.out.println(Arrays.toString(result));
    }

}