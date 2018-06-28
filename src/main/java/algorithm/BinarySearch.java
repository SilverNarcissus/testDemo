package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SilverNarcissus on 2018/6/1.
 */
public class BinarySearch {
    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(0);
        l.add(1);
        l.add(1);
        l.add(1);
        l.add(3);

        System.out.println(lowerBound(l, 2));
    }


    public static int lowerBound(List<Integer> list, int key) {
        int l = 0;
        int r = list.size() - 1;

        while(l <= r) {
            int mid = l + ((r - l) >> 1);
            if(list.get(mid) < key) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }

        return l;
    }

    public static int upperBound(List<Integer> list, int key) {
        int l = 0;
        int r = list.size() - 1;

        while(l <= r) {
            int mid = l + ((r - l) >> 1);
            if(list.get(mid) <= key) {
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }

        return l;
    }
}
