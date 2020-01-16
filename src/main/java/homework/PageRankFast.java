package homework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by SilverNarcissus on 2019/11/22.
 */
public class PageRankFast {

  private static final double PRECISE = 0.00000001;

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

    Data data = new Data(n);
    int[] group = tarjan(graph, data);
    int[] mapping = mapping(group, data);
    //System.out.println(Arrays.toString(group));

    double[] rank = pageRank(graph, mapping, data);

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

  private static double[] pageRank(List<List<Integer>> graph, int[] mapping, Data data) {
    // build matrix
    double[][] matrix = new double[data.size][data.size];
    // for speed
    List<List<Integer>> hasEdge = new ArrayList<>();
    for (int i = 0; i < data.size; i++) {
      hasEdge.add(new ArrayList<>());
    }

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
            hasEdge.get(row).add(col);
            matrix[row][col] = 1.0d / total;
          }
        }
      }
    }

    double[] rank = new double[data.size];
    Arrays.fill(rank, 1.0d / data.size);

    int count = 0;
    while (step(matrix, rank, hasEdge, data)) {
      count++;
      if (count > 100) {
        break;
      }
    }

    return rank;
  }

  private static boolean step(double[][] matrix, double[] rank, List<List<Integer>> loc,
      Data data) {
    double[] result = new double[data.size];
    // dot
    for (int i = 0; i < data.size; i++) {
      double sum = 0;
      for (int j : loc.get(i)) {
        sum += matrix[i][j] * rank[j];
      }
      result[i] = sum;
    }

    // check whether is convergence
    double max = 0;
    for (int i = 0; i < data.size; i++) {
      max = Math.max(max, Math.abs(result[i] - rank[i]));
    }

    if (max < PRECISE) {
      return false;
    }

    System.arraycopy(result, 0, rank, 0, data.size);
    return true;
  }

  /**
   * map node id into available map node id
   *
   * @param group scc group
   * @return map node id -> available map node id
   */
  private static int[] mapping(int[] group, Data data) {
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

    data.size = loc;
    return mapping;
  }


  /**
   * tarjan algorithm to find scc
   *
   * @param graph graph
   * @return scc group
   */
  private static int[] tarjan(List<List<Integer>> graph, Data data) {
    int[] define = new int[graph.size()];
    int[] low = new int[graph.size()];
    for (int i = 0; i < graph.size(); i++) {
      if (define[i] == 0) {
        dfs(graph, i, define, low, data);
      }
    }
    return low;
  }

  private static int dfs(List<List<Integer>> graph, int cur, int[] define, int[] low, Data data) {
//    System.out.println("loc: " + cur);
//    System.out.println("stack: " + stack);
//    System.out.println("low: " + Arrays.toString(low));
    if (define[cur] != 0) {
      return data.visit[cur] ? low[cur] : Integer.MAX_VALUE;
    }

    define[cur] = data.id++;
    low[cur] = define[cur];
    data.stack.push(cur);
    data.visit[cur] = true;

    for (int next : graph.get(cur)) {
      low[cur] = Math.min(low[cur], dfs(graph, next, define, low, data));
    }

    if (low[cur] == define[cur]) {
      //System.out.println("out!!! :" + cur);
      while (!data.stack.isEmpty()) {
        int node = data.stack.pop();
        low[node] = low[cur];
        data.visit[node] = false;
        if (node == cur) {
          break;
        }
      }
    }

    return low[cur];
  }

}


class Data {
  int id = 1;
  Stack<Integer> stack = new Stack<>();
  int size = 0;
  boolean[] visit;

  public Data(int n) {
    visit = new boolean[n];
  }
}