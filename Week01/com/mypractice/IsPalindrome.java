package com.mypractice;

public class IsPalindrome {
    public static boolean isPalindrome1(int x) {

        String num=String.valueOf(x);
        String reverseNum=new StringBuilder(num).reverse().toString();

        return num.equals(reverseNum);
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNum=0;
        while (x >= revertedNum) {

            revertedNum=revertedNum*10+x%10;
            x=x/10;


        }

        return revertedNum==x||revertedNum/10==x;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome1(1232));

        System.out.println(isPalindrome2(1221));
    }
}
