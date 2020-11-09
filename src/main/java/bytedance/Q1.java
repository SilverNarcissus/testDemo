package bytedance;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Q1 {

  private static int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    String read = in.next();
    HashMap<String, Integer> count = new HashMap<>();
    String pattern = "(\\d\\d-\\d\\d-\\d\\d\\d\\d)";

    Pattern patten = Pattern.compile(pattern);
    Matcher matcher = patten.matcher(read);

    int loc = 0;
    while (matcher.find(loc)) { //此处find（）每次被调用后，会偏移到下一个匹配
      String cur = matcher.group();
      loc = matcher.end() - 2;
      if(check(cur)){
        count.put(cur, count.getOrDefault(cur, 0) + 1);
      }
    }

    int max = 0;
    String res = null;
    for(String key : count.keySet()){
      if(count.get(key) > max){
        max = count.get(key);
        res = key;
      }
    }

    System.out.println(res);
  }

  private static boolean check(String date) {
    String[] part = date.split("-");
    int year = Integer.parseInt(part[2]);
    if (year > 2020 || year < 2001) {
      return false;
    }

    int month = Integer.parseInt(part[1]);
    if (month > 12 || month < 1) {
      return false;
    }

    int day = Integer.parseInt(part[0]);
    if (day < 1) {
      return false;
    }

    if (month == 2) {
      if (isLeap(year) && day > 29) {
        return false;
      }

      if (!isLeap(year) && day > 28) {
        return false;
      }
    }

    return day <= days[month - 1];
  }

  private static boolean isLeap(int year) {
    return year % 100 == 0 ? year % 400 == 0 : year % 4 == 0;
  }
}
