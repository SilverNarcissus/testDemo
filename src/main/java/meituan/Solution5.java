package meituan;

import java.util.Arrays;
import java.util.Scanner;

public class Solution5 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int p = in.nextInt();

    String map = in.next();
    int[] cost = new int[p];
    for (int i = 0; i < p; i++) {
      cost[i] = in.nextInt();
    }

    System.out.println(handle(map.toCharArray(), cost));
  }

  private static int handle(char[] array, int[] cost){
    int n = array.length;
    int m = cost.length;
    int[] dp = new int[n];
    Arrays.fill(dp, 1_00000000);
    dp[n - 1] = 0;

    for (int i = n - 2; i >= 0; i--) {
      if(array[i] == 'o'){
        int res = Integer.MAX_VALUE;
        for (int j = i + 1; j < n && j - i <= m; j++) {
          res = Math.min(res, cost[j - i - 1] + dp[j]);
        }
        dp[i] = res;
      }
    }

    return dp[0];
  }
}
