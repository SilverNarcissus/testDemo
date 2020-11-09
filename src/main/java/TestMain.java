import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by SilverNarcissus on 16/10/31.
 */
public class TestMain {

  private int a;
  private String s;

  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService pool = Executors.newScheduledThreadPool(1);
    pool.submit(new Runnable() {
      @Override
      public void run() {
        try {
          while (true){
            System.out.println("1");
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    pool.submit(new Runnable() {
      @Override
      public void run() {
        try {
          while (true){
            System.out.println("2");
            Thread.sleep(1000);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    pool.shutdown();



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


