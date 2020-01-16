package algorithm;

/**
 * Created by SilverNarcissus on 2017/10/21.
 */
public class MergeSort implements Sort{
    int count;

    public static void main(String[] args) {
        MergeSort q = new MergeSort();
        int[] test = {4,2,1,1};
        q.sort(test);
        System.out.println(q.count);
    }

    private int[] mergePart(int[] nums, int start, int end) {
        if (start == end) {
            int[] result = new int[1];
            result[0] = nums[start];
            return result;
        }

        int mid = (start + end) >> 1;
        int[] first = mergePart(nums, start, mid);
        int[] second = mergePart(nums, mid + 1, end);

        int[] result = new int[end - start + 1];
        int i = 0;
        int j = 0;
        int loc = 0;
        while (i != first.length || j != second.length) {
            if (i == first.length) {
                result[loc++] = second[j++];
            } else if (j == second.length) {
                result[loc++] = first[i++];
                count += j;
            } else if (first[i] <= second[j]) {
                result[loc++] = first[i++];
                count += j;
            } else {
                result[loc++] = second[j++];
            }
        }

        return result;
    }

    @Override
    public void sort(int[] nums) {
        count = 0;
        System.arraycopy(mergePart(nums, 0, nums.length - 1), 0, nums, 0, nums.length);
    }


}
