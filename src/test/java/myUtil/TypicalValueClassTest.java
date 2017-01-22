package myUtil;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by SilverNarcissus on 2016/12/6.
 */
public class TypicalValueClassTest {
    @Test
    public void testEqual() {
        ArrayList<String> childNames = new ArrayList<String>();
        childNames.add("Narcissus");
        childNames.add("Akita");
        ArrayList<String> anotherChildNames = new ArrayList<String>();
        anotherChildNames.addAll(childNames);

        TypicalValueClass typicalValueClass = new TypicalValueClass("Silver", 20, 5500.5, childNames);
        TypicalValueClass another = new TypicalValueClass("Silver", 20, 5500.5, anotherChildNames);
        System.out.println(typicalValueClass.equals(another));
    }

    @Test
    public void testHashCode() {
        ArrayList<String> childNames = new ArrayList<String>();
        childNames.add("Narcissus");
        childNames.add("Akita");
        ArrayList<String> anotherChildNames = new ArrayList<String>();
        anotherChildNames.addAll(childNames);

        TypicalValueClass typicalValueClass = new TypicalValueClass("Silver", 20, 5500.5, childNames);
        TypicalValueClass another = new TypicalValueClass("Silver", 20, 5500.5, anotherChildNames);
        System.out.println(typicalValueClass.hashCode());
        System.out.println(another.hashCode());
    }

    @Test
    public void testFormat() {
        ArrayList<String> childNames = new ArrayList<String>();
        childNames.add("Narcissus");
        childNames.add("Akita");
        ArrayList<String> anotherChildNames = new ArrayList<String>();
        anotherChildNames.addAll(childNames);

        TypicalValueClass typicalValueClass = new TypicalValueClass("Silver", 2, 5500.5, childNames);
        System.out.println(typicalValueClass.formatString());
    }

    @Test
    public void testClone() {
        ArrayList<String> childNames = new ArrayList<String>();
        childNames.add("Narcissus");
        childNames.add("Akita");

        TypicalValueClass typicalValueClass = new TypicalValueClass("Silver", 2, 5500.5, childNames);
        TypicalValueClass typicalValueClass1 = typicalValueClass.clone();

        typicalValueClass1.getChildNames().remove(1);
        System.out.println(typicalValueClass.getChildNames().get(1));
    }

    @Test
    public void testToString() {
        ArrayList<String> childNames = new ArrayList<String>();
        childNames.add("Narcissus");
        childNames.add("Akita");
        TypicalValueClass typicalValueClass = new TypicalValueClass("Silver", 2, 5500.5, childNames);
        System.out.println(typicalValueClass.print());
    }
}