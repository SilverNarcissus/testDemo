package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.DoubleAccumulator;

/**
 * Created by SilverNarcissus on 2017/3/13.
 */
public class Command implements Runnable {

    @Override
    public void run() {
        double result = 0;
        for (int i = 0; i < 1000000000; i++) {
            result += Math.sqrt(i);
        }

        System.out.println("Calculate done!" + result);
    }
}
