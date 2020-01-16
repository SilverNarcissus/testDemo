package algorithm;


import java.util.*;

/**
 * You are asked to cut off trees in a forest for a golf event.
 * The forest is represented as a non-negative 2D map, in this map:
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 * The place with number bigger than 1 represents a tree can be walked through,
 * and this positive number represents the tree's height.
 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first.
 * And after cutting, the original place has the tree will become a grass (value 1).
 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees.
 * If you can't cut off all the trees, output -1 in that situation.
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 */
public class AStar {
    private static final int[] X = new int[]{-1, 1, 0, 0};
    private static final int[] Y = new int[]{0, 0, -1, 1};

    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                int height = forest.get(i).get(j);
                if (height > 1) {
                    trees.add(new int[]{height, i, j});
                }
            }
        }
        Collections.sort(trees, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        int result = 0;
        int xs = 0;
        int ys = 0;
        for (int[] tree : trees) {
            int dis = dis(forest, xs, ys, tree[1], tree[2]);
            if (dis == -1) {
                return -1;
            }
            result += dis;
            xs = tree[1];
            ys = tree[2];
        }

        return result;
    }

    // use A* algorithm to get the distance
    private int dis(List<List<Integer>> forest, int xs, int ys, int xd, int yd) {
        // [0] means G + H, [1] means G, [2] means x, [3] means y
        PriorityQueue<int[]> p = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        int n = forest.size();
        int m = forest.get(0).size();
        boolean[][] record = new boolean[n][m];
        p.add(new int[]{0, 0, xs, ys});
        while (!p.isEmpty()) {
            int[] cur = p.poll();
            // find destination
            if (cur[2] == xd && cur[3] == yd) {
                return cur[1];
            }
            record[cur[2]][cur[3]] = true;
            for (int i = 0; i < 4; i++) {
                int x = cur[2] + X[i];
                int y = cur[3] + Y[i];
                if (check(x, y, record, n, m, forest)) {
                    int h = manhattanDis(x, y, xd, yd);
                    p.offer(new int[]{cur[1] + 1 + h, cur[1] + 1, x, y});
                }
            }
        }

        return -1;
    }

    private boolean check(int x, int y, boolean[][] record, int n, int m, List<List<Integer>> forest) {
        return x >= 0 && x < n && y >= 0 && y < m && !record[x][y] && forest.get(x).get(y) != 0;
    }

    // act as H in A*
    private int manhattanDis(int xs, int ys, int xd, int yd) {
        return Math.abs(xs - xd) + Math.abs(ys - yd);
    }
}
