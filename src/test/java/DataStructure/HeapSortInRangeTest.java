package DataStructure;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/12/13.
 */
public class HeapSortInRangeTest {
    @Test
    public void sort() throws Exception {
        HeapSortInRange heap = new HeapSortInRange();
        Integer[] array = {1, 2, 3, 5, 4, 2, 5, 2, 1, 0, 2, 7, 8, 23, 7, 2, 6, 2};
        //heap.Sort(array, 2, array.length - 1);
        for (Comparable i : heap.Sort(array, 0, 8)) {
            System.out.println(i);
        }
    }

}