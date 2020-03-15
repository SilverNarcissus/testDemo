package algorithm;
import java.util.*;

public class AVLTree {

  HeightTreeNode root = null;

  int size;


  public static void main(String[] args) {
    AVLTree avlTree = new AVLTree();
    for (int i = 0; i < 20; i++) {
      System.out.println(avlTree.add(i));
      avlTree.print();
      System.out.println("!!!!!!!!!!!!!!!!!!!!");
    }

    System.out.println(avlTree.add(15));
    System.out.println(avlTree.add(7));
  }

  /**
   * add an element
   * @param val element
   * @return whether the element is contains by tree
   */
  public boolean add(int val) {
    if (root == null) {
      root = new HeightTreeNode();
      root.val = val;
      size++;
      return true;
    }

    int beforeSize = size;
    root = insertIntern(val, root);
    return beforeSize != size;
  }

  private HeightTreeNode insertIntern(int val, HeightTreeNode root) {
    // go left
    if (root.val > val) {
      if (root.left == null) {
        HeightTreeNode node = new HeightTreeNode();
        node.val = val;
        root.left = node;
        root.leftHeight++;
        size++;
      } else {
        root.left = insertIntern(val, root.left);
        root.leftHeight = Math.max(root.left.leftHeight, root.left.rightHeight) + 1;
      }
    }
    // go right
    else if (root.val < val) {
      if (root.right == null) {
        HeightTreeNode node = new HeightTreeNode();
        node.val = val;
        root.right = node;
        root.rightHeight++;
        size++;
      } else {
        root.right = insertIntern(val, root.right);
        root.rightHeight = Math.max(root.right.leftHeight, root.right.rightHeight) + 1;
      }
    }
    else {
      // elements which in the tree has been added
      return root;
    }

    // check balance
    if (root.leftHeight > root.rightHeight + 1) {
      if (root.left.rightHeight > root.left.leftHeight) {
        root.left = leftShift(root.left);
      }

      return rightShift(root);
    } else if (root.rightHeight > root.leftHeight + 1) {
      if (root.right.leftHeight > root.right.rightHeight) {
        root.right = rightShift(root.right);
      }

      return leftShift(root);
    }

    return root;
  }

  private HeightTreeNode leftShift(HeightTreeNode cur) {
    HeightTreeNode res = cur.right;
    HeightTreeNode temp = res.left;
    res.left = cur;
    cur.right = temp;

    cur.rightHeight = temp == null ? 0 : Math.max(temp.rightHeight, temp.leftHeight) + 1;
    res.leftHeight = Math.max(cur.rightHeight, cur.leftHeight) + 1;

    print(res);
    return res;
  }

  private HeightTreeNode rightShift(HeightTreeNode cur) {
    HeightTreeNode res = cur.left;
    HeightTreeNode temp = res.right;
    res.right = cur;
    cur.left = temp;

    cur.leftHeight = temp == null ? 0 :  Math.max(temp.rightHeight, temp.leftHeight) + 1;
    res.rightHeight = Math.max(cur.rightHeight, cur.leftHeight) + 1;

    return res;
  }

  public void print() {
    List<List<Integer>> visualList = new ArrayList<>();
    report(root, visualList, 0);

    for (List<Integer> l : visualList) {
      System.out.println(l);
    }
  }

  private void print(HeightTreeNode root) {
    List<List<Integer>> visualList = new ArrayList<>();
    report(root, visualList, 0);

    for (List<Integer> l : visualList) {
      System.out.println(l);
    }
  }

  private void report(HeightTreeNode root, List<List<Integer>> visualList, int level) {
    if(visualList.size() <= level){
      visualList.add(new ArrayList<>());
    }

    if (root == null) {
      visualList.get(level).add(null);
      return;
    }


    report(root.left, visualList, level + 1);
    visualList.get(level).add(root.val);
    report(root.right, visualList, level + 1);
  }


  class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
      val = x;
    }

    TreeNode() {

    }
  }

  class HeightTreeNode extends TreeNode {

    int leftHeight;
    int rightHeight;

    HeightTreeNode left;
    HeightTreeNode right;

    @Override
    public String toString() {
      return "HeightTreeNode{" +
          "val=" + val +
          ", leftHeight=" + leftHeight +
          ", rightHeight=" + rightHeight +
          ", left=" + left +
          ", right=" + right +
          '}';
    }
  }
}
