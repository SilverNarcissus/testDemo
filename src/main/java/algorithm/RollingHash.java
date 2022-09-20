import java.util.*;

public class RollingHash {
    int P = 113;
    int MOD = 1_000_000_007;

    private int[] rolling(int[] source, int length) {
        int[] ans = new int[source.length - length + 1];
        long h = 0, power = 1;
        if (length == 0) return ans;
        for(int i = 0; i < length - 1; i++){
            power *= P;
            power %= MOD;
        }
        for (int i = 0; i < source.length; ++i) {
            h *= P;
            h = (h + source[i]) % MOD;
            if (i >= length - 1) {
                ans[i - (length - 1)] = (int) h;
                h = (h - source[i - (length - 1)] * power) % MOD;
                if (h < 0) h += MOD;
            }
        }
        return ans;
    }

    private boolean check(int guess, int[] A, int[] B) {
        Map<Integer, List<Integer>> hashes = new HashMap();
        int k = 0;
        for (int x: rolling(A, guess)) {
            hashes.computeIfAbsent(x, z -> new ArrayList()).add(k++);
        }
        int j = 0;
        for (int x: rolling(B, guess)) {
            for (int i: hashes.getOrDefault(x, new ArrayList<Integer>()))
                if (Arrays.equals(Arrays.copyOfRange(A, i, i+guess),
                                  Arrays.copyOfRange(B, j, j+guess))) {
                    return true;
                }
            j++;
        }
        return false;
    }

    public int findLength(int[] A, int[] B) {
        int lo = 0, hi = Math.min(A.length, B.length) + 1;
        while (lo < hi) {
            int mi = (lo + hi) / 2;
            if (check(mi, A, B)) lo = mi + 1;
            else hi = mi;
        }
        return lo - 1;
    }
}
