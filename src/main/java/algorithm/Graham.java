package algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Graham {

  public int[][] outerTrees(int[][] points) {
    if(points.length == 1){
      return points;
    }

    int[] bottom = findBottom(points);

    Arrays.sort(points, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        int diff = orientation(bottom, o1, o2) - orientation(bottom, o2, o1);

        if (diff == 0)
          return distance(bottom, o1) - distance(bottom, o2);
        else
          return diff > 0 ? 1 : -1;
      }
    });

    int l = points.length - 1;
    while(l >= 0 && orientation(bottom, points[points.length - 1], points[l]) == 0){
      l--;
    }
    l++;
    int r = points.length - 1;
    while(l < r){
      int[] temp = points[l];
      points[l] = points[r];
      points[r] = temp;
      l++;
      r--;
    }


    Stack<int[]> stack = new Stack<>();
    stack.push(points[0]);
    for (int i = 1; i < points.length; i++) {
      int[] point = points[i];
      int[] top = stack.pop();
      while(!stack.isEmpty() && orientation(stack.peek(), top, point) > 0){
        top = stack.pop();
      }

      stack.push(top);
      stack.push(point);
    }


    int[][] res = new int[stack.size()][2];
    int n = stack.size();
    for (int i = 0; i < n; i++) {
      res[i] = stack.pop();
    }


    return res;
  }

  private int[] findBottom(int[][] points){
    int[] res = points[0];
    for(int[] point : points){
      if(point[1] < res[1]){
        res = point;
      }
    }

    return res;
  }

  private int distance(int[] p1, int[] p2){
    return (p1[1] - p2[1]) * (p1[1] - p2[1]) + (p1[0] - p2[0]) * (p1[0] - p2[0]);
  }

  private int orientation(int[] p1, int[] p2, int[] p3){
    return (p2[1] - p1[1]) * (p3[0] - p2[0]) - (p2[0] - p1[0]) * (p3[1] - p2[1]);
  }

}
