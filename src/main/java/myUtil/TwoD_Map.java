package myUtil;

import java.util.*;

/**
 * Created by SilverNarcissus on 2017/1/23.
 * version 1.0 on2017/1/24
 */
public class TwoD_Map<K, V> {
    /**
     * This map is used to save the elements
     */
    private Map<K, Map<K, V>> store = new HashMap<K, Map<K, V>>();

    /**
     * This map is used to save the elements which have the reverse start and end
     * should synchronized with store
     */
    private Map<K, Map<K, V>> reverse = new HashMap<K, Map<K, V>>();

    /**
     * put the value in this map
     *
     * @param first  the first key of the value
     * @param second the second key of the value
     * @param value  the value
     */
    public void put(K first, K second, V value) {
        if (!store.containsKey(first)) {
            store.put(first, new HashMap<K, V>());
        }
        store.get(first).put(second, value);
        //synchronized
        if (!reverse.containsKey(second)) {
            reverse.put(second, new HashMap<K, V>());
        }
        reverse.get(second).put(first, value);
    }

    /**
     * get element from this map
     *
     * @param first  the first key of the element
     * @param second the second key of the element
     * @return the element
     */
    public V get(K first, K second) {
        if (store.get(first) == null) {
            return null;
        }
        return store.get(first).get(second);
    }

    /**
     * remove the element in this map
     *
     * @param first  the first key of the element
     * @param second the second key of the element
     * @return the element which has been removed
     */
    public V remove(K first, K second) {
        if (store.get(first) == null) {
            return null;
        }
        //synchronized
        if (reverse.get(second) == null) {
            return null;
        }
        reverse.get(second).remove(first);
        //
        return store.get(first).remove(second);
    }

    /**
     * get the number of all elements
     *
     * @return the number of all element
     */
    public int allElementSize() {
        int result = 0;
        for (Map<K, V> m : store.values()) {
            result += m.size();
        }
        return result;
    }

    /**
     * get the number of elements which first key is param
     *
     * @param first the first key of the elements
     * @return the number of elements which first key is param
     */
    public int elementSizeByFirstKey(K first) {
        if (store.get(first) == null) {
            return 0;
        }
        return store.get(first).size();
    }

    /**
     * get the number of elements which second key is param
     *
     * @param second the first key of the elements
     * @return the number of elements which second key is param
     */
    public int elementSizeBySecondKey(K second) {
        if (reverse.get(second) == null) {
            return 0;
        }
        return reverse.get(second).size();
    }

    /**
     * get the elements which first key is param
     *
     * @param first the first key of the elements
     * @return the elements which first key is param
     */
    public Collection<V> getElementByFirstKey(K first) {
        if (store.get(first) == null) {
            return new HashMap<K, V>().values();
        }
        return store.get(first).values();
    }

    /**
     * get the elements which second key is param
     *
     * @param second the second key of the elements
     * @return the elements which second key is param
     */
    public Collection<V> getElementBySecondKey(K second) {
        if (reverse.get(second) == null) {
            return new HashMap<K, V>().values();
        }
        return reverse.get(second).values();
    }

    /**
     * remove the elements which first key is param
     *
     * @param first the first key
     * @return the elements removed
     */
    public Map<K, V> removeElementsByFirstKey(K first) {
        if (store.get(first) == null) {
            return null;
        }
        //synchronized
        for (K k : store.get(first).keySet()) {
            if (reverse.get(k) != null) {
                reverse.get(k).remove(first);
            }
        }
        //
        return store.remove(first);
    }

    /**
     * remove the elements which second key is param
     *
     * @param second the second key
     * @return the elements removed
     */
    public Map<K, V> removeElementsBySecondKey(K second) {
        if (reverse.get(second) == null) {
            return null;
        }
        //synchronized
        for (K k : reverse.get(second).keySet()) {
            if (store.get(k) != null) {
                store.get(k).remove(second);
            }
        }
        //
        return reverse.remove(second);
    }

    /**
     * clean the empty source which has no elements
     *
     * @return the number of sources has been cleaned
     */
    public int clean() {
        //to record the number of sources has been cleaned
        int count = 0;

        Iterator<Map.Entry<K, Map<K, V>>> it = store.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, Map<K, V>> entry = it.next();
            if (entry.getValue().size() == 0) {
                it.remove();
                count++;
            }
        }

        //clean reverse
        it = reverse.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, Map<K, V>> entry = it.next();
            if (entry.getValue().size() == 0) {
                it.remove();
            }
        }

        return count;
    }
}
