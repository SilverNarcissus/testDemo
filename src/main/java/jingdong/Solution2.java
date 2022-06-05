package jingdong;

import java.util.Scanner;

public class Solution2 {

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
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < array.length; i++) {
      if(array[i][1] == 1){
        min = Math.min(min, array[i][0]);
        max = Math.max(max, array[i][0]);
      }
    }

    int count = 0;
    for (int i = 0; i < array.length; i++) {
      if(array[i][1] == 1){
        count++;
      }
      else{
        count += (array[i][0] > min && array[i][0] < max) ? 1 : 0;
      }
    }

    return count;
  }
}
