package concurrent;

import java.util.concurrent.*;

/**
 * Created by SilverNarcissus on 2017/2/6.
 * 1.0 version on 2017/2/6
 */
public class BlockingQueueTest {
    public static void main(String[] args) {
        final SynchronousQueue<String> strings = new SynchronousQueue<String>();

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Producer(strings));
        executorService.execute(new Consumer(strings));

        executorService.shutdown();
    }

    /**
     * a producer
     */
    private static class Producer implements Runnable {

        private SynchronousQueue<String> queue;

        public Producer(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                queue.put("first");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Done!");
        }
    }

    /**
     * a consumer
     */
    private static class Consumer implements Runnable {
        private SynchronousQueue<String> queue;

        public Consumer(SynchronousQueue<String> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
