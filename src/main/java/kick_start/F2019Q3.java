package kick_start;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class F2019Q3 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    int numTest = scanner.nextInt();
    for (int i = 0; i < numTest; i++) {
      int n = scanner.nextInt();
      int h = scanner.nextInt();
      int[] h1 = new int[n];
      int[] h2 = new int[n];

      for (int j = 0; j < n; j++) {
        h1[j] = scanner.nextInt();
      }

      for (int j = 0; j < n; j++) {
        h2[j] = scanner.nextInt();
      }

      System.out.println("Case #" + (i + 1) + ": " + solve(n, h1, h2, h));
    }
  }

  public static int solve(int n, int[] h1, int[] h2, int h) {
    HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
    dfs(n, 0, h1, h2, 0, 0, map);

    int res = 0;
    for(Map.Entry<Integer, HashMap<Integer, Integer>> entry1 : map.entrySet()){
      int p1 = entry1.getKey();
      for(Map.Entry<Integer, Integer> entry2 : entry1.getValue().entrySet()){
        if(p1 >= h && entry2.getKey() >= h){
          res += entry2.getValue();
        }
      }
    }

    return res;
  }

  private static void putMap(int k1, int k2, HashMap<Integer, HashMap<Integer, Integer>> map) {
    HashMap<Integer, Integer> inner;
    if (map.containsKey(k1)) {
      inner = map.get(k1);
    } else {
      inner = new HashMap<>();
      map.put(k1, inner);
    }

    inner.put(k2, 1 + inner.getOrDefault(k2, 0));
  }

  private static void dfs(int n, int cur, int[] h1, int[] h2, int p1, int p2,HashMap<Integer, HashMap<Integer, Integer>> map ) {
    if (cur == n) {
      putMap(p1, p2, map);
      return;
    }

    dfs(n, cur + 1, h1, h2, p1 + h1[cur], p2, map);
    dfs(n, cur + 1, h1, h2, p1, p2 + h2[cur], map);
    dfs(n, cur + 1, h1, h2, p1 + h1[cur], p2 + h2[cur], map);
  }

}
