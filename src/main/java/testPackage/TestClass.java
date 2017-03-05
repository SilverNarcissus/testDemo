package testPackage;

import java.util.Collections;
import java.util.List;

/**
 * Created by SilverNarcissus on 2017/1/12.
 */
public class TestClass {
    private int x;

    public TestClass(int x) {
        this.x = x;
    }


    public static void main(String[] args) {
        System.out.println("Start");
        TestLazyInitialize testLazyInitialize=new TestLazyInitialize();
        System.out.println("GetField");
        TestLazyInitialize.getField();
    }

}
