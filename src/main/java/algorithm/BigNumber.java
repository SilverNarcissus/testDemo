package algorithm;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SilverNarcissus on 2018/9/14.
 */
public class BigNumber {
    private static final long MOD = 100000;

    public static void main(String[] args) {
//        print(mul(a2, a1));
//        System.out.println(mul(a1, a2));
//        System.out.println(i1.multiply(i2));
        BigInteger i = new BigInteger("2");


        long time = System.nanoTime();
        List<Long> result = pow(234524);
        sub_1(result);
        printSize(result);
        print_100(result);

        System.out.println((System.nanoTime() - time) / 1000 / 1000);

        String s1 = i.pow(234524).subtract(new BigInteger("1")).toString();
        String s2 = print(result);

        if(!s1.equals(s2.substring(s2.length() - s1.length()))){
            System.out.println("error!");
        }
    }

    public static void sub_1(List<Long> array){
        int i = 0;
        while(array.get(i) == 0){
            array.set(i, MOD - 1);
            i++;
        }
        array.set(i, array.get(i) - 1);
    }

    public static void printSize(List<Long> array){
        int result = (array.size() - 1) * 5;
        long last = array.get(array.size() - 1);

        if(last > 9999){
            result += 5;
        }
        else if(last > 999){
            result += 4;
        }
        else if(last > 99){
            result += 3;
        }
        else if(last > 9){
            result += 2;
        }
        else {
            result += 1;
        }

        System.out.println(result);
    }

    public static void print_100(List<Long> array){
        for (int i = 99; i >= 0; i--) {
            if(array.size() <= i){
                System.out.print("00000");
            }
            else {
                System.out.printf("%05d", array.get(i));
            }

            if(i % 10 == 0){
                System.out.println();
            }
        }
    }

    public static List<Long> pow(int p){
        ArrayList<Long> result = new ArrayList<>();
        if(p <= 4){
            result.add((long) Math.pow(2, p));
            return result;
        }

        List<Long> next_result = pow(p >> 1);
        List<Long> temp_result = mul(next_result, next_result);
        if((p & 1) == 0){
            return temp_result;
        }

        result.add(2L);
        return mul(temp_result, result);
    }

    public static String print(List<Long> array) {
        StringBuilder sb = new StringBuilder();
        for (int i = array.size() - 1; i >= 0; i--) {
            sb.append(String.format("%05d", array.get(i)));
        }
        return sb.toString();
    }

    public static ArrayList<Long> mul(List<Long> first, List<Long> second) {
        ArrayList<Long> result = new ArrayList<>();
        int loc = 0;
        for (long i : first) {
            ArrayList<Long> step = new ArrayList<>();
            long up = 0;
            for (long j : second) {
                long cur = up + i * j;
                up = cur / MOD;
                cur = cur % MOD;

                step.add(cur);
            }
            if (up > 0) {
                step.add(up);
            }

            //System.out.println("result:" + result);
            add(step, result, loc);
            //System.out.println("step:" + step);
            loc++;
        }

        return result;
    }

    private static void add(ArrayList<Long> big, ArrayList<Long> small, int loc) {
        long up = 0;
        for (long l : big) {
            if (small.size() <= loc) {
                long cur = up + l;
                up = cur / MOD;
                cur = cur % MOD;
                //System.out.println("up: " + up);
                small.add(cur);
            } else {
                long cur = up + l + small.get(loc);
                up = cur / MOD;
                cur = cur % MOD;
                //System.out.println("up: " + up);
                small.set(loc, cur);
            }
            loc++;
        }
        if (up > 0) {
            small.add(up);
        }
    }
}
