package bytedance;


import java.util.HashMap;
import java.util.Scanner;

public class Q4 {

  static int id = 1;
  static int loc = 0;
  private static HashMap<String, Node> memo = new HashMap<>();

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();

    for (int i = 0; i < n; i++) {
      memo.clear();
      id = 1;
      loc = 0;
      System.out.println(buildTree(in.next()).name);
      // System.out.println(memo);
    }
  }

  private static Node buildTree(String exp) {
    char cur = exp.charAt(loc);
    loc++;
    if (isOp(cur)) {
      int thisId = id;
      id++;

      loc++;
      Node left = buildTree(exp);
      loc++;
      Node right = buildTree(exp);
      Node n = new Node(cur + "(" + left.expName + "," + right.expName + ")", thisId);

      n.left = left;
      n.right = right;
      loc++;

      return buildNode(n, cur);
    } else {
      return buildNode(new Node("" + cur, id++), cur);
    }
  }

  private static Node buildNode(Node n, char cur) {
    if (memo.containsKey(n.expName)) {
      id--;
      n.name = String.valueOf(memo.get(n.expName).id);
    }
    else{
      // is op
      if(n.left != null){
        n.name = cur + "(" + n.left.name + "," + n.right.name + ")";
      }
      else{
        n.name = cur + "";
      }
      memo.put(n.expName, n);
    }

    return n;
  }

  private static boolean isOp(char cur) {
    return cur == '+' || cur == '-' || cur == '*' || cur == '/';
  }


}


class Node {

  Node left;
  Node right;
  String name;
  String expName;
  int id;

  public Node(String name, int id) {
    this.expName = name;
    this.id = id;
  }

  public String toString() {
    return "" + id;
  }
}