package algorithm;

import java.util.Arrays;
import java.util.HashMap;

public class HappyGrid {

  int[][] key;
  int[][] cost;
  int[][] map;

  public static void main(String[] args) {
    HappyGrid happyGrid = new HappyGrid();
    System.out.println(happyGrid.getMaxGridHappiness(2, 3, 1, 2));

    HashMap<Integer, Integer> map = new HashMap<>();
    map.computeIfAbsent(0, id -> {
      System.out.println("123");
      return 1;
    });

    map.computeIfAbsent(0, id -> {
      System.out.println("123");
      return 1;
    });
  }


  public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
    key = build(n);
    cost = new int[3][3];
    cost[1][1] = -60;
    cost[1][2] = -10;
    cost[2][2] = 40;
    cost[2][1] = -10;
    map = calculate(key, n, cost);

    int[][][][] dp = new int[m][1 << n << n][introvertsCount + 1][extrovertsCount + 1];
    for(int i = 0; i < m; i++){
      for (int j = 0; j < (1 << n << n); j++) {
        for (int k = 0; k < introvertsCount + 1; k++) {
          Arrays.fill(dp[i][j][k], Integer.MIN_VALUE);
        }
      }
    }

    return handle(dp, 0, 0,
        introvertsCount, extrovertsCount, m);
  }


  private int handle(int[][][][] dp, int row, int mask, int inCount, int exCount, int m) {
    if (row >= m) {
      return 0;
    }

    if (dp[row][mask][inCount][exCount] == Integer.MIN_VALUE) {
      int res = Integer.MIN_VALUE;
      for (int i = 0; i < key.length; i++) {
        if (key[i][1] > inCount || key[i][2] > exCount) {
          continue;
        }

        int next = key[i][0];
        res = Math.max(res, map[mask][next] + handle(dp, row + 1, next, inCount - key[i][1], exCount - key[i][2], m));
      }

      dp[row][mask][inCount][exCount] = res;
    }

    return dp[row][mask][inCount][exCount];
  }

  private int[][] calculate(int[][] key, int n, int[][] cost) {
    int[][] res = new int[1 << n << n][1 << n << n];

    for (int i = 0; i < key.length; i++) {
      for (int j = i; j < key.length; j++) {
        int row1 = key[i][0];
        int row2 = key[j][0];

        int tempCost = 0;
        int sum1 = 0;
        int sum2 = 0;
        int before1 = 0;
        int before2 = 0;
        for (int k = 0; k < n; k++) {
          int cur1 = row1 & 3;
          int cur2 = row2 & 3;
          if (cur1 == 1) {
            sum1 += 120;
          } else if (cur1 == 2) {
            sum1 += 40;
          }

          if (cur2 == 1) {
            sum2 += 120;
          } else if (cur2 == 2) {
            sum2 += 40;
          }

          sum1 += cost[cur1][before1];
          sum2 += cost[cur2][before2];

          tempCost += cost[cur1][cur2];

          row1 = row1 >> 2;
          row2 = row2 >> 2;
          before1 = cur1;
          before2 = cur2;
        }

        res[key[i][0]][key[j][0]] = sum2 + tempCost;
        res[key[j][0]][key[i][0]] = sum1 + tempCost;
      }
    }

    return res;
  }

  private int[][] build(int n) {
    int count = (int) Math.pow(3, n);
    int[][] res = new int[count][3];
    int loc = 0;

    for (int i = 0; i < (1 << n << n); i++) {
      int[] cur = getCount(i);
      if (cur != null) {
        res[loc][0] = i;
        res[loc][1] = cur[0];
        res[loc][2] = cur[1];
        loc++;
      }
    }

    return res;
  }

  public int[] getCount(int i) {
    int[] res = new int[2];
    while (i > 0) {
      int cur = i & 3;
      if (cur == 3) {
        return null;
      }

      if (cur == 2) {
        res[1]++;
      } else if (cur == 1) {
        res[0]++;
      }

      i = i >> 2;
    }

    return res;
  }
}
