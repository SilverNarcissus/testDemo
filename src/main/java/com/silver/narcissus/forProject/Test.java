package com.silver.narcissus.forProject;

/**
 * Created by SilverNarcissus on 2017/3/7.
 */
public class Test {
    public Test() {
        super();
    }

    public int getHigher(int i) {
        if (i < 10) {
            return 10;
        }
        String temp = String.valueOf(i + i / 8);
        int base = getBase(temp);
        int upLevel = (int) Math.pow(10, base);
        String after = temp.substring(temp.length() - base);


        if (Integer.parseInt(after) > upLevel / 2) {
            return i + i / 8 + upLevel - Integer.parseInt(after);
        }

        return i + i / 8 - Integer.parseInt(after);
    }

    private int getBase(String number) {
        if (number.length() == 2) {
            return 1;
        }
        return number.length() - 2;
    }
}
