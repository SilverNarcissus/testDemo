package exam;

import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/11/17.
 */
public class Solution6 {


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int t = sc.nextInt();

    for(int i = 0; i < t; i++){
      int n = sc.nextInt();
      int[] array = new int[n];
      for (int j = 0; j < 9; j++) {
        array[j] = sc.nextInt();
      }
      System.out.print("Case #" + (i + 1) + ":");
      judge(array);
      System.out.println();
    }

  }

  private static void judge(int[] array){
    int h = 0;

  }
}
