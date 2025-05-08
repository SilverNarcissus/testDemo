class Solution {
    int n;
    int mod = 1_000_000_007;
    int[] nums;
    long[][][][] dp;

    long[] fac;
    long[] ifac; 
    
    public int magicalSum(int M, int K, int[] nums) {
        n = nums.length;
        this.nums = nums;

       

        // 预处理阶乘
        fac = new long[51];
        fac[0] = 1;
        for (int i = 1; i <= 50; i++) fac[i] = fac[i - 1] * i % mod;
        // 预处理阶乘的逆元
        ifac = new long[51];
        ifac[0] = ifac[1] = 1;
        for (int i = 2; i <= 50; i++) ifac[i] = (mod - mod / i) * ifac[mod % i] % mod;
        for (int i = 2; i <= 50; i++) ifac[i] = ifac[i - 1] * ifac[i] % mod;

        //System.out.println(c(3, 1));
        //System.out.println(c(5, 2));
        
        dp = new long[n][M + 1][K + 1][32];
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= M; j++){
                for(int k = 0; k <= K; k++){
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }

        long res = dfs(0, M, K, 0);

        return (int) res;
    }

    private long dfs(int loc, int len, int remain, int before){
        //System.out.println(loc + " " + len + " " + remain + " " + before);
        
        if(len < 0){
            return 0;
        }

        if(remain < 0){
            return 0;
        }
        
        if(loc >= n){
            if(len > 0){
                return 0;
            }
            
            int count = Integer.bitCount(before);
            return count == remain ? 1 : 0;
        }

        if(dp[loc][len][remain][before] == -1){
            long res = 0;
            long val = 1;
            int cur = before;
            
            res = dfs(loc + 1, len, remain - (cur & 1), cur >> 1);

            for(int i = 0; i < len; i++){
                val *= nums[loc];
                val %= mod;
                cur++;
                
                long add1 = val * dfs(loc + 1, len - i - 1, remain - (cur & 1), cur >> 1);
                add1 %= mod;
                
                long type = 1;
                int box = len - i;
                int dup = i + 1;

                long mul = c(box + dup - 1, dup);

                add1 *= mul;
                add1 %= mod;

                res += add1;
                res %= mod;
            }

            dp[loc][len][remain][before] = res;
        }

        //System.out.println(loc + " " + len + " " + remain + " " + before + " " + dp[loc][len][remain][before]); 

        return dp[loc][len][remain][before];
    }

    private long c(int n, int m){
        long up = fac[n];
        long down = (ifac[m] * ifac[n - m]) % mod;

        return (up * down) % mod;
    }
}
