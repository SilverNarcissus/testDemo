package DataStructure;

import java.util.AbstractCollection;
import java.util.Arrays;

/**
 * Created by SilverNarcissus on 2016/12/4.
 */
public class HeapSort {
//    public static void main(String[] args) {
//        HeapSort heap = new HeapSort();
//        int[] array = {1, 2, 3, 5, 4, 2, 5, 2, 1, 6, 2, 7, 8, 23, 7, 2, 6, 2};
//        //heap.Sort(array, 2, array.length - 1);
//        for (int i : heap.Sort(array,0,4)) {
//            System.out.println(i);
//        }
//    }

    public int[] Sort(int[] ints, int low, int high) {
        int[] temp = new int[high-low+1];
        for(int i=low;i<=high;i++){
            temp[i-low]=ints[i];
        }
        //
        fullSort(temp);
        //
        for(int i=low;i<=high;i++){
            ints[i]=temp[i-low];
        }
        return ints;
    }

    public int[] fullSort(int[] array) {
        //建堆
        for (int i = array.length / 2; i >= 0; i--) {
            percDown(array, i, array.length);
        }
        //调整
        for (int i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);
            percDown(array, 0, i);
        }
        return array;
    }

    private void swap(int[] ints, int first, int second) {
        int temp = ints[first];
        ints[first] = ints[second];
        ints[second] = temp;
    }

    private void percDown(int[] array, int i, int n) {
        int child;
        int temp = array[i];
        int a = i;
        for (; leftChild(a) < n; a = child) {
            System.out.println("a:" + a + "now: " + array[a]);
            child = leftChild(a);
            //选择两子节点之间较大的
            if (child + 1 < n && array[child] < array[child + 1]) {
                child++;
            }
            //如果已经是最大堆，则跳出
            if (temp > array[child]) {
                break;
            }
            //
            array[a] = array[child];
        }

        array[a] = temp;
    }

    private int leftChild(int a) {
        return a * 2 + 1;
    }

}
