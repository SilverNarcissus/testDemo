package algorithm;

import java.util.Arrays;

/**
 * Created by SilverNarcissus on 2019/7/8.
 * Given strings S and T,
 * find the minimum (contiguous) substring W of S,
 * so that T is a subsequence of W.
 *
 If there is no such window in S that covers all characters in T,
 return the empty string "".
 If there are multiple such minimum-length windows,
 return the one with the left-most starting index.

 Example 1:

 Input:
 S = "abcdebdde", T = "bde"
 Output: "bcde"
 Explanation:
 "bcde" is the answer because it occurs before "bdde" which has the same length.
 "deb" is not a smaller window because the elements of T in the window must occur in order.

 Note:
 All the strings in the input will only contain lowercase letters.
 The length of S will be in the range [1, 20000].
 The length of T will be in the range [1, 100].
 */
public class MinimumWindowSubsequence {
    public static void main(String[] args) {
        MinimumWindowSubsequence minimumWindowSubsequence = new MinimumWindowSubsequence();
        System.out.println(minimumWindowSubsequence.handle("abcdebddebde", "bde"));
    }

    public String handle(String s, String t){
        int n = t.length();
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        int min = Integer.MAX_VALUE;
        int start = -1;

        for(int i = 0; i < s.length(); i++){
            dp[0] = i;
            int[] temp = new int[n + 1];
            char cur = s.charAt(i);
            for(int j = 0; j < n; j++){
                if(cur == t.charAt(j)){
                    temp[j + 1] = dp[j];
                }
                else{
                    temp[j + 1] = dp[j + 1];
                }
            }

            dp = temp;
            System.out.println(Arrays.toString(dp));
            if(dp[n] != -1){
                int len = i - dp[n];
                if(len < min){
                    min = len;
                    start = dp[n];
                }
            }
        }

        return start != -1 ? s.substring(start, start + min + 1) : "";
    }
}
