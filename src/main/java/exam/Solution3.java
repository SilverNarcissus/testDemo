package exam;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/4/10.
 */
public class Solution3 {
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            Solution3.main2();
        }
    }

    public static void main2() {
        Scanner sc = new Scanner(System.in);
        int[][] map = Solution3.produce();
        int n = map.length;
        int m = map[0].length;
        System.out.println(n + " " + m);

        Random r = new Random();
        int xs = r.nextInt(n);
        int ys = r.nextInt(m);
        int xd = r.nextInt(n);
        int yd = r.nextInt(m);


        int[][] dp = new int[n][m];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        System.out.println(Solution3.getMethod(map, dp, xs, ys, xd, yd, -1));
    }

    private static int[][] produce() {
        Random r = new Random();
        int n = r.nextInt(600) + 1;
        int m = r.nextInt(600) + 1;
        int[][] result = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result[i][j] = i * j + j;
            }
        }



        return result;
    }

    private static int getMethod(int[][] map, int[][] dp, int x, int y, int xd, int yd, int before) {
        if (x < 0 || x >= map.length || y < 0 || y >= map[0].length || map[x][y] <= before) {
            return 0;
        }

        if (x == xd && y == yd) {
            return 1;
        }

        if (dp[x][y] == -1) {
            int result = 0;
            int height = map[x][y];
            result += getMethod(map, dp, x + 1, y, xd, yd, height);
            result %= MOD;
            result += getMethod(map, dp, x - 1, y, xd, yd, height);
            result %= MOD;
            result += getMethod(map, dp, x, y + 1, xd, yd, height);
            result %= MOD;
            result += getMethod(map, dp, x, y - 1, xd, yd, height);
            result %= MOD;

            dp[x][y] = result;
        }

        return dp[x][y];
    }

}
