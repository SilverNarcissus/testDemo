package meituan;

import java.util.*;


public class Solution3 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int[][] array = new int[n][3];
    for (int i = 0; i < n; i++) {
      array[i][0] = in.nextInt();
      array[i][1] = in.nextLine().contains("R") ? 1 : 0;
      array[i][2] = i;
    }

    handle(array);
  }

  private static void handle(int[][] array) {
    Arrays.sort(array, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return Integer.compare(o1[0], o2[0]);
      }
    });

    List<Pair> l = new ArrayList<>();
    int[] res = new int[array.length];
    Arrays.fill(res, -1);
    for(int i = 0; i < array.length; i++){
      int[] a = array[i];
      if(a[1] == 1){
        l.add(new Pair(a[0], a[2]));
      }
      else{
        for (int j = l.size() - 1; j >= 0; j--) {
          Pair p = l.get(j);
          int dis = a[0] - p.val;
          if((dis & 1) == 0){
            int time = dis >> 1;
            res[p.loc] = time;
            res[a[2]] = time;
            // System.out.println("put:" + a[2] + " " + p.loc + " " + time);
            l.remove(j);
            break;
          }
        }
      }
    }

    for (int i : res) {
      System.out.println(i);
    }
  }

  private static class Pair{
    int val;
    int loc;

    public Pair(int val, int loc) {
      this.val = val;
      this.loc = loc;
    }
  }
}
