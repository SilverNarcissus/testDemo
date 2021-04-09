package algorithm;

import java.util.*;

public class MinDiff {

  public static void main(String[] args) {
    MinDiff minDiff = new MinDiff();
    int[] nums = {1,2,1,4};
    int k = 2;

    System.out.println(minDiff.minimumIncompatibility(nums, k));
  }

  int groupSize;
  List<Integer> group = new ArrayList<>();
  List<Integer> score = new ArrayList<>();
  int[] nums;

  public int minimumIncompatibility(int[] nums, int k) {
    if(nums.length == k){
      return 0;
    }

    this.nums = nums;

    groupSize = nums.length / k;

    generateMask(groupSize, 0, nums.length, 0);

    int mask = (1 << (nums.length + 1)) - 1;

    int[][] dp = new int[mask + 1][k + 1];
    for(int i = 0; i < mask + 1; i++){
      Arrays.fill(dp[i], -2);
    }

    for(int i = 0; i < group.size(); i++){
      System.out.println(Integer.toBinaryString(group.get(i)));
      System.out.println(score.get(i));
    }
    System.out.println();

    return dp(mask, k, dp);
  }

  private void generateMask(int remain, int mask, int size, int start){
    if(remain == 0){
      int index = 0;
      int[] array = new int[groupSize];
      for(int i = 0; i < nums.length; i++){
        if((mask & (1 << i)) > 0){
          array[index++] = nums[i];
        }
      }

      int res = judge(array);

      if(res != -1){
        group.add(mask);
        score.add(res);
      }

      return;
    }

    for(int i = start; i <= size - remain; i++){
      generateMask(remain - 1, mask | (1 << i), size, i + 1);
    }
  }

  private int dp(int mask, int remain, int[][] dp){
    if(remain == 0){
      return 0;
    }

    if(dp[mask][remain] == -2){
      int res = -1;
      for(int i = 0; i < group.size(); i++){
        int candidate = group.get(i);
        int cScore = score.get(i);

        if((mask & candidate) == candidate){
          int next = dp(mask & (~ candidate), remain - 1, dp);
          if(next != -1){
            if(res == -1){
              res = cScore + next;
            }
            else{
              res = Math.min(res, cScore + next);
            }
          }
        }
      }

      dp[mask][remain] = res;
    }

    return dp[mask][remain];
  }


  private int judge(int[] array){
    System.out.println(Arrays.toString(array));

    Arrays.sort(array);
    for(int i = 0; i < array.length - 1; i++){
      if(array[i] == array[i + 1]){
        return -1;
      }
    }

    return array[array.length - 1] - array[0];
  }
}
