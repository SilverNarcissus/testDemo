package concurrent;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * Created by SilverNarcissus on 2017/2/6.
 * 1.0 version on 2017/2/6
 */
public class FutureTaskTest {
    public static void main(String[] args) {
        //
        FutureTask<Long> futureTask = new FutureTask<Long>(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                //do a complicated computation
                long l = 1;
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    l = l * 2;
                }
                return l;
                //
            }
        });

        //a new thread pool to execute futureTask
        ExecutorService e = Executors.newCachedThreadPool();
        e.execute(futureTask);

        //a timer used to record the time
        ScheduledThreadPoolExecutor timer=new ScheduledThreadPoolExecutor(1);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(System.nanoTime()/10000/10000);
            }
        }, 100, 100,TimeUnit.MILLISECONDS);
        //

        long result = 0;
        try {
            result = futureTask.get();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (ExecutionException e1) {
            e1.printStackTrace();
        }
        timer.shutdown();

        System.out.println(result);
        e.shutdown();
    }
}
