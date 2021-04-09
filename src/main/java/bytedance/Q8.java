package bytedance;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Q8 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    long w = scanner.nextLong();

    int[][] pair = new int[n][2];
    for (int i = 0; i < n; i++) {
      pair[i][0] = scanner.nextInt();
      pair[i][1] = scanner.nextInt();
    }

    System.out.println(handle(pair, w));
  }

  private static long handle(int[][] pair, long w) {
    long l = 1;
    long r = w;

    while(l <= r){
      long mid = l + ((r - l) >> 1);

      if(check(pair, mid, w)){
        l = mid + 1;
      }
      else{
         r = mid - 1;
      }
    }

    return l - 1;
  }

  private static boolean check(int[][] pair, long mid, long w) {
    int leftCount = pair.length >> 1;
    int rightCount = leftCount;

    long res = 0;
    List<Integer> l = new ArrayList<>();
    for(int[] p : pair){
      // left
      if(p[1] < mid){
        leftCount--;
        res += p[0];
        if(leftCount < 0){
          return false;
        }
      }
      else if(p[0] > mid){
        rightCount--;
        res += p[0];

        if(rightCount < 0){
          return true;
        }
      }
      else{
        l.add(p[0]);
      }
    }

    Collections.sort(l);
    int loc = 0;
    for (int i = 0; i < leftCount; i++) {
      res += l.get(loc++);
    }

    for (int i = 0; i < rightCount; i++) {
      res += mid;
    }

    res += mid;

    return res <= w;
  }
}
