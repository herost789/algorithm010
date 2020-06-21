package com.mypractice;

import java.util.Arrays;

public class Anagram {
    public boolean isAnagram1(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }
        //使用暴力解法，将得到的两个字符串进行排序后比较是否相同
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        //使用数组缓存字母元素，在s中出现时给元素+1，在t中出现时给元素-1
        //最后检查数组元素是否均为0
        int[] counter = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counter[s.toLowerCase().charAt(i)-'a']++;
            counter[t.toLowerCase().charAt(i)-'a']--;
        }
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String s="Aa";
        System.out.println((s.charAt(0)-'a'));
        System.out.println((s.charAt(1)-'a'));

    }

}
