package algorithm;

/**
 * Created by SilverNarcissus on 2018/3/22.
 */
public class BinaryIndexTree {
    private int[] array;
    private int[] BIX;

    public static void main(String[] args) {
        int[] i = {1, 3, 5};
        BinaryIndexTree binaryIndexTree = new BinaryIndexTree(i);
        System.out.println(binaryIndexTree.sumRange(0, 2));
        binaryIndexTree.update(2, 2);
        System.out.println(binaryIndexTree.sumRange(0, 2));
    }


    public BinaryIndexTree(int[] array) {
        this.array = new int[array.length];
        BIX = new int[array.length + 1];
        System.arraycopy(array, 0, this.array, 0, array.length);

        for(int i = 0; i < array.length; i++) {
            init(i, array[i]);
        }
    }

    private void init(int loc, int val){
        loc++;
        while(loc <= array.length) {
            BIX[loc] += val;
            // loc = loc & (-loc)
            loc = loc + Integer.lowestOneBit(loc);
        }
    }

    public void update(int loc, int val){
        int diff = val - array[loc];
        array[loc] = val;
        init(loc, diff);
    }

    public int sumRange(int l, int r){
        return get(r) - get(l - 1);
    }

    private int get(int loc) {
        loc++;
        int result = 0;
        while(loc > 0) {
            result += BIX[loc];
            // loc = loc & (-loc)
            loc = loc - Integer.lowestOneBit(loc);
        }

        return result;
    }
}
