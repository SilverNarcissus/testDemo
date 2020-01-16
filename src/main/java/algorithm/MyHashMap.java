package algorithm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Created by SilverNarcissus on 2019/3/29.
 */
public class MyHashMap {
    private int size = 8; // default size
    private LinkedList<Item>[] bucket = new LinkedList[size];
    private int curSize = 0;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {

    }

    public static void main(String[] args) {
        MyHashMap myHashMap = new MyHashMap();
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            myHashMap.put(r.nextInt(10000), 5);
        }
        //[84,82],[59,29],[71,71]
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int loc = hash(key);
        if (bucket[loc] == null) {
            putNew(key, value);
            return;
        }

        for (Item i : bucket[loc]) {
            if (i.key == key) {
                i.value = value;
                return;
            }
        }

        bucket[loc].add(new Item(key, value));
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int loc = hash(key);
        LinkedList<Item> l = bucket[loc];
        if (l == null) {
            return -1;
        }

        for (Item i : l) {
            if (i.key == key) {
                return i.value;
            }
        }

        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int loc = hash(key);
        LinkedList<Item> l = bucket[loc];
        if (l != null) {
            Iterator<Item> i = l.iterator();
            while (i.hasNext()) {
                if (i.next().key == key) {
                    i.remove();
                    return;
                }
            }
        }
    }

    private int hash(int key) {
        return (key ^ (key >>> 16)) & (size - 1);
    }

    private void putNew(int key, int value) {
        if (curSize > (size >> 1)) {
            resize();
        }
        bucketPut(hash(key), key, value);
    }

    private void bucketPut(int loc, int key, int value) {
        if (bucket[loc] != null) {
            bucket[loc].add(new Item(key, value));
        } else {
            curSize++;
            LinkedList<Item> l = new LinkedList<>();
            l.add(new Item(key, value));
            bucket[loc] = l;
        }
    }

    private void resize() {
        LinkedList<Item>[] old = bucket;
        size = size << 1;
        bucket = new LinkedList[size];
        curSize = 0;
        for (LinkedList<Item> l : old) {
            if (l != null) {
                for (Item i : l) {
                    bucketPut(hash(i.key), i.key, i.value);
                }
            }
        }
    }

    class Item {
        int key;
        int value;

        public Item(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}