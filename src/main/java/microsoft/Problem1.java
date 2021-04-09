package microsoft;

public class Problem1 {

  public int solution(int[] A) {
    int min = Integer.MAX_VALUE;

    for(int i = 1; i <= 4; i++){
      int sum = 0;
      for(int n : A){
        sum += Math.abs(n - i);
      }
      min = Math.min(min, sum);
    }

    return min;
  }
}
