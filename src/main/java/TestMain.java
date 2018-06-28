import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by SilverNarcissus on 16/10/31.
 */
public class TestMain {
    private int a;
    private String s;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Father f = new Child();
        TestMain testMain = new TestMain();
        f.accept(testMain);
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
//        l.add(2)；
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();

        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int next = Math.max(i, j) + 1;
                dp[i][j] = Integer.MAX_VALUE;
                dp[i][j] = Math.min(dp[i][j], dp[next][j] + (i == 0 ? 0 : Math.abs(a[next - 1] - a[i - 1])));
                dp[i][j] = Math.min(dp[i][j], dp[i][next] + (j == 0 ? 0 : Math.abs(a[next - 1] - a[j - 1])));
            }
        }

        System.out.println(dp[0][0]);


//        TestMain testMain = new TestMain();
//        char[] chars = {'1'};
//        testMain.f(chars);
    }

    @SuppressWarnings("TypeParameterUnusedInFormals")
    public static <T extends Collection<Integer>> T getCollection() {
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
        GregorianCalendar calendar = new GregorianCalendar();
        System.out.println(calendar);
    }

    public void visit(Father f){
        System.out.println("father");
    }

    public void visit(Child c){
        System.out.println("child");
    }
}


