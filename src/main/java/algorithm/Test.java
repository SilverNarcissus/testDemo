package algorithm;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 2019/3/6.
 */
public class Test {
    int count = 0;
    public static void main(String[] args) {
        Test test = new Test();
        ArrayList<Integer> l = new ArrayList<>();
        l.add(0);
        //l.add(2);
        //l.add(1);
        l.add(3);
        l.add(4);
        l.add(5);
        l.add(6);
        //System.out.println(test.calculateDistance(l, 3));
        //System.out.println(test.maxChange(l, 2));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append('a');
        }

        long cur = System.nanoTime();
        System.out.println(test.maxContinuesChar(sb.toString(), 8));
        System.out.println("time:" + (System.nanoTime() - cur) / 1000 / 1000);
        System.out.println(test.count);
    }

    private int maxContinuesChar(String s, int m) {
        ArrayList<Integer>[] loc = new ArrayList[26];
        for (int i = 0, len = loc.length; i < len; i++) {
            loc[i] = new ArrayList<>();
        }

        int result = 1;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            int pos = cur - 'a';
            loc[pos].add(i);

            result = Math.max(result, maxChange(loc[pos], m));
            //System.out.println(loc[pos]);
        }

        return result;
    }

    private int maxChange(ArrayList<Integer> pos, int m) {
        if(pos.size() == 1){
            return 1;
        }

        int l = 2;
        int r = pos.size();

        while(l <= r){
            int mid = l + ((r - l) >> 1);
            int needChange= calculateDistance(pos, mid);
            count++;

            if(needChange <= m){
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

        return l - 1;
    }

    private int calculateDistance(ArrayList<Integer> pos, int len){
        int midLoc = pos.size() - 1 - (len >> 1);
        int mid = pos.get(midLoc);
        int counter = 1;
        int result = 0;
        for(int i = pos.size() - 1; i > midLoc; i--){
            result += pos.get(i) - mid;
            result -= counter;
            counter++;
        }

        counter = 1;
        for(int i = midLoc - 1; i >= pos.size() - len; i--){
            result += mid - pos.get(i);
            result -= counter;
            counter++;
        }

        return result;
    }
}
