package algorithm;

public class DynamicSegmentMaxTree {

  public static void main(String[] args) {
    DynamicSegmentMaxTree segmentTree = new DynamicSegmentMaxTree();
    segmentTree.modify(1,9,9);
    segmentTree.modify(1,1,-2);
    System.out.println(segmentTree.query(1,1));
  }


  private Node root = new Node(1, (int) 1e9);

  public DynamicSegmentMaxTree() {

  }

  public void modify(int l, int r, int v) {
    modify(l, r, v, root);
  }

  public void modify(int l, int r, int v, Node node) {
    if (l > r) {
      return;
    }
    if (node.l >= l && node.r <= r) {
      node.v += v;
      node.add += v;
      return;
    }
    pushdown(node);
    if (l <= node.mid) {
      modify(l, r, v, node.left);
    }
    if (r > node.mid) {
      modify(l, r, v, node.right);
    }
    pushup(node);
  }

  public int query(int l, int r) {
    return query(l, r, root);
  }

  public int query(int l, int r, Node node) {
    if (l > r) {
      return 0;
    }
    if (node.l >= l && node.r <= r) {
      return node.v;
    }
    pushdown(node);
    int v = 0;
    if (l <= node.mid) {
      v = Math.max(v, query(l, r, node.left));
    }
    if (r > node.mid) {
      v = Math.max(v, query(l, r, node.right));
    }
    return v;
  }

  public void pushup(Node node) {
    node.v = Math.max(node.left.v, node.right.v);
  }

  public void pushdown(Node node) {
    if (node.left == null) {
      node.left = new Node(node.l, node.mid);
    }
    if (node.right == null) {
      node.right = new Node(node.mid + 1, node.r);
    }
    if (node.add != 0) {
      Node left = node.left, right = node.right;
      left.add += node.add;
      right.add += node.add;
      left.v += node.add;
      right.v += node.add;
      node.add = 0;
    }
  }

  class Node {

    Node left;
    Node right;
    int l;
    int r;
    int mid;
    int v;
    int add;

    public Node(int l, int r) {
      this.l = l;
      this.r = r;
      this.mid = (l + r) >> 1;
    }
  }
}


