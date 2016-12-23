package DataStructure;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2016/12/13.
 */
public class HeapSortTest {
    @Test
    public void sort() throws Exception {
        HeapSort heap = new HeapSort();
        int[] array = {1, 2, 3, 5, 4, 2, 5, 2, 1, 6, 2, 7, 8, 23, 7, 2, 6, 2};
        //heap.Sort(array, 2, array.length - 1);
        for (int i : heap.Sort(array,0,4)) {
            System.out.println(i);
        }
    }

    @Test
    public void fullSort(){
        HeapSort heap = new HeapSort();
        int[] array = {1, 2, 3, 5, 4, 2, 5, 2, 1, 6, 2, 7, 8, 23, 7, 2, 6, 2};
        //heap.Sort(array, 2, array.length - 1);
        for (int i : heap.fullSort(array)) {
            System.out.println(i);
        }
    }
}