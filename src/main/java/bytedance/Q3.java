package bytedance;

import java.util.HashSet;
import java.util.Scanner;

public class Q3 {


  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    HashSet<Integer> feb = buildFeb();

    int n = in.nextInt();
    int res = 0;
    for (int i = 0; i < n; i++) {
      if (!feb.contains(in.nextInt())) {
        res++;
      }
    }

    System.out.println(res);
  }

  private static HashSet<Integer> buildFeb() {
    int a = 2;
    int b = 3;
    HashSet<Integer> res = new HashSet<>();
    res.add(a);
    res.add(b);

    while (true) {
      int next = a + b;
      if (next < 0) {
        return res;
      }
      res.add(next);
      a = b;
      b = next;
    }
  }
}
