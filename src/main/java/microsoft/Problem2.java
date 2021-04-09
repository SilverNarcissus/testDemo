package microsoft;

import java.util.HashSet;
import java.util.Set;

public class Problem2 {

  public int solution(Tree T) {
    return dfs(T, new HashSet<>());
  }

  private int dfs(Tree root, Set<Integer> set){
    if(root == null){
      return 0;
    }

    if(!set.add(root.x)){
      return 0;
    }

    int max = Math.max(dfs(root.l, set), dfs(root.r, set));

    set.remove(root.x);

    return max + 1;
  }
}

class Tree {
  public int x;
  public Tree l;
  public Tree r;
}
