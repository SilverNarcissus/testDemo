package DataStructure;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FenwickTree {

  public static void main(String[] args) {
    Map<String, List<String>> s = new LinkedHashMap<>();
    s.put("213", null);
    s.put(null, new LinkedList<>());
    System.out.println(s);
  }

  int[] array;
  int size;


  public FenwickTree(int size) {
    this.size = size;
    array = new int[size];
  }

  public void add(int loc, int val){
    while(loc < size){
      array[loc] += val;
      loc += loc & (-loc);
    }
  }

  public int sum(int loc){
    int res = 0;
    while(loc > 0){
      res += array[loc];
      loc -= loc & (-loc);
    }

    return res;
  }
}
