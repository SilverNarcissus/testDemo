package algorithm;


import java.util.*;

/**
 * Created by SilverNarcissus on 2018/3/6.
 */
public class BucketSort implements Sort{
    
    @Override
    public void sort(int[] nums) {
        List<Integer> input = new ArrayList<>(nums.length);
        for (int i : nums) {
            input.add(i);
        }

        List<Integer> sort = bucketSort(input, 100000000);

        for(int i = 0; i < sort.size(); i++) {
            nums[i] = sort.get(i);
        }

    }

    private List<Integer> bucketSort(List<Integer> origin, int radix) {
        int beforeRadix = radix * 100;
        List<Integer>[] result = new ArrayList[100];
        for (int i : origin) {
            int next = i;
            if (radix != 100000000) {
                next = (i - i / beforeRadix * beforeRadix);
            }
            next = next / radix;
            if (result[next] == null) {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                result[next] = l;
            } else {
                result[next].add(i);
            }
        }


        List<Integer> array = new ArrayList<>(origin.size());
        if (radix != 1) {
            for (int i = 0; i < 100; i++) {
                if (result[i] != null) {
                    array.addAll(bucketSort(result[i], radix / 100));
                }
            }
        } else {
            for (int i = 0; i < 100; i++) {
                if (result[i] != null) {
                    array.addAll(result[i]);
                }
            }
        }

        return array;
    }
}
