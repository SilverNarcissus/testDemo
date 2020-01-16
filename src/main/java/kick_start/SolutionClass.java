package kick_start;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Created by SilverNarcissus on 2019/11/22.
 */
public class SolutionClass {

  private static int count = 0;

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    int n = scanner.nextInt();
    if (n == -1) {
      System.out.println(-1);
      return;
    }

    TreeNode root = new TreeNode(scanner.nextInt());
    if (root.val == -1) {
      System.out.println(-1);
      return;
    }

    Queue<TreeNode> q = new LinkedList<>();
    q.add(root);
    for (int i = 1; i <= n; i++) {
      int size = q.size();
      for (int j = 0; j < size; j++) {
        TreeNode cur = q.poll();
        int left = scanner.nextInt();
        int right = scanner.nextInt();
        if (left != -1) {
          TreeNode l = new TreeNode(left);
          q.add(l);
          cur.left = l;
        }
        if (right != -1) {
          TreeNode r = new TreeNode(right);
          q.add(r);
          cur.right = r;
        }
      }
    }

    dfs(root);
    System.out.println(count);
  }

  private static int dfs(TreeNode cur) {
    if (cur == null) {
      return -1;
    }

    int l = dfs(cur.left);
    int r = dfs(cur.right);
    // left
    if(l == -1 && r == -1){
      count++;
      return cur.val;
    }

    if(l == -1){
      if(cur.val == r){
        count++;
        return cur.val;
      }
      else{
        return -2;
      }
    }

    if(r == -1){
      if(cur.val == l){
        count++;
        return cur.val;
      }
      else{
        return -2;
      }
    }

    if(l == cur.val && r == cur.val){
      count++;
      return cur.val;
    }

    return -2;
  }
}

class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode(int val) {
    this.val = val;
  }
}

