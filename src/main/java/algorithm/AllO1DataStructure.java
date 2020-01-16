package algorithm;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by SilverNarcissus on 2019/4/8.
 * Implement a data structure supporting the following operations:
 * <p>
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 */
public class AllO1DataStructure {
    public static void main(String[] args) {
        AllO1DataStructure dataStructure = new AllO1DataStructure();
        dataStructure.inc("hello");
        dataStructure.inc("hello");
        dataStructure.getMaxKey();
        dataStructure.inc("leet");
        dataStructure.print();
    }
    private Node dummy;
    private Node tail;
    private HashMap<String, Node> map;

    /**
     * Initialize your data structure here.
     */
    public AllO1DataStructure() {
        dummy = new Node(null, null, 0, null);
        tail = new Node(dummy, null, Integer.MAX_VALUE, null);
        dummy.next = tail;
        map = new HashMap<>();
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            Node before = removeKeyInc(key, cur);
            map.put(key, addKey(cur.val + 1, key, before));
        } else {
            map.put(key, addKey(1, key, dummy));
        }
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        if (map.containsKey(key)) {
            Node cur = map.get(key);
            Node before = removeKeyDesc(key, cur);
            if(cur.val != 1) {
                map.put(key, addKey(cur.val - 1, key, before));
            }
            else{
                map.remove(key);
            }
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        Node max = tail.prev;
        if (max.val == 0) {
            return "";
        }

        for (String s : max.set) {
            return s;
        }

        return null;
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        Node min = dummy.next;
        if (min.val == Integer.MAX_VALUE) {
            return "";
        }

        for (String s : min.set) {
            return s;
        }

        return null;
    }

    private Node addKey(int val, String key, Node before) {
        if (before.val == val) {
            before.set.add(key);
            return before;
        } else if (before.next.val == val) {
            before.next.set.add(key);
            return before.next;
        }
        HashSet<String> newSet = new HashSet<>();
        newSet.add(key);
        Node cur = new Node(before, before.next, val, newSet);
        addNode(cur, before);
        return cur;
    }

    private Node removeKeyInc(String key, Node cur) {
        cur.set.remove(key);
        if (cur.set.isEmpty()) {
            Node before = cur.prev;
            removeNode(cur);
            return before;
        }
        return cur;
    }

    private Node removeKeyDesc(String key, Node cur) {
        cur.set.remove(key);
        Node before = cur.prev;
        if (cur.set.isEmpty()) {
            removeNode(cur);
        }
        return before;
    }

    private void removeNode(Node cur) {
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
    }

    private void addNode(Node cur, Node before) {
        cur.next.prev = cur;
        before.next = cur;
    }

    class Node {
        Node prev;
        Node next;
        int val;
        HashSet<String> set;

        public Node(Node prev, Node next, int val, HashSet<String> set) {
            this.prev = prev;
            this.next = next;
            this.val = val;
            this.set = set;
        }
    }

    private void print(){
        Node root = dummy.next;
        while(root != tail){
            System.out.println(root.val + " " + root.set);
            root = root.next;
        }
    }
}
