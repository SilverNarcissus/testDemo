package concurrent;

import javassist.bytecode.analysis.Executor;

import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SilverNarcissus on 2017/1/30.
 * 1.0 version on 2017/1/30.
 */
public class CyclicBarrierTest {

    private static final int ROW = 1000;
    private static final int CROSS = 1000;

    private double[][] matrix = new double[ROW][CROSS];

    public CyclicBarrierTest() {
        fill();
    }

    /**
     * 多线程运行
     */
    public void concurrent() {
        CyclicBarrier barrier = new CyclicBarrier(ROW, new Runnable() {

            @Override
            public void run() {
                System.out.println("Done!");
            }
        });

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < ROW; i++) {
            executorService.execute(new Worker(i, barrier));
            //new Thread(new Worker(i,barrier)).start();
        }

        executorService.shutdown();
    }

    /**
     * 单线程运行
     */
    public void single() {
        for (int a = 0; a < ROW; a++) {
            for (int b = 0; b < CROSS; b++) {
                compute(matrix[a][b]);
            }
        }

        System.out.println("Done!");
    }

    /**
     * initialize the matrix and fill it
     */
    private void fill() {
        for (int i = 0; i < ROW; i++) {
            Arrays.fill(matrix[i], 520.520);
        }
    }

    /**
     * to do something
     *
     * @param a param
     */
    private void compute(double a) {
        for (int i = 0; i < 100; i++) {
            Math.sqrt(a);
        }
    }

    /**
     * a worker to do things
     */
    private class Worker implements Runnable {
        int row = 0;
        CyclicBarrier c;

        Worker(int row, CyclicBarrier c) {
            this.row = row;
            this.c = c;
        }

        @Override
        public void run() {
            for (int i = 0; i < CROSS; i++) {
                compute(matrix[row][i]);
            }

            try {
                c.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
