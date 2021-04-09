package algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by SilverNarcissus on 2019/3/28.
 */
public class KMP {
    public static void main(String[] args) {

        //System.out.println(kmp.KMP("eeecdecabaaeadaebcbbcbaceddaabddecddceceadddbdabeeddcdaeabccaaebebadecaadbecebdbeacaaedcbcccaeccedde", "ebbcd"));
        for (int i = 0; i < 1000; i++) {
            String s = KMP.buildRandomString(100);
            String pattern = KMP.buildRandomString(5);
            KMP kmp = new KMP(pattern);
            //System.out.println(s + " " + pattern);
            if(kmp.getIndex(s) != s.indexOf(pattern)){
                System.out.println(s + " " + pattern);
            }
            //System.out.println(kmp.KMP(s, pattern));
        }

    }

    private static String buildRandomString(int n){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((char) (r.nextInt(5) + 'a'));
        }

        return sb.toString();
    }

    private final int[] next;
    private final String pattern;

    public KMP(String pattern) {
        this.pattern = pattern;
        int n = pattern.length();
        next = new int[n];
        Arrays.fill(next, -1);
        int j = -1;
        for (int i = 1; i < n; i++) {
            while (j != -1 && pattern.charAt(j + 1) != pattern.charAt(i)) {
                j = next[j];
            }
            if (pattern.charAt(j + 1) == pattern.charAt(i)) {
                j = j + 1;
            }
            next[i] = j;
        }
    }

    public int getIndex(String query) {
        int j = -1;
        for (int i = 0; i < query.length(); i++) {
            while (j != -1 && query.charAt(i) != pattern.charAt(j + 1)) {
                j = next[j];
            }
            if (query.charAt(i) == pattern.charAt(j + 1)) {
                j++;
            }
            if (j == pattern.length() - 1) {
                return i - pattern.length() + 1;
            }
        }
        return -1;
    }
}