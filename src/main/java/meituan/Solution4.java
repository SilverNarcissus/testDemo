package meituan;

import java.util.*;

public class Solution4 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    int x = in.nextInt();
    int y = in.nextInt();

    int[][] carMap = new int[m][3];
    int[][] personMap = new int[m][3];
    for (int i = 0; i < m; i++) {
      int start = in.nextInt();
      int end = in.nextInt();
      int carDis = in.nextInt();
      int personDis = in.nextInt();
      carMap[i][0] = start;
      carMap[i][1] = end;
      carMap[i][2] = carDis;
      personMap[i][0] = start;
      personMap[i][1] = end;
      personMap[i][2] = personDis;
    }
    int[] hold = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      hold[i] = in.nextInt();
    }

    int[] carRes = dis2(carMap, n, y);
    int[] personRes = dis2(personMap, n, x);
    System.out.println(Arrays.toString(carRes));
    System.out.println(Arrays.toString(personRes));

    int min = personRes[y];
    for (int i = 1; i <= n; i++) {
      min = Math.min(min, Math.max(personRes[i], hold[i]) + carRes[i]);
    }

    System.out.println(min);
  }

  private static int[] dis(int[][] sides, int n, int start){
    HashMap<Integer, List<Pair>> map = new HashMap<>();
    for (int[] side : sides) {
      map.computeIfAbsent(side[0], id -> new ArrayList<>()).add(new Pair(side[1], side[2]));
      map.computeIfAbsent(side[1], id -> new ArrayList<>()).add(new Pair(side[0], side[2]));
    }

    int[] res = new int[n + 1];
    Arrays.fill(res, -1);
    PriorityQueue<Pair> queue =new PriorityQueue<>(new Comparator<Pair>() {
      @Override
      public int compare(Pair o1, Pair o2) {
        return Integer.compare(o1.left, o2.left);
      }
    });

    queue.add(new Pair(0, start));
    while(!queue.isEmpty()){
      Pair cur = queue.poll();
      res[cur.right] = cur.left;
      for(Pair next : map.getOrDefault(cur.right, Collections.emptyList())){
        if(res[next.left] == -1){
          queue.add(new Pair(cur.left + next.right, next.left));
        }
      }
    }

    return res;
  }

  private static int[] dis2(int[][] sides, int n, int start){
    int[][] link = new int[n + 1][n + 1];
    for (int i = 1; i < n + 1; i++) {
      Arrays.fill(link[i], 1000000);
    }

    for (int[] side : sides) {
      link[side[0]][side[1]] = side[2];
      link[side[1]][side[0]] = side[2];
    }

    for (int i = 1; i < n + 1; i++) {
      link[i][i] = 0;
    }

    for (int k = 1; k < n + 1; k++) {
      for (int i = 1; i < n + 1; i++) {
        for (int j = 1; j < n + 1; j++) {
          link[i][j] = Math.min(link[i][j], link[i][k] + link[k][j]);
        }
      }
    }

    return link[start];
  }

  private static class Pair{
    int left;
    int right;

    public Pair(int left, int right) {
      this.left = left;
      this.right = right;
    }
  }
}
