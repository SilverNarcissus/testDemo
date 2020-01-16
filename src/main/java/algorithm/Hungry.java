package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by SilverNarcissus on 2019/4/3.
 */
public class Hungry {
    int[] record;
    int[] location;
    ArrayList<Integer>[] map;

    public static void main(String[] args) {
        Hungry h = new Hungry();
        h.record = new int[5];
        Arrays.fill(h.record, -1);
        h.location = new int[5];
        h.map = new ArrayList[5];
        ArrayList<Integer> l1 = new ArrayList<>();
        ArrayList<Integer> l2 = new ArrayList<>();
        ArrayList<Integer> l3 = new ArrayList<>();
        ArrayList<Integer> l4 = new ArrayList<>();
        ArrayList<Integer> l5 = new ArrayList<>();
        l1.add(0);
        l1.add(1);

        l2.add(0);
        l2.add(2);

        l3.add(2);
        l3.add(4);

        l4.add(4);
        l5.add(3);
        h.map[0] = l1;
        h.map[1] = l2;
        h.map[2] = l3;
        h.map[3] = l4;
        h.map[4] = l5;

        System.out.println(h.maxMatch(h.map));
    }

    private int maxMatch(ArrayList[] nodes) {
        int result = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (dfs(i)) {
                result++;
            }
        }

        return result;
    }


    private boolean dfs(int nodeId) {
        if (map[nodeId] == null) {
            return false;
        }

        for (int i = location[nodeId]; i < map[nodeId].size(); i++) {
            int node = map[nodeId].get(i);
            if (record[node] == -1 || dfs(record[node])) {
                record[node] = nodeId;
                location[nodeId] = i + 1;
                return true;
            }
        }

        location[nodeId] = map[nodeId].size();
        return false;
    }
}
