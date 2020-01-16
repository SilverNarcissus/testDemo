package exam;

import java.util.LinkedList;
import java.util.Queue;

class Solution {

  int UP = 0;
  int DOWN = 1;
  int LEFT = 2;
  int RIGHT = 3;
  int[] diff_x = {-1, 1, 0, 0};
  int[] diff_y = {0, 0, -1, 1};
  int[] move_x = {1, -1, 0, 0};
  int[] move_y = {0, 0, 1, -1};
  int[][][] memo;

  public static void main(String[] args) {
    Solution solution = new Solution();
    char[][] grid = {{'#','#','#','#','#','#'},{'#','T','.','.','#','#'},{'#','.','#','B','.','#'},{'#','.','.','.','.','#'},{'#','.','.','.','S','#'},{'#','#','#','#','#','#'}};
    System.out.println(solution.minPushBox(grid));
  }

  public int minPushBox(char[][] grid) {
    int n = grid.length;
    int m = grid[0].length;

    memo = new int[n][m][4];
    int s_x = 0;
    int s_y = 0;
    int b_x = 0;
    int b_y = 0;
    int t_x = 0;
    int t_y = 0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (grid[i][j] == 'T') {
          t_x = i;
          t_y = j;
          grid[i][j] = '.';
        }

        if (grid[i][j] == 'B') {
          b_x = i;
          b_y = j;
        }

        if (grid[i][j] == 'S') {
          s_x = i;
          s_y = j;
          grid[i][j] = '.';
        }
      }
    }

    if (b_x == t_x && b_y == t_y) {
      return 0;
    }

    //System.out.println(canGet(grid,s_x, s_y, 1, 4));
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < 4; i++) {
      int step = canGet(grid, s_x, s_y, b_x + diff_x[i], b_y + diff_y[i]);
      //System.out.println(step);
      if (step != -1) {
        grid[b_x][b_y] = '.';
        int next = bfs(grid, b_x + move_x[i], b_y + move_y[i], i, t_x, t_y);
        if (next != -1) {
          min = Math.min(min, 1 + next);
        }
        grid[b_x][b_y] = 'B';
      }
    }

    return min == Integer.MAX_VALUE ? -1 : min;
  }

  private int bfs(char[][] grid, int b_x, int b_y, int direction, int t_x, int t_y) {
    //System.out.println(b_x + " " + b_y + " " + direction);

    if (b_x < 0 || b_y < 0 || b_x >= grid.length || b_y >= grid[0].length || grid[b_x][b_y] != '.') {
      return -1;
    }

    if (b_x == t_x && b_y == t_y) {
      return 0;
    }

    if (memo[b_x][b_y][direction] != 0) {
      return memo[b_x][b_y][direction];
    }

    grid[b_x][b_y] = 'B';

    memo[b_x][b_y][direction] = -1;
    int cur_x = b_x + diff_x[direction];
    int cur_y = b_y + diff_y[direction];
    int min = Integer.MAX_VALUE;
    int step = 0;
    // go
    for (int i = 0; i < 4; i++) {
      step = canGet(grid, cur_x, cur_y, b_x + diff_x[i], b_y + diff_y[i]);
      if (step != -1) {
        grid[b_x][b_y] = '.';
        int next = bfs(grid, b_x + move_x[i], b_y + move_y[i], i, t_x, t_y);
        grid[b_x][b_y] = 'B';

        if (next != -1) {
          min = Math.min(min, 1 + next);
        }
      }
    }

    grid[b_x][b_y] = '.';
    int cur = min == Integer.MAX_VALUE ? -1 : min;
    memo[b_x][b_y][direction] = cur;
    return cur;
  }

  private int canGet(char[][] grid, int x, int y, int t_x, int t_y) {
    if (x == t_x && y == t_y) {
      return 0;
    }
    Queue<Integer> x_q = new LinkedList<>();
    Queue<Integer> y_q = new LinkedList<>();

    x_q.add(x);
    y_q.add(y);

    boolean[][] record = new boolean[grid.length][grid[0].length];
    int step = 0;
    while (!x_q.isEmpty()) {

      int size = x_q.size();
      for (int i = 0; i < size; i++) {
        int cur_x = x_q.poll();
        int cur_y = y_q.poll();

        if (cur_x < 0 || cur_x >= grid.length || cur_y < 0 || cur_y >= grid[0].length
            || grid[cur_x][cur_y] != '.' || record[cur_x][cur_y]) {
          continue;
        }

        if (cur_x == t_x && cur_y == t_y) {
          return step;
        }

        record[cur_x][cur_y] = true;
        x_q.add(cur_x + 1);
        y_q.add(cur_y);

        x_q.add(cur_x - 1);
        y_q.add(cur_y);

        x_q.add(cur_x);
        y_q.add(cur_y + 1);

        x_q.add(cur_x);
        y_q.add(cur_y - 1);
      }
      step++;
    }

    return -1;
  }
}
