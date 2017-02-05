package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SilverNarcissus on 2017/1/30.
 * 1.0 version on 2017/1/30.
 */
public class CountDownLatchTest {
    /**
     * the number of thread
     */
    private static final int NUM_OF_THREAD = 10;

    static CountDownLatch start = new CountDownLatch(1);
    static CountDownLatch end = new CountDownLatch(NUM_OF_THREAD);

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < NUM_OF_THREAD; i++) {
            executorService.execute(new Worker(start, end, String.valueOf(i)));
        }

        System.out.println("-----Now start!-----");
        start.countDown();
        end.await();
        System.out.println("-----Now end!-----");

        executorService.shutdown();
    }

    private static class Worker implements Runnable {

        CountDownLatch start;
        CountDownLatch end;
        /**
         * the name of the worker
         */
        String name;

        Worker(CountDownLatch start, CountDownLatch end, String name) {
            this.start = start;
            this.end = end;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //do something
            f();
            //
            end.countDown();
        }

        void f() {
            System.out.println(name);
        }
    }
}
