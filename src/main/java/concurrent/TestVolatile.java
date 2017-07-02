package concurrent;

/**
 * Created by SilverNarcissus on 2017/3/19.
 */
public class TestVolatile {
    volatile static int x = 0;

    public static void main(String[] args) {
        //改变属性
        Thread printThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(x);
            }
        });
        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                x = 10;
            }
        });

        writeThread.start();
        printThread.start();

    }

}
