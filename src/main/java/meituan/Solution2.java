package meituan;

import java.util.Scanner;

public class Solution2 {
  static String t;
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String s = in.next();

    int[] len = longestPalindrome(s);
    //System.out.println(t);
    //System.out.println(Arrays.toString(len));
    //System.out.println(len.length);
    //System.out.println(t.length());
    int start = 0;
    for (int i = 0; i < t.length(); i++) {
      if(i + len[i] == t.length() - 1){
        start = i - len[i];
        break;
      }
    }

    // System.out.println(start);
    int res = 0;
    for (int i = 1; i <= start; i++) {
      if(t.charAt(i) != '#'){
        res++;
      }
    }

    System.out.println(res);
  }

  private static int[] longestPalindrome(String s) {
    int mi = 0;
    int right = 0;
    int maxlength = 0;
    int maxpoint = 0;
    StringBuilder temp = new StringBuilder("@#");
    for(int i = 0; i < s.length(); i++){
      temp.append(s.charAt(i));
      temp.append("#");
    }
    temp.append("*");
    t = temp.toString();
    int[] p = new int[temp.length()];
    for(int i = 0; i < temp.length(); i++){
      p[i] = 0;
    }
    for(int i = 1; i < temp.length()-1; i++){
      p[i] = right > i? Math.min(p[2*mi-i], right - i) : 1;
      while(temp.charAt(i+p[i]) == temp.charAt(i-p[i])){
        p[i]++;
      }
      if(i + p[i] > right){
        right = i + p[i];
        mi = i;
      }
      if(maxlength < p[i]){
        maxlength = p[i];
        maxpoint = i;
      }
    }

    return p;
  }

  private static boolean check(String s, int start, int end){
    while(start <= end){
      if(s.charAt(start) != s.charAt(end)){
        return false;
      }
      start++;
      end--;
    }
    return true;
  }
}
