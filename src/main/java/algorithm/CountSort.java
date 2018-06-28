package algorithm;

/**
 * Created by SilverNarcissus on 2018/4/22.
 */
public class CountSort implements Sort {
    private static final int MAX = 100000;

    @Override
    public void sort(int[] nums) {
        int[] count = new int[MAX];

        for(int i : nums) {
            count[i]++;
        }

        for(int i = 1; i < MAX; i++) {
            count[i] += count[i - 1];
        }

        int[] result = new int[nums.length];
        for(int i = nums.length - 1; i >= 0; i--){
            result[--count[nums[i]]] = nums[i];
        }

        System.arraycopy(result, 0, nums, 0, nums.length);
    }
}
