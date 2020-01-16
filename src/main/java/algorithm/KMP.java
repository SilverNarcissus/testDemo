package algorithm;

import java.util.Random;

/**
 * Created by SilverNarcissus on 2019/3/28.
 */
public class KMP {
    public static void main(String[] args) {
        KMP kmp = new KMP();
        //System.out.println(kmp.KMP("eeecdecabaaeadaebcbbcbaceddaabddecddceceadddbdabeeddcdaeabccaaebebadecaadbecebdbeacaaedcbcccaeccedde", "ebbcd"));
        for (int i = 0; i < 1000; i++) {
            String s = kmp.buildRandomString(100);
            String pattern = kmp.buildRandomString(5);
            //System.out.println(s + " " + pattern);
            if(kmp.KMP(s, pattern) != s.indexOf(pattern)){
                System.out.println(s + " " + pattern);
            }
            //System.out.println(kmp.KMP(s, pattern));
        }

    }

    private String buildRandomString(int n){
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append((char) (r.nextInt(5) + 'a'));
        }

        return sb.toString();
    }

    public int KMP(String s, String pattern){
        int[] next = getNext(pattern);
        //System.out.println(Arrays.toString(next));
        int loc = 0;
        int i = 0;
        while(i < s.length()){
            //System.out.println(i + " " + loc);
            if(loc == pattern.length()){
                return i - loc;
            }

            if(loc == -1 || s.charAt(i) == pattern.charAt(loc)){
                loc++;
                i++;
            }
            else{
                loc = next[loc];
            }
        }

        return loc == pattern.length() ? s.length() - loc : -1;
    }

    private int[] getNext(String s){
        int[] result = new int[s.length() + 1];
        result[0] = -1;
        int i = 0;
        int j = -1;
        while(i < s.length()){
            if(j == -1 || s.charAt(i) == s.charAt(j)){
                result[++i] = ++j;
            }
            else {
                j = result[j];
            }
        }

        return result;
    }
}
