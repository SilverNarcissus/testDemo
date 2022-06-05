package meituan;

import java.util.Arrays;
import java.util.Scanner;

public class Solution1 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    for (int i = 0; i < n; i++) {
      int m = in.nextInt();
      int[] array = new int[m];
      for (int j = 0; j < m; j++) {
        array[j] = in.nextInt();
      }
      if(check(array)){
        System.out.println("Yes");
      }
      else{
        System.out.println("No");
      }

    }
  }

  private static boolean check(int[] array){
    Arrays.sort(array);
    for (int i = 0; i < array.length; i++) {
      if(array[i] != i + 1){
        return false;
      }
    }
    return true;
  }

}
