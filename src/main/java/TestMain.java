import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by SilverNarcissus on 16/10/31.
 */
public class TestMain {
    private int a;
    private String s;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
//        System.out.println(-1 % 10);
//        Collection<Integer> array = getCollection();
//        System.out.println(array);
//        Object[] o = new Object[12];
//        Arrays.sort(o, new Comparator<Object>() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                return 0;
//            }
//        });
//
//
//        List<Integer> l = new ArrayList<>();
//        l.add(1);
//        l.add(2);


        ArrayList<Integer> arrayList = new ArrayList<>();

        try {
            ArrayList<Integer> array2 = (ArrayList) Class.forName("java.util.ArrayList").newInstance();
            array2.getClass().getMethods();
            Method m = array2.getClass().getMethod("add", Object.class);

            m.invoke(array2, 1);

            System.out.println(array2);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

//        TestMain testMain = new TestMain();
//        char[] chars = {'1'};
//        testMain.f(chars);
    }

    @SuppressWarnings("TypeParameterUnusedInFormals")
    public static <T extends Collection<Integer>> T getCollection(){
        return (T) new HashSet<Integer>();
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


