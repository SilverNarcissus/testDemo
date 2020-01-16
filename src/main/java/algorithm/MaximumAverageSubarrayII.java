package algorithm;

/**
 * Created by SilverNarcissus on 2019/1/17.
 */

/*
Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:

Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
    when length is 5, maximum average value is 10.8,
    when length is 6, maximum average value is 9.16667.
    Thus return 12.75.

Note:

1 <= k <= n <= 10,000.
Elements of the given array will be in range [-10,000, 10,000].
The answer with the calculation error less than 10-5 will be accepted.

 */
public class MaximumAverageSubarrayII {
    public static void main(String[] args) {
        int[] input = {1,12,-5,-6,50,3};
        MaximumAverageSubarrayII subarrayII = new MaximumAverageSubarrayII();
        System.out.println(subarrayII.calculate(input, 4));
    }

    public double calculate(int[] nums, int k){
        int n = nums.length;
        double left = min(nums);
        double right = max(nums);

        while(right - left >= 1e-5){
            double mid = (left + right) / 2;
            double sum = 0;
            double preSum = 0;
            double minSum = 0;
            boolean canSum = false;
            for (int i = 0; i < n; i++) {
                sum += nums[i] - mid;
                if(i >= k){
                    preSum += nums[i - k] - mid;
                    minSum = Math.min(minSum, preSum);
                }

                if(i >= k && sum - minSum >= 0){
                    canSum = true;
                    break;
                }
            }

            if(canSum){
                left = mid;
            }
            else {
                right = mid;
            }

        }

        return left;
    }

    private int min(int[] nums){
        int min = nums[0];
        for(int i : nums){
            min = Math.min(i, min);
        }

        return min;
    }

    private int max(int[] nums){
        int max = nums[0];
        for(int i : nums){
            max = Math.max(i, max);
        }

        return max;
    }
}
