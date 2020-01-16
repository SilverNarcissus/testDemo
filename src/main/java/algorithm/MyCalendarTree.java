package algorithm;

import java.util.*;

/**
 * Created by SilverNarcissus on 2019/4/14.
 */
//["MyCalendarThree","book","book","book","book","book","book","book","book","book","book"]
// [[],[24,40],[43,50],[27,43],[5,21],[30,40],[14,29],[3,19],[3,14],[25,39],[6,19]]
public class MyCalendarTree {
    TreeMap<Integer, Node> event;
    int max = 1;
    public MyCalendarTree() {
        event = new TreeMap<>();

    }

    public static void main(String[] args) {
        MyCalendarTree myCalendarTree = new MyCalendarTree();
        myCalendarTree.book(24, 40);
        myCalendarTree.book(43, 50);
        myCalendarTree.book(27, 43);
        myCalendarTree.book(5, 21);
    }

    public int book(int start, int end) {
        Map.Entry<Integer, Node> before = event.lowerEntry(end);
        int last = end;
        while (before != null) {
            if (before.getValue().end <= start) {
                break;
            }
            last = overlap(before.getValue(), start, end, last);
            before = event.lowerEntry(before.getKey());
        }
        if(start < last){
            event.put(start, new Node(start, last, 1));
        }


        for(Map.Entry<Integer, Node> cur : event.entrySet()){
            System.out.print(cur + " ");
        }
        System.out.println();
        return max;
    }

    private int overlap(Node cur, int s, int e, int last) {
        int x = Math.max(cur.start, s);
        int y = Math.min(cur.end, e);
        if (cur.start < x) {
            event.put(cur.start, new Node(cur.start, x, cur.count));
        }
        if (cur.end > y) {
            event.put(y, new Node(y, cur.end, cur.count));
        }

        event.put(x, new Node(x, y, cur.count + 1));
        max = Math.max(max, cur.count + 1);
        if (y < last) {
            event.put(y, new Node(y, last, 1));
        }
        return x;
    }

    class Node {
        int start;
        int end;
        int count;

        public Node(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    ", count=" + count +
                    '}';
        }
    }
}

