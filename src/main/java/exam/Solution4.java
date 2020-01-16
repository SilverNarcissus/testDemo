package exam;

import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/11/17.
 */
public class Solution4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    for(int i = 0; i < t; i++){
      int n = sc.nextInt();
      int[] array = new int[n];
      for (int j = 0; j < n; j++) {
        array[j] = sc.nextInt();
      }
      System.out.print("Case #" + (i + 1) + ":");
      hIndex(array);
      System.out.println();
    }

  }

  private static void hIndex(int[] array){
    int h = 0;
    for (int i = 0; i < array.length; i++) {
      int next = h + 1;
      int count = 0;
      for (int j = 0; j <= i; j++) {
        count += array[j] >= next ? 1 : 0;
      }

      if(count >= next){
        h = next;
      }
      System.out.print(" " + h);
    }
  }

}
