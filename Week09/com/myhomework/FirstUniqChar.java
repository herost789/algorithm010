package com.myhomework;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FirstUniqChar {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> count = new HashMap<Character, Integer>();
        int n = s.length();
        // 构建所有字符的hashmap,key为字符，value为次数
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        //找出次数为1的最小索引，就是所求解
        for (int i = 0; i < n; i++) {
            if (count.get(s.charAt(i)) == 1)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        FirstUniqChar firstUniqChar=new FirstUniqChar();
        int result=firstUniqChar.firstUniqChar("loveleetcode");
        System.out.println(result);
    }


}
