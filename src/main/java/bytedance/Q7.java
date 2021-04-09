package bytedance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Q7 {
  static int min = Integer.MAX_VALUE;

  public static void main(String[] args) {
//    List<Integer> l = new ArrayList<>();
//    l.add(1);
//    l.add(2);
//    System.out.println(binSort(l, 50000));
//    System.out.println(binSort2(l, 50000));

    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    List<Integer>[] list = new List[n];

    for (int i = 0; i < n; i++) {
      int m = scanner.nextInt();
      List<Integer> cur = new ArrayList<>();
      for (int j = 0; j < m; j++) {
        cur.add(scanner.nextInt());
      }
      Collections.sort(cur);
      list[i] = cur;
    }

    int q = scanner.nextInt();

    // System.out.println(Arrays.toString(list));

    for (int i = 0; i < q; i++) {
      int len = scanner.nextInt();
      int[] index = new int[len];
      for (int j = 0; j < len; j++) {
        index[j] = scanner.nextInt() - 1;
      }
      int k = scanner.nextInt();

      System.out.println(handle(list, index, k));
    }
  }

  private static int handle(List<Integer>[] list, int[] index, int k) {
//    System.out.println(Arrays.toString(index));
//    System.out.println(k);
//    System.out.println("------");
    int l = 1;
    int r = 1_000_000_000;

    while(l <= r){
      min = Integer.MAX_VALUE;
      int mid = l + ((r - l) >> 1);
      int sum1 = 0;
      int sum2 = 0;
      for (int i : index) {
        sum1 += binSort(list[i], mid);
        sum2 += binSort2(list[i], mid);
      }

      // System.out.println("!!!" + sum1 + " " + sum2 + " " + mid);
      // sum1 >= sum2
      if(sum2 < k && sum1 >= k){
        return mid;
      }

      if(sum2 >= k){
        r = mid - 1;
      }
      else{
        l = mid + 1;
      }
    }

    return -1;
  }

  // se
  private static int binSort(List<Integer> list, int target){
    int l = 0;
    int r = list.size() - 1;

    while(l <= r){
      int mid = l + ((r - l) >> 1);
      int cur = list.get(mid);

      if(cur <= target){
        l = mid + 1;
      }
      else{
        r = mid - 1;
      }
    }

    return l;
  }

  // s
  private static int binSort2(List<Integer> list, int target){
    int l = 0;
    int r = list.size() - 1;

    while(l <= r){
      int mid = l + ((r - l) >> 1);
      int cur = list.get(mid);

      if(cur < target){
        l = mid + 1;
      }
      else{
        r = mid - 1;
      }
    }

    return r + 1;
  }

}
