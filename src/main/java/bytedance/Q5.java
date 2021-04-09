package bytedance;

import java.util.HashMap;
import java.util.Scanner;

public class Q5 {

  static HashMap<Integer, Integer> map = new HashMap<>();

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    for (int i = 0; i < n; i++) {
      System.out.println(handle(scanner.nextInt()));
    }
  }

  private static int handle(int nextInt) {
    if (nextInt <= 2) {
      return nextInt;
    }

    if(map.containsKey(nextInt)){
      return map.get(nextInt);
    }

    int res =  Math.min(nextInt % 2 + 1 + handle(nextInt >> 1), nextInt % 3 + 1 + handle(nextInt / 3));
    map.put(nextInt, res);

    return res;
  }
}
