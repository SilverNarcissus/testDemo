package algorithm;

/**
 * Created by SilverNarcissus on 2018/3/22.
 */
public class SegmentTree {
    private TreeNode root;
    private int len;


    public SegmentTree(int[] array) {
        root = buildTree(array, 0, array.length - 1);
        len = array.length;
    }

    public static void main(String[] args) {
        int[] i = {1, 3, 5, 7};
        SegmentTree segmentTree = new SegmentTree(i);
        System.out.println(segmentTree.getRange(0, 3));
        segmentTree.updateRange(0, 0, 10);
        System.out.println(segmentTree.getRange(0, 3));
    }

    /**
     * build a segment tree
     *
     * @param array data
     * @param l     left
     * @param r     right
     * @return the root of the segment tree
     */
    private TreeNode buildTree(int[] array, int l, int r) {
        if (l == r) {
            return new TreeNode(l, r, array[l]);
        }

        TreeNode node = new TreeNode(l, r);

        node.left = buildTree(array, l, (l + r) >> 1);
        node.right = buildTree(array, ((l + r) >> 1) + 1, r);
        aggregateFunction(node, node.left, node.right);

        return node;
    }

    /**
     * get range query
     *
     * @param left  left bound
     * @param right right bound
     * @return the result of range query
     */
    public int getRange(int left, int right) {
        if (rangeCheck(left, right)) {
            return -1;
        }

        return internalGetRange(root, left, right);
    }

    /**
     * check the range of query or update
     * @param left left bound
     * @param right right bound
     * @return the range of query or update is out of bounds or not
     */
    private boolean rangeCheck(int left, int right) {
        return left >= len || right >= len || left < 0 || right < 0;
    }

    /**
     * get range recursively
     *
     * @param node  current node
     * @param left  left bound
     * @param right right bound
     * @return the result of range query
     */
    private int internalGetRange(TreeNode node, int left, int right) {
        if (left > node.r || right < node.l) {
            return 0;
        }
        if (node.l >= left && node.r <= right) {
            return node.value;
        }

        if (node.isTag) {
            pushDown(node, node.tag);
        }

        return combine(internalGetRange(node.left, left, right),
                internalGetRange(node.right, left, right));
    }

    /**
     * push down the tag
     *
     * @param node parent
     * @param tag  tag
     */
    protected void pushDown(TreeNode node, int tag) {
        if (node.left != null) {
            node.value = (node.left.r - node.left.l + 1) * tag;
            node.left.tag = tag;
            node.left.isTag = true;
        }

        if (node.right != null) {
            node.value = (node.right.r - node.right.l + 1) * tag;
            node.right.tag = tag;
            node.right.isTag = true;
        }
    }

    /**
     * combine two value from child when doing a range query
     *
     * @param left  left child value
     * @param right right child value
     * @return combine value
     */
    protected int combine(int left, int right) {
        return left + right;
    }


    /**
     * update one value of the array
     *
     * @param loc   the location of the value
     * @param value new value
     */
    public void update(int loc, int value) {
        if (rangeCheck(loc, loc)) {
            return;
        }

        innerUpdate(root, loc, loc, value);
    }

    /**
     * update range value of the array
     *
     * @param left left bound
     * @param right right bound
     * @param value new value
     */
    public void updateRange(int left, int right, int value) {
        if (rangeCheck(left, right)) {
            return;
        }

        innerUpdate(root, left, right, value);
    }

    /**
     * inner update function
     *
     * @param node root of the segement tree
     * @param left left bound
     * @param right right bound
     * @param value new value
     */
    private void innerUpdate(TreeNode node, int left, int right, int value) {
        if (left > node.r || right < node.l) {
            return;
        }

        System.out.println(node.l + " " + node.r + " " + node.value);

        if (node.l >= left && node.r <= right) {
            node.value = (node.r - node.l + 1) * value;
            node.isTag = true;
            node.tag = value;
            return;
        }

        if (node.isTag) {
            pushDown(node, node.tag);
        }

        innerUpdate(node.left, left, right, value);
        innerUpdate(node.right, left, right, value);

        aggregateFunction(node, node.left, node.right);
    }

    /**
     * aggregate children to the root<br>
     * eg: sum, gcd and so on
     *
     * @param root       root
     * @param leftChild  left child
     * @param rightChild right child
     */
    protected void aggregateFunction(TreeNode root, TreeNode leftChild, TreeNode rightChild) {
        root.value = 0;

        if (leftChild != null) {
            root.value += leftChild.value;
        }
        if (rightChild != null) {
            root.value += rightChild.value;
        }
    }

    /**
     * basic tree node class
     */
    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int l;
        int r;
        int value;
        boolean isTag;
        int tag;

        TreeNode(int l, int r) {
            this.l = l;
            this.r = r;
        }

        TreeNode(int l, int r, int value) {
            this.l = l;
            this.r = r;
            this.value = value;
        }
    }
}
