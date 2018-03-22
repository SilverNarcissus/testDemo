package algorithm;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.Stack;

/**
 * Created by SilverNarcissus on 2017/10/19.
 */
public class QuickSort implements Sort{
    public static long count = 0;
    public static void main(String[] args) {
        Sort sort = new MergeSort();

        Random random;
        for (int i = 0; i < 100; i++) {
            random = new Random(Calendar.getInstance().getTimeInMillis());
            int[] nums1 = new int[100000];
            int[] nums2 = new int[100000];
            for (int ind = 0; ind < nums1.length; ind++) {
                final int r = random.nextInt(10000000);
                nums1[i] = r;
                nums2[i] = r;
            }
            long time = Calendar.getInstance().getTimeInMillis();
            Arrays.sort(nums1);
            System.out.println(Calendar.getInstance().getTimeInMillis() - time);

            time = Calendar.getInstance().getTimeInMillis();
            sort.sort(nums2);
            System.out.println(Calendar.getInstance().getTimeInMillis() - time);


            for (int ind = 0; ind < nums1.length; ind++) {
                if (nums1[i] != nums2[i]) {
                    System.err.println("Wrong!");
                }
            }

            System.out.println("__________");
        }
    }

    public void sort(int[] nums) {
        shuffle(nums);
        int lo = 0;
        int hi = nums.length - 1;
        Stack<Integer> next = new Stack<>();
        next.push(lo);
        next.push(hi);
        while (!next.isEmpty()) {
            hi = next.pop();
            lo = next.pop();
            partion(nums, lo, hi, next);
        }
        //System.out.println(count);
    }

    private void partion(int[] nums, int lo, int hi, Stack<Integer> next) {
        if (lo >= hi) {
            return;
        }
        int i = lo;
        int j = hi;
        boolean isForward = true;
        boolean isChanged = false;
        while (true) {
            while (i < j && nums[j] >= nums[i]) {
                count ++;
                if (isForward) {
                    j--;
                } else {
                    i++;
                }
            }
            if (i < j) {
                isChanged = true;
                exchange(nums, i, j);
                isForward = !isForward;
            } else {
                break;
            }
        }

        if(isChanged) {
            next.push(lo);
            next.push(j - 1);
            next.push(j + 1);
            next.push(hi);
        }
    }

    private void exchange(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    private void shuffle(int a[]) {
        final Random random = new Random();
        for (int ind = 1; ind < a.length; ind++) {
            final int r = random.nextInt(ind + 1);
            exchange(a, ind, r);
        }
    }
}
