package testPackage;

/**
 * Created by SilverNarcissus on 2017/12/21.
 */
public class Person {
    public static void main(String[] args) {
        Person p = new Student();
        System.out.println(p.getClass().getName());
        p.function(p.getClass().cast(p));
    }

    public void function(Person p){
        System.out.println("Person");
    }

    public void function(Student p){
        System.out.println("Student");
    }
}
