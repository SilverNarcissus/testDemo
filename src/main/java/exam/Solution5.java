package exam;

import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/11/17.
 */
public class Solution5 {
  static long[] result;
  static long[] mask;
  static HashMap<Long, Integer> memo;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    result = new long[6];
    mask = new long[6];
    for (int i = 2; i < 8; i++) {
      long base = (1 << i) - 1;
      long res = 0;
      for (int j = 0; j < i; j++) {
        res = (res << 8) | base;
      }
      result[i - 2] = res;
      mask[i - 2] = base;
    }

    for (int i = 0; i < t; i++) {
      int n = sc.nextInt();
      memo = new HashMap<>();

      long[] map = new long[n];
      for (int j = 0; j < n; j++) {
        long row = 0;
        String line = sc.next();
        //System.out.println(line);
        for (int k = 0; k < n; k++) {
          if(line.charAt(k) == '#'){
            row |= (1 << k);
          }
        }
        map[j] = row;
      }
      System.out.print("Case #" + (i + 1) + ": ");
      System.out.println(judge(map, n));
    }

  }

  private static int judge(long[] map, int n) {
    long key = toLong(map, n);
    //System.out.println("to long " + key);
    //print(map, n);

    if(key == result[n - 2]){
      //System.out.println("find!!!!");
      return 0;
    }

    if(memo.containsKey(key)){
      return memo.get(key);
    }

    memo.put(key, Integer.MAX_VALUE);
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < (n << 1) - 1; i++) {
      int count = i;
      long[] next = new long[n];
      System.arraycopy(map, 0, next, 0, n);

      boolean flag = false;
      for (int j = n - 1; j >= 0; j--) {
        if (count < 0) {
          break;
        }
        if(count >= n){
          count--;
          continue;
        }
        if((next[j] & (1 << count)) == 0){
          flag = true;
        }
        next[j] ^= (1 << count);
        count--;
      }

      if(flag){
        //System.out.println("my!! " + i + " " + toLong(next, n));
        min = Math.min(min, judge(next, n));
      }
    }

    for (int i = 0; i < (n << 1) - 1; i++) {
      int count = i;
      long[] next = new long[n];
      System.arraycopy(map, 0, next, 0, n);

      boolean flag = false;
      for (int j = 0; j < n; j++) {
        if (count < 0) {
          break;
        }
        if(count >= n){
          count--;
          continue;
        }

        if((next[j] & (1 << count)) == 0){
          flag = true;
        }

        next[j] ^= (1 << count);
        count--;
      }

      if(flag){
        min = Math.min(min, judge(next, n));
      }
    }

    int res = min == Integer.MAX_VALUE ? Integer.MAX_VALUE : min + 1;
    memo.put(key, res);
    return res;
  }

  private static long toLong(long[] array, int n){
    long m = mask[n - 2];
    long res = 0;
    for (int j = 0; j < n; j++) {
      res = (res << 8) + (array[j] & m);
    }


    return res;
  }

  private static void print(long[] array, int n){
    System.out.println();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if((array[i] & (1 << j)) > 0){
          System.out.print('#');
        }
        else{
          System.out.print('.');
        }
      }
      System.out.println();
    }
  }
}
