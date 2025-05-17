class Solution {
    int mod = 1_000_000_007;

    public int colorTheGrid(int m, int n) {
        int maskLen = 1 << m << m;
        int[] posMask = {3, 3 << 2, 3 << 4, 3 << 6, 3 << 8, 3 << 10};
        List<Integer> valid = new ArrayList<>();

        OUTER:
        for(int i = 0; i < maskLen; i++){
            for(int j = 0; j < m - 1; j++){
                int cur = (i & posMask[j]) >> j >> j;
                int next = (i & posMask[j + 1]) >> (j + 1) >> (j + 1);

                if(cur == 3 || next == 3 || cur == next){
                    continue OUTER;
                }
            }

            int val = (i & posMask[m - 1]) >> (m - 1) >> (m - 1);
            if(val != 3){
                valid.add(i);
            }
        }


        List<Integer>[] before = new List[valid.size()];
        Arrays.setAll(before, i -> new ArrayList<>());
        int[][] mat = new int[valid.size()][valid.size()];

        for(int i = 0; i < valid.size(); i++){
            OUTER1:
            for(int j = 0; j < valid.size(); j++){
                for(int k = 0; k < m; k++){
                    if((valid.get(i) & posMask[k]) == (valid.get(j) & posMask[k])){
                        continue OUTER1;
                    }
                }

                mat[i][j] = 1;
            }
        }

        //System.out.println(Arrays.deepToString(mat));

        int[][] dp = new int[valid.size()][1];
        for(int i = 0; i < valid.size(); i++){
            dp[i][0] = 1;
        }

        int[][] pow = pow(mat, n - 1);
        //System.out.println(Arrays.deepToString(pow));

        int[][] res = mul(pow(mat, n - 1), dp);
        
        //System.out.println(Arrays.deepToString(res));

        int out = 0;
        for(int[] arr : res){
            out += arr[0];
            out %= mod;
        }

        return out;
    }

    private int[][] pow(int[][] mat, int up){
        int n = mat.length;
        int[][] res = new int[n][n];
        int[][] mul = mat;

        for(int i = 0; i < n; i++){
            res[i][i] = 1;
        }

        while(up > 0){
            if((up & 1) == 1){
                res = mul(res, mul);
            }

            mul = mul(mul, mul);
            up >>= 1;
        }

        return res;
    }

     public int[][] mul(int[][] matrixA, int[][] matrixB) {
        int m = matrixA.length;    
        int p = matrixB[0].length; 
        int n = matrixA[0].length;

        int[][] result = new int[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += (int)((1L * matrixA[i][k] * matrixB[k][j]) % mod);
                    result[i][j] %= mod;
                }
            }
        }
        return result;
    }
}
