package com.myhomework;

public class HammingWeight {
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            //按位掩码，如果为1则1的数量++
            if ((n & mask) != 0) {
                bits++;
            }
            //掩码左移，从低向高按位与
            mask <<= 1;
        }
        return bits;
    }
}
