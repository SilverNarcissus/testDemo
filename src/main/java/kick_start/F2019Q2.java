package kick_start;

import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/10/19.
 */
public class F2019Q2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numTest = scanner.nextInt();
    for (int i = 0; i < numTest; i++) {
      int n = scanner.nextInt();
      long m = scanner.nextLong();
      long[] array = new long[n];
      for (int j = 0; j < n; j++) {
        array[j] = scanner.nextLong();
      }

      System.out.println("Case #" + (i + 1) + ": " + solve(array, m));
    }
  }

  public static long solve(long[] array, long m) {
    int n = array.length;
    long res = 0;
    long[] minValAfter = minVal(array);
    // System.out.println(Arrays.toString(minValAfter));

    for (int i = 55; i >= 0; i--) {
      int count0 = 0;
      long mask = 1L << i;
      for(long a : array){
        if((a & mask) == 0){
          count0++;
        }
      }

      long cur_res = mask * count0;
      if (cur_res >= 0 && cur_res <= m - minValAfter[i]){
        res += mask;
        m -= cur_res;
      }
      else{
        // System.out.println("we put 0 at " + i);
        m -= mask * (n - count0);
        if(m < 0){
          return -1;
        }
      }
    }

    return res;
  }

  private static long[] minVal(long[] array){
    long[] res = new long[64];

    int n = array.length;
    for (int i = 0; i < 63; i++) {
      int count0 = 0;
      long mask = 1L << i;
      for(long a : array){
        if((a & mask) == 0){
          count0++;
        }
      }

      if(res[i] == Long.MAX_VALUE || res[i] + mask * Math.min(count0, n - count0) < 0){
        res[i + 1] = Long.MAX_VALUE;
      }
      else{
        res[i + 1] = res[i] + mask * Math.min(count0, n - count0);
      }
    }

    return res;
  }
}
