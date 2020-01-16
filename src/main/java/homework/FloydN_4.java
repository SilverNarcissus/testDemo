package homework;

import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/12/6.
 */
public class FloydN_4 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int n = scanner.nextInt();
    int[][] graph = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        graph[i][j] = scanner.nextInt();
      }
    }

    graph = floyd(graph, n);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        System.out.print(graph[i][j]);
        if(j != n - 1){
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

  private static int[][] floyd(int[][] graph, int n){
    for (int k = 0; k < n; k++) {
      int[][] next = new int[n][n];
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          // algorithm
          if(i == j){
            continue;
          }
          next[i][j] = -1;
          for (int mid = 0; mid < n; mid++) {
            if(graph[i][mid] != -1 && graph[mid][j] != -1){
              int dis = graph[i][mid] + graph[mid][j];
              if(next[i][j] == -1){
                next[i][j] = dis;
              }
              else{
                next[i][j] = Math.min(next[i][j], dis);
              }
            }
          }
          //
        }
      }
      graph = next;
    }

    return graph;
  }
}
