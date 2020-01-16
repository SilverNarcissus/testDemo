package kick_start;

import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/11/22.
 */
public class Solution {
  private static int n;
  private static int m;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    n = scanner.nextInt();
    m = scanner.nextInt();
    if(n < 2 || m < 2){
      System.out.println(-1);
      return;
    }

    int[][] map = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        map[i][j] = scanner.nextInt();
      }
    }
    int[][][] memo = new int[n][m][m];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        for (int k = 0; k < m; k++) {
          memo[i][j][k] = -1;
        }
      }
    }

    System.out.println(dfs(memo,map, 0, m - 1, n - 1));
  }

  private static int dfs(int[][][] memo, int[][] map, int x, int y, int level){
    if(level < 0){
      return Integer.MIN_VALUE;
    }

    if(x < 0 || x >= m || y < 0 || y >= m){
      return Integer.MIN_VALUE;
    }

    if(level == 0){
      if(x == 0 && y == m - 1){
        return map[0][x] + map[0][y];
      }
    }

    if(memo[level][x][y] != -1){
      return memo[level][x][y];
    }

    int max = Integer.MIN_VALUE;
    max = Math.max(dfs(memo, map, x - 1, y, level - 1), max);
    max = Math.max(dfs(memo, map, x - 1, y - 1, level - 1), max);
    max = Math.max(dfs(memo, map, x - 1, y + 1, level - 1), max);

    max = Math.max(dfs(memo, map, x + 1, y, level - 1), max);
    max = Math.max(dfs(memo, map, x + 1, y - 1, level - 1), max);
    max = Math.max(dfs(memo, map, x + 1, y + 1, level - 1), max);

    max = Math.max(dfs(memo, map, x, y - 1, level - 1), max);
    max = Math.max(dfs(memo, map, x, y + 1, level - 1), max);
    max = Math.max(dfs(memo, map, x, y, level - 1), max);

    if(max == Integer.MIN_VALUE){
      memo[level][x][y] = max;
    }
    else{
      if(x != y){
        memo[level][x][y] = max + map[level][x] + map[level][y];
      }
      else{
        memo[level][x][y] = max + map[level][x];
      }
    }

    return memo[level][x][y];
  }
}
