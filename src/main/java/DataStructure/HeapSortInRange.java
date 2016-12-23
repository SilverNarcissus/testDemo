package DataStructure;

import java.util.Comparator;

/**
 * Created by SilverNarcissus on 2016/12/5.
 */
public class HeapSortInRange {

    //核心思想：将low-high的元素映射到0~high-low，交换时交换数组的真实编号，使用映射编号寻找子节点
    //这样使得空间开销为常数级
    public Comparable[] Sort(Comparable[] array, int low, int high) {
        //建堆
        for (int i = (low + high + 1) / 2; i >= low; i--) {
            percDown(array, i, low, high);
        }
        //调整
        for (int i = high; i >= low; i--) {
            swap(array, 0, i);
            percDown(array, low, low, i-1);
        }
        return array;
    }

    private void swap(Comparable[] array, int first, int second) {
        Comparable temp = array[first];
        array[first] = array[second];
        array[second] = temp;
    }

    private void percDown(Comparable[] array, int i, int low, int high) {
        int child;
        Comparable temp = array[i];
        int a = i;
        for (; leftChild(a, low) <= high; a = child) {
            //System.out.println("a:" + a + "now: " + array[a]);
            child = leftChild(a, low);
            //选择两子节点之间较大的
            if (child + 1 <= high && array[child].compareTo(array[child + 1]) < 0) {
                child++;
            }
            //如果已经是最大堆，则跳出
            if (temp.compareTo(array[child]) > 0) {
                break;
            }
            //
            array[a] = array[child];
        }
        array[a] = temp;
    }

    private int leftChild(int a, int low) {
        return ((a - low) * 2 + 1) + low;
    }

}
