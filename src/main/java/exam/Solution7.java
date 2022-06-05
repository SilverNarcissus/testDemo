package exam;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution7 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[][] input = new int[n][2];
    for (int i = 0; i < n; i++) {
      input[i][0] = in.nextInt();
      input[i][1] = in.nextInt();
    }

    System.out.println(handle(input));
  }

  public static int handle(int[][] input){
    Arrays.sort(input, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return Integer.compare(o2[0], o1[0]);
      }
    });

    int cur = input[0][0] - 1;
    int loc = 0;
    PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
    int res = 0;
    for (int i = cur; i >= 0; i--) {
      while(loc < input.length && input[loc][0] > i){
        queue.add(input[loc][1]);
        loc++;
      }

      res += queue.isEmpty() ? 0 : queue.poll();
      //System.out.println(res);
    }

    return res;
  }
}