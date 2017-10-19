import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;

/**
 * Created by SilverNarcissus on 2017/10/19.
 */
public class QuickSort {
    public static void main(String[] args) {
        QuickSort q = new QuickSort();
        Random random;
        for (int i = 0; i < 99; i++) {
            random = new Random(Calendar.getInstance().getTimeInMillis());
            int[] nums1 = new int[1000];
            int[] nums2 = new int[1000];
            for (int ind = 0; ind < nums1.length; ind++) {
                final int r = random.nextInt();
                nums1[i] = r;
                nums2[i] = r;
            }
            Arrays.sort(nums1);

            q.quickSort(nums2);

            for (int ind = 0; ind < nums1.length; ind++) {
                if(nums1[i] != nums2[i]){
                    System.err.println("Wrong!");
                }
            }
        }
    }

    public void quickSort(int[] nums) {
        shuffle(nums);
        int lo = 0;
        int hi = nums.length - 1;

        partion(nums, lo ,hi);
    }

    private void partion(int[] nums, int lo, int hi) {
        int i = lo;
        int j = hi;
        boolean isForward = true;
        while (true) {
            while (i > j && nums[j] <= nums[i]) {
                if (isForward) {
                    j--;
                } else {
                    i++;
                }
            }
            if (i < j) {
                exchange(nums, i, j);
                isForward = !isForward;
            } else {
                break;
            }
        }

        partion(nums, lo, j - 1);
        partion(nums, j + 1, hi);
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
