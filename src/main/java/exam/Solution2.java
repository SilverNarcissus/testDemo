package exam;

import java.util.*;

/**
 * Created by SilverNarcissus on 2019/4/10.
 */
public class Solution2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();

        System.out.println(Solution2.recursive(s, 0, s.length()));

    }

    public static String recursive(String s, int loc, int end) {
        StringBuilder sb = new StringBuilder();
        while (loc < end) {
            char cur = s.charAt(loc++);
            if (Character.isDigit(cur)) {
                int num = cur - '0';
                while (loc < end) {
                    char n = s.charAt(loc++);
                    if (Character.isDigit(n)) {
                        num = num * 10 + n - '0';
                    } else {
                        //System.out.println(pair);
                        //System.out.println(loc);
                        int nextEnd = findNext(s, loc, n, makePair(n));
                        String inner = recursive(s, loc, nextEnd);
                        for (int i = 0; i < num; i++) {
                            sb.insert(0, inner);
                        }
                        loc = nextEnd + 1;
                        break;
                    }
                }
            } else {
                sb.insert(0, cur);
            }
        }

        sb.reverse();
        return sb.toString();
    }

    private static char makePair(char c){
        return c == '(' ? ')' : c == '[' ? ']' : '}';
    }

    private static int findNext(String s, int loc, char front, char end){
        int count = 1;
        for (int i = loc; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(cur == front){
                count++;
            }
            else if(cur == end){
                count--;
                if(count == 0){
                    return i;
                }
            }
        }

        return -1;
    }
}
