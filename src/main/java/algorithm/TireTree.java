package algorithm;

/**
 * Created by SilverNarcissus on 2019/3/16.
 */
public class TireTree {
    public static void main(String[] args) {
        TireTree tireTree = new TireTree();
        int[] input = {6, 5, 10, 2, 25, 1, 61, 27, 2, 84, 74, 8, 4, 842, 45, 23, 23};
        System.out.println(tireTree.brutForce(input, 125));
        System.out.println(tireTree.check(input, 125));
    }

    private int brutForce(int[] array, int m) {
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if ((array[i] ^ array[j]) > m) {
                    counter++;
                }
            }
        }

        return counter;
    }

    private int check(int[] array, int m) {
        Node root = new Node();
        for (int i : array) {
            insert(root, 31, i);
        }

        int result = 0;
        for (int i : array) {
            result += find(root, 31, m, i);
        }

        return result >> 1;
    }


    private void insert(Node root, int loc, int val) {
        if (loc < 0) {
            return;
        }

        int next = (val & (1 << loc)) > 0 ? 1 : 0;
        if (root.next[next] == null) {
            Node n = new Node();
            n.count++;
            root.next[next] = n;
        } else {
            root.next[next].count++;
        }

        insert(root.next[next], loc - 1, val);
    }

    private int find(Node root, int loc, int m, int val) {
        if (root == null || loc < 0) {
            return 0;
        }

        int guard = 1 << loc;
        int nextOne = (val & guard) == 0 ? 1 : 0;
        int nextZero = nextOne ^ 1;
        if ((m & guard) > 0) {
            return find(root.next[nextOne], loc - 1, m, val);
        }
        int result = root.next[nextOne] == null ? 0 : root.next[nextOne].count;
        return find(root.next[nextZero], loc - 1, m, val) + result;
    }

    private class Node {
        int count = 0;
        Node[] next = new Node[2];
    }
}
