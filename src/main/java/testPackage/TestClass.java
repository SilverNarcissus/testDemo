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
        List<TestClass> s = Collections.nCopies(5, new TestClass(5));
        s.get(2).x=10;
        for(TestClass t:s){
            System.out.println(t.x);
        }
    }

}
