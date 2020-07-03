package com.myhomework;

public class LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill: bills) {
            //当订单为5，无需找零，并且将5的零钱数量++
            if (bill == 5)
                five++;
            else if (bill == 10) {
                //当订单为10，如果没有5的零钱，则无法找零返回false
                if (five == 0) return false;
                //如果可以找零，那么将5块钱找零后，5的零钱--，10的零钱++
                five--;
                ten++;
            } else {
                //使用1个5元和1个10元找零20
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    //使用三张5元找零20
                    five -= 3;
                } else {
                    //其他情况无法找零，返回false
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] bills=new int[]{20,5,5,10};
        LemonadeChange lemonadeChange=new LemonadeChange();
        boolean result=lemonadeChange.lemonadeChange(bills);
        System.out.println(result);
    }
}
