package exam;

import java.util.*;

public class Solution8 {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    List<int[]> list = new ArrayList<>();
    String s = in.next();
    while(s.startsWith("[")){
      int[] cur = new int[3];
      s = s.substring(1, s.length() - 1);
      String[] part = s.split(",");
      cur[0] = Integer.parseInt(part[0]);
      cur[1] = Integer.parseInt(part[1]);
      cur[2] = Integer.parseInt(part[2]);
      list.add(cur);
      s = in.next();
    }
    int total = Integer.parseInt(s);
    int loc = in.nextInt();

    System.out.println(handle(list, total, loc));
  }

  public static int handle(List<int[]> list, int total, int loc){
//    for(int[] i : list){
//      System.out.println(Arrays.toString(i));
//    }
//    System.out.println(total);
//    System.out.println(loc);

    List<MyPair>[] paths = new List[total + 1];
    for (int i = 0; i <= total; i++) {
      paths[i] = new ArrayList<>();
    }

    for (int[] i : list) {
      paths[i[0]].add(new MyPair(i[1], i[2]));
    }

    boolean[] record = new boolean[total + 1];
    boolean[] reach = new boolean[total + 1];
    reach[loc] = true;
    record[loc] = true;

    int res = dfs(paths, loc, record, reach);

    for(int i = 1; i <= total; i++){
      if(!reach[i]){
        return -1;
      }
    }

    return res;
  }

  public static int dfs(List<MyPair>[] list, int loc, boolean[] record, boolean[] reach){
    int res = 0;
    // System.out.println("loc: " + loc);
    reach[loc] = true;
    for(MyPair next : list[loc]){
      if(!record[next.target]){
        record[next.target] = true;
        res = Math.max(res, next.dis + dfs(list, next.target, record, reach));
        record[next.target] = false;
      }
    }

    return res;
  }
}

class MyPair{
  int target;
  int dis;

  public MyPair(int target, int dis) {
    this.target = target;
    this.dis = dis;
  }
}