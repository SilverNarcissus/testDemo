package algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by SilverNarcissus on 2019/3/23.
 */
public class BooleanEvaluation {
    public static void main(String[] args) {
        BooleanEvaluation booleanEvaluation = new BooleanEvaluation();
        System.out.println(booleanEvaluation.count("0&0&0&1^1|0", true));
    }

    private int count(String s, boolean result){
        int n = (s.length() + 1) >> 1;
        int[][][] dp = new int[n][n][3];
        int[][] d = new int[2][2];
        build(s, 0, n - 1,dp);

        return result ? dp[0][n - 1][1] : dp[0][n - 1][0];
    }

    private void build(String s, int l, int r, int[][][] dp){
        if(l > r){
            return;
        }
        if(dp[l][r][2] != 0){
            return;
        }
        if(l == r){
            if(s.charAt(l << 1) == '0'){
                dp[l][r][0] = 1;
            }
            else{
                dp[l][r][1] = 1;
            }
            dp[l][r][2] = 1;
            return;
        }

        int trueCount = 0;
        int falseCount = 0;
        for(int i = l; i < r; i++){
            build(s, l, i, dp);
            build(s, i + 1, r, dp);
            switch (s.charAt((i << 1) + 1)) {
                case '&':
                    trueCount += dp[l][i][1] * dp[i + 1][r][1];
                    falseCount += dp[l][i][0] * dp[i + 1][r][1] + dp[l][i][1] * dp[i + 1][r][0] + dp[l][i][0] * dp[i + 1][r][0];
                    break;
                case '|':
                    trueCount += dp[l][i][0] * dp[i + 1][r][1] + dp[l][i][1] * dp[i + 1][r][0] + dp[l][i][1] * dp[i + 1][r][1];
                    falseCount += dp[l][i][0] * dp[i + 1][r][0];
                    break;
                case '^':
                    trueCount += dp[l][i][0] * dp[i + 1][r][1] + dp[l][i][1] * dp[i + 1][r][0];
                    falseCount += dp[l][i][0] * dp[i + 1][r][0] + dp[l][i][1] * dp[i + 1][r][1];
                    break;
            }
        }

        dp[l][r][0] = falseCount;
        dp[l][r][1] = trueCount;
        dp[l][r][2] = 1;
    }
}
