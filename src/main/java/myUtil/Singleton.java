package myUtil;

/**
 * Created by SilverNarcissus on 2018/9/24.
 */
public class Singleton {
    public static void main(String[] args) {
        Singleton s = new Singleton();

        System.out.println("here");


    }


    private static class Obj{
        static Integer i;

        static {
            i = 0;
            System.out.println("build");
        }
    }
}
