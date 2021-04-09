import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by SilverNarcissus on 16/10/31.
 */
public class TestMain {

  private static int a;
  private String s;
  private int[] array;
  int b;


  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    a = 0;
    Object lock = new Object();

    TestMain main = new TestMain();
    main.array = new int[10];

    System.out.println(main.getVal());
    System.out.println(main.getVal2());
//    ExecutorService pool = Executors.newScheduledThreadPool(10);
//    for (int i = 0; i < 10; i++) {
//      pool.submit(new Runnable() {
//        @Override
//        public void run() {
//          for (int i = 0; i < 10000; i++) {
//            increase(lock);
//          }
//        }
//      });
//    }


//    Thread another = new Thread(() -> {
//      synchronized (lock) {
//        while (true) {
//          System.out.println("here");
//
//          try {
//            lock.wait(1000);
//          } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//          }
//        }
//
//      }
//    });
//
//    another.start();
//    another.interrupt();
//
//    Thread.sleep(1000000);

//    Thread.sleep(5000);
//    System.out.println(a);
//    Thread.sleep(5000);
//    System.out.println(a);

//    System.out.println("开始...");
//
//    CompletableFuture.supplyAsync(new Supplier<String>() {
//      @Override
//      public String get() {
//        try {
//          TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//
//        System.out.println("返回 A");
//        return "A";
//      }
//    }).applyToEitherAsync(CompletableFuture.supplyAsync(new Supplier<String>() {
//      @Override
//      public String get() {
//        try {
//          TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//
//        System.out.println("返回 B");
//        return "B";
//      }
//    }), new Function<String, Object>() {
//
//      @Override
//      public Object apply(String s) {
//        return s + " 最快返回";
//      }
//    }).whenCompleteAsync(new BiConsumer<Object, Throwable>() {
//      @Override
//      public void accept(Object o, Throwable throwable) {
//        System.out.println(o.getClass());
//        System.out.println(o.toString());
//      }
//    });
//
//
//
//    System.out.println("main thread");
//    Thread.sleep(10000);
//
    main.increase(lock);
    main.increase2(lock);
    main.increase3();
  }

  private int change(){
    array = new int[5];
    Arrays.fill(array, 1);
    return 2;
  }

  private int getVal(){
    return array[change()];
  }

  private int getVal2(){
    int index = change();
    return array[index];
  }


  public void increase(Object o) {
    synchronized (o) {
      b = b + 1;
    }
  }

  public void increase2(Object o) {
    b = b + 1;
  }

  public synchronized void increase3() {
    synchronized (this) {
      b = b + 1;
    }
  }

  public static void lambdaTest(List<Integer> elements) {
    long time = System.nanoTime();
    int res = elements.stream().map(x -> x + 1).reduce((x, y) -> x + y).get();
    System.out.println(res);
    System.out.println("time:" + (System.nanoTime() - time) / 1000 / 1000);
  }

  @SuppressWarnings("TypeParameterUnusedInFormals")
  public static <T extends Collection<Integer>> T getCollection() {
    return (T) new HashSet<Integer>();
  }

  public int getA() {
    return a;
  }

  public void setA(int a) {
    this.a = a;
  }

  public String getS() {
    return s;
  }

  public void setS(String s) {
    this.s = s;
  }

  public void say() {
    //more code
    sayI();
  }

  private void sayI() {
    int i = 10;
    System.out.println(i);
  }

  private void f(Object o) {
    GregorianCalendar calendar = new GregorianCalendar();
    System.out.println(calendar);
  }

  private void does(Person p) {
    p = new Person();
    p.i = 20;
  }

  public void visit(Father f) {
    System.out.println("father");
  }

  public void visit(Child c) {
    System.out.println("child");
  }

  static class Person {

    int i;
  }
}


