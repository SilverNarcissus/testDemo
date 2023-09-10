class MatrixMultiplyAndPower {
    private static final int M = 1000000007;

    private int[][] matrixMultiply(int[][] a, int[][] b) {
        int m = a.length, n = a[0].length, p = b[0].length;
        int[][] r = new int[m][p];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < p; ++j) {
                for (int k = 0; k < n; ++k) {
                    r[i][j] = add(r[i][j], mul(a[i][k], b[k][j]));
                }
            }
        }
        return r;
    }

    private int[][] matrixPower(int[][] a, long y) {
        int n = a.length;
        int[][] r = new int[n][n];
        int[][] x = new int[n][n];
        for(int i = 0; i < n; i++){
            r[i][i] = 1;
            System.arraycopy(a[i], 0, x[i], 0, n);
        }

        while(y > 0){
            if((y & 1) > 0){
                r = matrixMultiply(x, r);
            }

            x = matrixMultiply(x, x);
            y >>= 1;
        }
        
        return r;
    }
 

    private int add(int x, int y) {
        if ((x += y) >= M) {
            x -= M;
        }
        return x;
    }

    private int mul(long x, long y) {
        return (int) (x * y % M);
    }
}
