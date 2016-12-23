package Singleton;

import java.io.Serializable;

/**
 * Created by SilverNarcissus on 2016/11/26.
 */
public class OddSingleton implements Serializable {
    private static final OddSingleton o = new OddSingleton();

    private OddSingleton() {

    }

    public static OddSingleton getInstance() {
        return o;
    }

    public void printHello() {

    }

    public Object readResolve() {
        return o;
    }
}
