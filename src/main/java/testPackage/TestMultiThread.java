package testPackage;

/**
 * Created by SilverNarcissus on 2017/1/23.
 */
public class TestMultiThread {
    public static void main(String[] args) {
        Thread one = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    System.out.println("Thread 1");
                }
            }
        });
        one.start();
        System.out.println("main");
    }
}
