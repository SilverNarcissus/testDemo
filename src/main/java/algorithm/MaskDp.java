class Solution {
    public int[] concatenatedDivisibility(int[] nums, int k) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] len = new int[1 << n];
        for(int i = 1; i < (1 << n); i++){
            for(int j = 0; j < n; j++){
                int mask = 1 << j;
                if((i & mask) > 0){
                    len[i] += len(nums[j]);
                }
            }
        }

        long[] p = new long[100];
        p[0] = 1; 
        for(int i = 1; i < 100; i++){
            p[i] = (10 * p[i - 1]) % k;
        }

        int[][][] dp = new int[1 << n][k][2];
        for(int i = 0; i < (1 << n); i++){
            for(int j = 0; j < k; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }

        dp[0][0][0] = 0;
        dp[0][0][1] = 0;

        // main dp
        for(int i = 1; i < (1 << n); i++){
            for(int j = 0; j < k; j++){
                for(int pos = 0; pos < n; pos++){
                    int mask = 1 << pos;
                    if((i & mask) > 0){
                        int nextKey = i - mask;
                        long now = (p[len[nextKey]] * nums[pos]) % k;
                        int nextRemainder = (int) ((j - now + k) % k);

                    
                        if(dp[nextKey][nextRemainder][0] != -1){
                            dp[i][j][0] = pos;
                            dp[i][j][1] = nextRemainder;
                            break;
                        } 
                    }
                }
            }
        }

        //System.out.println(Arrays.deepToString(dp));
        //System.out.println(Arrays.toString(nums));
        if(dp[(1 << n) - 1][0][0] == -1){
            return new int[0];
        }

        int[] res = new int[n];
        int index = 0;
        for(int i = (1 << n) - 1, remainder = 0; i > 0;){
            int loc = dp[i][remainder][0];
            //System.out.println(loc + " " + i);
            res[index++] = nums[loc];

            remainder = dp[i][remainder][1];
            i = i - (1 << loc);
        }

        return res;
    }

    private int len(int val){
        int res = 0;
        while(val > 0){
            res++;
            val /= 10;
        }

        return res;
    }
}
