package bytedance;

import java.util.Arrays;
import java.util.Scanner;

public class Q6 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    for (int i = 0; i < n; i++) {
      int len = scanner.nextInt();
      int m = scanner.nextInt();
      int[] array = new int[len];
      for (int j = 0; j < len; j++) {
        array[j] = scanner.nextInt();
      }
      System.out.println(handle(array, m));
    }
  }

  private static long handle(int[] array, int m) {
    long sum = 0;

    long[] dp = new long[m];
    Arrays.fill(dp, -1);
    dp[0] = 0;
    for (int i : array) {
      sum += i;
      long[] temp = new long[m];
      System.arraycopy(dp, 0, temp, 0, m);
      for (int j = 0; j < m; j++) {
        if(dp[j] != -1){
          int loc = (j + i) % m;
          temp[loc] = Math.max(temp[loc], dp[j] + i);
        }
      }

      dp = temp;
      // System.out.println(Arrays.toString(dp));
    }

    return sum - dp[0];
  }
}
