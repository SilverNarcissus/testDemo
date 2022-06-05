package jingdong;

import java.util.HashMap;
import java.util.Scanner;

public class Solution1 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[][] array = new int[n][2];
    for (int i = 0; i < n; i++) {
      array[i][0] = in.nextInt();
      array[i][1] = in.nextInt();
    }

    System.out.println(handle(array));
  }

  private static long handle(int[][] array) {
    long sum = 0;
    HashMap<Integer, Long> right = new HashMap<>();
    HashMap<Integer, Long> left = new HashMap<>();
    for (int[] loc : array) {
      right.put(loc[0] + loc[1], right.getOrDefault(loc[0] + loc[1], 0L) + 1);
      left.put(loc[0] - loc[1], left.getOrDefault(loc[0] - loc[1], 0L) + 1);
    }

    for (long l : right.values()) {
      sum += ((l * (l - 1)) >> 1);
    }

    for (long l : left.values()) {
      sum += ((l * (l - 1)) >> 1);
    }

    return sum;
  }

}
