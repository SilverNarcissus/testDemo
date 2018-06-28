package myUtil.memorizer;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Cache implement of computable
 */
@ThreadSafe
public class CacheComputable<I, V> implements Computable<I, V> {
    private final Map<I, Future<V>> cache;
    private final Computable<I, V> task;

    /**
     * @param task the compute function
     */
    public CacheComputable(Computable<I, V> task) {
        this(16, 0.75f, task);
    }

    /**
     *
     * @param initialSize initial size of map
     * @param resizeFactor resize factor of map
     * @param task the compute function
     */
    public CacheComputable(int initialSize, float resizeFactor, Computable<I, V> task) {
        cache = new ConcurrentHashMap<>(initialSize, resizeFactor);
        this.task = task;

    }

    @Override
    public V compute(I input) {
        while(true){
            Future<V> future = cache.get(input);
            if(future == null){
                Callable<V> eval = new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return task.compute(input);
                    }
                };

                FutureTask<V> futureTask = new FutureTask<V>(eval);
                // put if absent is a thread safe method
                future = cache.putIfAbsent(input, futureTask);
                // if cache don't has future we compute the result
                // or we just get the result
                if(future == null){
                    future = futureTask;
                    futureTask.run();
                }
            }

            try {
                return future.get();
            } catch (InterruptedException e) {
                cache.remove(input, future);
                e.printStackTrace();
            } catch (ExecutionException e) {
                throw checkException(e);
            }
        }
    }

    /**
     * change the exception from future into RuntimeException
     * @param throwable the exception from future
     * @return RuntimeException
     */
    private RuntimeException checkException(Throwable throwable){
        if(throwable instanceof RuntimeException){
            return (RuntimeException) throwable;
        }
        if(throwable instanceof Error){
            throw (Error) throwable;
        }

        throw new IllegalStateException("Not unchecked", throwable);
    }
}
