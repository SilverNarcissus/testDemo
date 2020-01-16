package algorithm;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by SilverNarcissus on 2019/5/12.
 */
public class Duplicate {
    long MOD = 1_000_000_007L;
    long mod;
    HashMap<Integer, String> res = new HashMap<>();
    static String ss;

    public static void main(String[] args) {
        Duplicate d = new Duplicate();
        ss = "okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel";
        //System.out.println("okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel".length());
        String input = "okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajvielokmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel";
        //System.out.println(input.substring(1).contains(ss));
        System.out.println(d.longestDupSubstring(input));
    }

    private String longestDupSubstring(String s){
        int l = 0;
        int r = s.length() - 1;
        while (l <= r){
            int mid = (l + r) >> 1;
            if(check(s, mid)){
                l = mid + 1;
            }
            else{
                r = mid - 1;
            }
        }

//        System.out.println(s.length());
//        System.out.println(l);
        System.out.println(check(s, 800));
//        System.out.println("code : " + hash(ss, 800));

        return res.getOrDefault(l - 1, "");
    }

    private boolean check(String s, int size){
        long code = hash(s, size);
        HashMap<Long, List<Integer>> map = new HashMap<>();
        put(map, code, 0);
        BigInteger bigInteger = new BigInteger("26");
        bigInteger = bigInteger.modPow(new BigInteger(String.valueOf(size - 1)),new BigInteger("1000000007"));
        mod = bigInteger.longValue();

        //System.out.println(mod);

        for (int i = 1; i + size <= s.length(); i++) {
            //System.out.println(code);
            if(code == 398283540){
                System.out.println("loc " + i);
            }
            if(i + size == s.length()){
                System.out.println(s.charAt(i));
                System.out.println(code);
            }
            code -= (s.charAt(i - 1) - 'a') * mod % MOD;
            if(code < 0){
                code += MOD;
            }
            code *= 26;
            code += s.charAt(i + size - 1) - 'a';
            code %= MOD;

            if(map.containsKey(code)){
                for(int start : map.get(code)){
                    if(equal(s, start, i, size)){
                        res.put(size, s.substring(start, start + size));
                        return true;
                    }
                }
            }

            put(map, code, i);
        }

        return false;
    }


    private boolean equal(String s, int loc1, int loc2, int size){
        for (int i = 0; i < size; i++) {
            if(s.charAt(loc1 + i) != s.charAt(loc2 + i)){
                return false;
            }
        }

        return true;
    }


    private void put(HashMap<Long, List<Integer>> map, long key, int val){
        if(map.containsKey(key)){
            map.get(key).add(val);
        }
        else{
            List<Integer> l = new ArrayList<>();
            l.add(val);
            map.put(key, l);
        }
    }

    private long hash(String s, int size){
        long code = 0;
        for (int i = 0; i < size; i++) {
            code *= 26;
            code += s.charAt(i) - 'a';
            code %= MOD;
        }


        return code;
    }
}
