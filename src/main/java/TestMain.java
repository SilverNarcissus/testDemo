import java.util.GregorianCalendar;

/**
 * Created by SilverNarcissus on 16/10/31.
 */
public class TestMain {
    private int a;
    private String s;

    public static void main(String[] args) {
        System.out.println(Math.pow(0 , 0));
//        TestMain testMain = new TestMain();
//        char[] chars = {'1'};
//        testMain.f(chars);
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
        GregorianCalendar calendar=new GregorianCalendar();
        System.out.println(calendar);
    }
}


