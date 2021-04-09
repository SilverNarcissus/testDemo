package microsoft;

public class Problem3 {

  public static void main(String[] args) {
    int[][] res = {{6, 25, 4, 10}, {12, 25, 1, 15}, {7, 15, 15, 5}};
    Problem3 problem3 = new Problem3();

    System.out.println(problem3.solution(res));
  }

  public int solution(int[][] A) {
    // write your code in Java SE 8
    int n = A.length;
    int m = A[0].length;

    int[][] two = new int[n][m];
    int[][] five = new int[n][m];

    // count five and two
    for(int i = 0; i < n; i++){
      for (int j = 0; j < m; j++) {
        int count = 0;
        int val = A[i][j];
        while(val != 0 && (val & 1) != 1){
          count++;
          val >>= 1;
        }
        two[i][j] = count;

        count = 0;
        val = A[i][j];
        while(val != 0 && (val % 5 == 0)){
          count++;
          val /= 5;
        }
        five[i][j] = count;
      }
    }

    // prefix sum
    int[][][] prefix2 = new int[n][m][4];
    int[][][] prefix5 = new int[n][m][4];

    for(int j = 0; j < m; j++){
      int sumOf2 = 0;
      int sumOf5 = 0;
      for(int i = 0; i < n; i++){
        sumOf2 += two[i][j];
        sumOf5 += five[i][j];
        prefix2[i][j][0] = sumOf2;
        prefix5[i][j][0] = sumOf5;
      }

      sumOf2 = 0;
      sumOf5 = 0;
      for(int i = n - 1; i >= 0; i--){
        sumOf2 += two[i][j];
        sumOf5 += five[i][j];
        prefix2[i][j][1] = sumOf2;
        prefix5[i][j][1] = sumOf5;
      }
    }

    for(int i = 0; i < n; i++){
      int sumOf2 = 0;
      int sumOf5 = 0;
      for(int j = 0; j < m; j++){
        sumOf2 += two[i][j];
        sumOf5 += five[i][j];
        prefix2[i][j][2] = sumOf2;
        prefix5[i][j][2] = sumOf5;
      }

      sumOf2 = 0;
      sumOf5 = 0;
      for(int j = m - 1; j >= 0; j--){
        sumOf2 += two[i][j];
        sumOf5 += five[i][j];
        prefix2[i][j][3] = sumOf2;
        prefix5[i][j][3] = sumOf5;
      }
    }

    int res = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int up2 = prefix2[i][j][0];
        int down2 = prefix2[i][j][1];
        int left2 = prefix2[i][j][2];
        int right2 = prefix2[i][j][3];

        int up5 = prefix5[i][j][0];
        int down5 = prefix5[i][j][1];
        int left5 = prefix5[i][j][2];
        int right5 = prefix5[i][j][3];

        int num2 = two[i][j];
        int num5 = five[i][j];

        res = Math.max(res, Math.min(up2 + left2 - num2, up5 + left5 - num5));
        res = Math.max(res, Math.min(up2 + right2 - num2, up5 + right5 - num5));
        res = Math.max(res, Math.min(down2 + left2 - num2, down5 + left5 - num5));
        res = Math.max(res, Math.min(down2 + right2 - num2, down5 + right5 - num5));
      }
    }

    return res;
  }


}
