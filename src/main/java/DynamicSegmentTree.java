class DynamicSegmentTree {
  private Node root = new Node(0, (int) 1e5 + 1);

  public DynamicSegmentTree() {

  }

  public void modify(int l, int r, int v) {
    modify(l, r, v, root);
  }

  public void modify(int l, int r, int v, Node node) {
    if (l > r) {
      return;
    }
    if (node.l >= l && node.r <= r) {
      node.min += v;
      node.max += v;
      node.add_min += v;
      node.add_max += v;
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

  public int query(int l, int r, int v) {
    return query(l, r, root, v);
  }

  public int query(int l, int r, Node node, int v) {
    if (node.l > r || node.r < l || node.min > v || node.max < v) {
      return -1;
    }
      
    if (node.l == node.r){
        return node.l;
    }  
    
    pushdown(node);

    int idx = query(l, r, node.left, v);
    if(idx == -1){
        return query(l, r, node.right, v);
    }

    return idx;
  }

  public void pushup(Node node) {
    node.min = Math.min(node.left.min, node.right.min);
    node.max = Math.max(node.left.max, node.right.max);
  }

  public void pushdown(Node node) {
    if (node.left == null) {
      node.left = new Node(node.l, node.mid);
    }
    if (node.right == null) {
      node.right = new Node(node.mid + 1, node.r);
    }
    if (node.add_min != 0) {
      Node left = node.left, right = node.right;
      left.add_min += node.add_min;
      right.add_min += node.add_min;
      left.min += node.add_min;
      right.min += node.add_min;
      node.add_min = 0;

      left.add_max += node.add_max;
      right.add_max += node.add_max;
      left.max += node.add_max;
      right.max += node.add_max;
      node.add_max = 0;
    }
  }

  public String toString(){
      Node cur = root;
      return cur.toString() + " -> " + cur.left.toString() + " -> " + cur.right.toString();
  }

  class Node {

    Node left;
    Node right;
    int l;
    int r;
    int mid;
    int min;
    int max;
    int add_min;
    int add_max;

    public Node(int l, int r) {
      this.l = l;
      this.r = r;
      this.mid = (l + r) >> 1;
    }

    public String toString(){
        return "[" + l + "," + r + "] : " + min + " " + max;
    }
  }
}©leetcode
