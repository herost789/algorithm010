package com.myhomework;

import java.util.Arrays;

public class ReverseBits {
    public static int reverseBits(int n) {
        int result = 0;
        //int 为32位，循环32次
        for (int i = 0; i < 32; i++) {
            result = (result << 1) + (n & 1);
            n >>= 1;

        }
        return result;
    }

    //通过这个题，我学会了位运算的使用，在群里提出的问题，将一个byte转成各个二进制位
    //使用boolean来表示true表示1，false表示0
    public static boolean[] changeByteToBit(int b) {
        boolean[] results = new boolean[8];
        int result=0;
        for (int i = 0; i < 8; i++) {
            result = b&1;
            if (result == 1) results[i] = true;
            System.out.println(result);
            b >>= 1;
        }
        return results;
    }


    public static void main(String[] args) {
        int n = 125;
        boolean[] res = changeByteToBit(n);
        System.out.println(Arrays.toString(res));
    }
}
