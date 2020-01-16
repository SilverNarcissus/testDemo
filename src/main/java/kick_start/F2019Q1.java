package kick_start;

import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/10/19.
 */
public class F2019Q1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numTest = scanner.nextInt();
    for (int i = 0; i < numTest; i++) {
      int n = scanner.nextInt();
      int m = scanner.nextInt();
      int q = scanner.nextInt();
      int[] torn = new int[m];
      int[] read = new int[q];
      for (int j = 0; j < m; j++) {
        torn[j] = scanner.nextInt();
      }

      for (int j = 0; j < q; j++) {
        read[j] = scanner.nextInt();
      }

      System.out.println("Case #" + (i + 1) + ": " + solve(n, torn, read));
    }
  }

  public static long solve(int n, int[] torn, int[] read) {
    long count = 0;
    // pre process
    boolean[] hasTorn = new boolean[n + 1];
    int[] predefine = new int[n + 1];
    for (int t : torn) {
      hasTorn[t] = true;
    }
    // n log n
    for (int i = 1; i <= n; i++) {
      for (int j = i; j <= n; j += i) {
        if (hasTorn[j]) {
          predefine[i]++;
        }
      }
    }
    //

    for (int q : read) {
      count += n / q;
    }

    for (int q : read) {
      count -= predefine[q];
    }

    return count;
  }
}
