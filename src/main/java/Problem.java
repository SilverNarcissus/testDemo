import java.util.Arrays;
import java.util.HashMap;

class Problem {

  HashMap<String, HashMap<Integer, Integer>> memo = new HashMap<>();

  public int getLengthOfOptimalCompression(String s, int k) {
    // System.out.println(s + " " + k);

    if(s.length() == k){
      return 0;
    }

    if(memo.containsKey(s) && memo.get(s).containsKey(k)){
      return memo.get(s).get(k);
    }


    int min = 10000;

    if(k != 0){
      char before = s.charAt(0);
      int count = 1;


      for(int i = 1; i < s.length(); i++){
        char cur = s.charAt(i);
        if(cur == before){
          count++;
        }
        else{
          int d = dis(count);
          if(d <= k){
            min = Math.min(min, getLengthOfOptimalCompression(s.substring(0, i - count)
                + build(before, count - d)
                + s.substring(i), k - d));
          }

          before = cur;
          count = 1;
        }
      }

      int d = dis(count);
      if(d <= k){
        min = Math.min(min, getLengthOfOptimalCompression(s.substring(0, s.length() - count)
            + build(before, count - d), k - d));
      }
    }



    if(min == 10000){
      min = encode(s);
    }

    memo.computeIfAbsent(s, (id) -> new HashMap<>()).put(k, min);

    return min;
  }

  private int encode(String s){
    char before = s.charAt(0);
    int count = 1;
    int res = 0;

    for(int i = 1; i < s.length(); i++){
      char cur = s.charAt(i);
      if(cur == before){
        count++;
      }
      else{
        res += len(count);

        before = cur;
        count = 1;
      }
    }

    res += len(count);

    return res;
  }

  private int len(int i){
    if(i == 1){
      return 1;
    }

    if(i < 10){
      return 2;
    }

    if(i < 100){
      return 3;
    }

    return 4;
  }

  private String build(char c, int count){
    char[] res = new char[count];
    Arrays.fill(res, c);

    return new String(res);
  }

  private int dis(int cur){
    if(cur == 1){
      return 1;
    }

    if(cur < 10){
      return cur - 1;
    }

    if(cur < 100){
      return cur - 9;
    }

    return cur - 99;
  }
}