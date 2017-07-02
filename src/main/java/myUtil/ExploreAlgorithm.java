package myUtil;

/**
 * Created by SilverNarcissus on 2017/3/8.
 */
public class ExploreAlgorithm {
    public void explore(int number, int limit) {
        int i = 1;
        int[] array = new int[limit];
        while (true) {
            boolean flag = true;
            if (check()) {
                flag = false;
            }
            if (flag && isDone()) {
                System.out.println("result!");
            }
            //开始向下一层探索
            if (i < limit && flag) {
                i++;
                array[i] = 1;
                continue;
            }
            //开始回溯
            while (array[i] == number && i > 1) {
                i--;
            }
            if (array[i] == number && i == 1) {
                break;
            } else array[i]++;
        }
    }

    private boolean isDone() {
        return true;
    }

    private boolean check() {
        return true;
    }
}
