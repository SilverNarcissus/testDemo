package testPackage;

/**
 * Created by SilverNarcissus on 2017/2/11.
 */
public class TestLazyInitialize {
    static String getField(){
        return FieldHolder.field;
    }

    private static class FieldHolder{
        FieldHolder(){
            System.out.println("FieldHolder");
        }
        static final String field=computeField();
    }

    private static String computeField() {
        System.out.println("Initialize!");
        return "Start";
    }
}
