package testPackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SilverNarcissus on 2017/1/19.
 */
public class TestGeneric<E> {
    private E[] element;

    @SuppressWarnings("unchecked")
    TestGeneric() {
        element = (E[]) new Object[10];
    }

    public static void main(String[] args) {
        TestGeneric<String> stringTestGeneric = new TestGeneric<String>();
        stringTestGeneric.in("123");
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("123");
        stringTestGeneric.printFirst(strings);
    }

    private void in(E e) {
        element[0] = e;
    }

    private E out() {
        return element[0];
    }

    private native char test();

    private <T> void printFirst(List<T> list) {
        if (list.size() != 0) {
            System.out.println(list.get(0));
        } else {
            list.add(list.get(0));
        }
    }
}
