package homework;

import java.util.*;
import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/11/21.
 */
public class PageRank {

  private static int id = 1;
  private static Stack<Integer> stack = new Stack<>();
  private static int size = 0;
  private static final double PRECISE = 0.0000001;
  private static boolean[] visit;


  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int m = scanner.nextInt();
    List<List<Integer>> graph = new ArrayList<>(n);
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < m; i++) {
      int start = scanner.nextInt();
      int end = scanner.nextInt();
      graph.get(start).add(end);
    }

    visit = new boolean[n];
    int[] group = tarjan(graph);
    int[] mapping = mapping(group);
    double[] rank = pageRank(graph, mapping);

    int k = scanner.nextInt();
    for (int i = 0; i < k; i++) {
      int node = scanner.nextInt();
      if (mapping[node] == -1) {
        System.out.println("None");
      } else {
        System.out.println(String.format("%.5f", rank[mapping[node]]));
      }
    }

  }

  private static double[] pageRank(List<List<Integer>> graph, int[] mapping) {
    // build matrix
    double[][] matrix = new double[size][size];
    for (int i = 0; i < mapping.length; i++) {
      int col = mapping[i];
      if (col != -1) {
        int total = 0;
        for (int node : graph.get(i)) {
          if (mapping[node] != -1) {
            total++;
          }
        }

        for (int node : graph.get(i)) {
          int row = mapping[node];
          if (row != -1) {
            matrix[row][col] = 1.0d / total;
          }
        }
      }
    }

    double[] rank = new double[size];
    Arrays.fill(rank, 1.0d / size);

    int count = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        System.out.print(matrix[i][j] + " ");
      }
      System.out.println();
    }
    while (step(matrix, rank)) {
      count++;
    }

    return rank;
  }

  private static boolean step(double[][] matrix, double[] rank) {
    double[] result = new double[size];
    // dot
    for (int i = 0; i < size; i++) {
      double sum = 0;
      for (int j = 0; j < size; j++) {
        sum += matrix[i][j] * rank[j];
      }
      result[i] = sum;
    }

    // check whether is convergence
    double max = 0;
    for (int i = 0; i < size; i++) {
      max = Math.max(max, Math.abs(result[i] - rank[i]));
    }

    if (max < PRECISE) {
      return false;
    }

    System.arraycopy(result, 0, rank, 0, size);
    return true;
  }

  /**
   * map node id into available map node id
   *
   * @param group scc group
   * @return map node id -> available map node id
   */
  private static int[] mapping(int[] group) {
    int n = group.length;
    // find valid group
    int[] count = new int[n + 1];
    int validGroup = 0;
    for (int i : group) {
      count[i]++;
      if (count[i] != 1) {
        validGroup = i;
        break;
      }
    }

    // build mapping
    int loc = 0;
    int[] mapping = new int[n];
    Arrays.fill(mapping, -1);
    for (int i = 0; i < n; i++) {
      if (group[i] == validGroup) {
        mapping[i] = loc++;
      }
    }

    size = loc;
    return mapping;
  }


  private static int[] tarjan(List<List<Integer>> graph) {
    int[] define = new int[graph.size()];
    int[] low = new int[graph.size()];
    for (int i = 0; i < graph.size(); i++) {
      if (define[i] == 0) {
        dfs(graph, i, define, low);
      }
    }
    return low;
  }

  private static int dfs(List<List<Integer>> graph, int cur, int[] define, int[] low) {
//    System.out.println("loc: " + cur);
//    System.out.println("stack: " + stack);
//    System.out.println("low: " + Arrays.toString(low));
    if (define[cur] != 0) {
      return visit[cur] ? low[cur] : Integer.MAX_VALUE;
    }

    define[cur] = id++;
    low[cur] = define[cur];
    stack.push(cur);
    visit[cur] = true;

    for (int next : graph.get(cur)) {
      low[cur] = Math.min(low[cur], dfs(graph, next, define, low));
    }

    if (low[cur] == define[cur]) {
      //System.out.println("out!!! :" + cur);
      while (!stack.isEmpty()) {
        int node = stack.pop();
        low[node] = low[cur];
        visit[node] = false;
        if (node == cur) {
          break;
        }
      }
    }

    return low[cur];
  }

}
