package exam;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution9 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    char[][] input = new char[n][m];
    for (int i = 0; i < n; i++) {
      String row = in.next();
      for (int j = 0; j < m; j++) {
        input[i][j] = row.charAt(j);
      }
    }

    System.out.println(handle(input));
  }

  public static int handle(char[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[0].length; j++) {
        if (map[i][j] == 'H') {
          return bfs(map, i * map[0].length + j);
        }
      }
    }

    return 0;
  }

  private static int bfs(char[][] map, int loc) {
    Queue<Integer> queue = new LinkedList<>();
    boolean[] record = new boolean[map.length * map[0].length];
    queue.add(loc);
    record[loc] = true;

    int res = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        int cur = queue.poll();
        int x = cur / map[0].length;
        int y = cur % map[0].length;
        // System.out.println(x + " " + y);

        if (map[x][y] == 'T') {
          return res;
        }

        // up
        if (x != 0 && map[x - 1][y] != '#') {
          checkAndAdd(map, queue, record, x - 2, y - 1);
          checkAndAdd(map, queue, record, x - 2, y + 1);
        }

        // left
        if (y != 0 && map[x][y - 1] != '#') {
          checkAndAdd(map, queue, record, x - 1, y - 2);
          checkAndAdd(map, queue, record, x + 1, y - 2);
        }

        // right
        if (y != map[0].length - 1 && map[x][y + 1] != '#') {
          checkAndAdd(map, queue, record, x - 1, y + 2);
          checkAndAdd(map, queue, record, x + 1, y + 2);
        }

        // down
        if (x != map.length - 1 && map[x + 1][y] != '#') {
          checkAndAdd(map, queue, record, x + 2, y - 1);
          checkAndAdd(map, queue, record, x + 2, y + 1);
        }
      }
      res++;
    }

    return -1;
  }

  private static void checkAndAdd(char[][] map, Queue<Integer> queue, boolean[] record, int x, int y) {
    if(!(x >= 0 && y >= 0 && x < map.length && y < map[0].length && map[x][y] != '#')){
      return;
    }

    int loc = x * map[0].length + y;
    if(record[loc]){
      return;
    }

    record[loc] = true;
    queue.add(loc);
  }
}
