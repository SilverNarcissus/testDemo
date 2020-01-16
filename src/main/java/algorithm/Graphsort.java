package algorithm;

import java.util.*;

/**
 * Created by SilverNarcissus on 2019/9/22.
 */
public class Graphsort {

  public static void main(String[] args) {
    List<List<Integer>> items = new ArrayList<>();
    List<Integer> temp1 =  new ArrayList<>();
    temp1.add(2);
    temp1.add(1);
    temp1.add(3);
    items.add(temp1);
    List<Integer> temp2 =  new ArrayList<>();
    temp2.add(2);
    temp2.add(4);
    items.add(temp2);
    items.add(new ArrayList<>());
    items.add(new ArrayList<>());
    items.add(new ArrayList<>());
    int[] group = {2,0,-1,3,0};

    Solution solution = new Solution();
    System.out.println(Arrays.toString(solution.sortItems(5, 5, group, items)));

  }
}

class Solution {

  HashMap<Integer, HashSet<Integer>> map;

  public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
    int groupId = m;
    map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      if (group[i] == -1) {
        group[i] = groupId;
        put(groupId++, i);
      } else {
        put(group[i], i);
      }
    }

    System.out.println(map);

    Graph groupMap = new Graph(map, beforeItems, n, group);
    List<Integer> groupSort = groupMap.sort();
    System.out.println(groupSort);
    if (groupSort.isEmpty()) {
      return new int[0];
    }

    int[] out = new int[n];
    int loc = 0;
    for (int i : groupSort) {
      HashSet<Integer> vertex = map.get(i);
      if (vertex.size() == 1) {
        for (int j : vertex) {
          out[loc++] = j;
        }
        continue;
      }
      Graph graph = new Graph(vertex, beforeItems);
      List<Integer> res = graph.sort();
      if (res.isEmpty()) {
        return new int[0];
      }

      for (int j : res) {
        out[loc++] = j;
      }
    }

    return out;
  }

  private void put(int key, int id) {
    if (map.containsKey(key)) {
      map.get(key).add(id);
    } else {
      HashSet<Integer> s = new HashSet<>();
      s.add(id);
      map.put(key, s);
    }
  }
}

class Graph {

  HashMap<Integer, Node> vertexs = new HashMap<>();

  public Graph(HashSet<Integer> vertex, List<List<Integer>> beforeItems) {
    for (int i : vertex) {
      vertexs.put(i, new Node(i));
    }

    for (int i : vertex) {
      Node cur = vertexs.get(i);
      for (int before : beforeItems.get(i)) {
        if (vertexs.containsKey(before)) {
          Node b = vertexs.get(before);
          b.next.add(i);
          cur.in++;
        }
      }
    }
  }

  public Graph(HashMap<Integer, HashSet<Integer>> map, List<List<Integer>> beforeItems, int n,
      int[] group) {
    for (int i : map.keySet()) {
      vertexs.put(i, new Node(i));
    }

    for (int i = 0; i < n; i++) {
      Node cur = vertexs.get(group[i]);
      for (int before : beforeItems.get(i)) {
        Node b = vertexs.get(group[before]);
        if(b.id != cur.id){
          if(b.next.add(group[i])){
            cur.in++;
          }
        }
      }
    }

    System.out.println(vertexs);
  }

  public List<Integer> sort() {
    List<Integer> res = new ArrayList<>();
    Queue<Node> q = new LinkedList<>();
    for (Node n : vertexs.values()) {
      if (n.in == 0) {
        q.add(n);
      }
    }

    while (!q.isEmpty()) {
      Node cur = q.poll();
      System.out.println("out" + cur.id);
      res.add(cur.id);
      for (int next : cur.next) {
        Node after = vertexs.get(next);
        after.in--;
        if (after.in == 0) {
          q.add(after);
        }
      }
    }

    return res.size() == vertexs.size() ? res : Collections.emptyList();
  }

  class Node {

    int id;
    int in;
    HashSet<Integer> next = new HashSet<>();

    public Node(int id) {
      this.id = id;
    }

    @Override
    public String toString() {
      return "id :" + id + " in :" + in + " next: " + next;
    }
  }
}