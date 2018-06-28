package algorithm;

import java.util.Arrays;

/**
 * Created by SilverNarcissus on 2018/4/23.
 */
public class SuffixArray {
    private static final int MAX = 10000;
    private int[] sa;
    private int[] rank;
    private int[] height;

    public SuffixArray(String str) {
        buildSuffixArray(str);

        build_height(str);

        for (int i : height) {
            System.out.print(i + " ");
        }
    }

    public static void main(String[] args) {
        SuffixArray suffixArray = new SuffixArray("ababa");
    }

    private void buildSuffixArray(String str) {
        System.out.println(str.length());
        sa = new int[str.length()];
        rank = new int[str.length() << 1];
        int[] count = new int[MAX];

        for (int i = 0; i < str.length(); i++) {
            rank[i] = str.charAt(i);
            count[rank[i]]++;
        }

        for (int i = 1; i < MAX; i++) {
            count[i] += count[i - 1];
        }

        for (int i = str.length() - 1; i >= 0; i--) {
//            System.out.print(rank[i]);
            sa[--count[rank[i]]] = i;
        }

        int[] y = new int[str.length() << 1];
        int max = MAX;
        for (int k = 1; k <= str.length(); k <<= 1) {
            int pointer = 0;

            // sort for second key
            for (int i = str.length() - k; i < str.length(); i++) {
                y[pointer++] = i;
            }

            for (int i = 0; i < str.length(); i++) {
                if (sa[i] >= k) {
                    y[pointer++] = sa[i] - k;
                }
            }

            // sort for 2 keys
            Arrays.fill(count, 0);

            for (int i = 0; i < str.length(); i++) {
                count[rank[y[i]]]++;
            }

            for (int i = 1; i < max; i++) {
                count[i] += count[i - 1];
            }

            for (int i = str.length() - 1; i >= 0; i--) {
                sa[--count[rank[y[i]]]] = y[i];
            }

            //swap rank, y
            int[] temp = rank;
            rank = y;
            y = temp;

            int p = 1;
            rank[sa[0]] = 0;
            for (int i = 1; i < str.length(); i++) {
                if (y[sa[i - 1]] == y[sa[i]] && y[sa[i - 1] + k] == y[sa[i] + k]) {
                    rank[sa[i]] = p - 1;
                } else {
                    rank[sa[i]] = p++;
                }
            }

            if (p >= str.length()) {
                return;
            }

            max = p;
        }

    }


    private void build_height(String str) {
        int i, j, k = 0;
        height = new int[str.length()];
        for (i = 0; i < sa.length; i++) {
            if (rank[i] == 0) continue;
            if (k != 0) k--;
            j = sa[rank[i] - 1];
            while (j + k < sa.length && i + k < sa.length && str.charAt(j + k) == str.charAt(i + k)) k++;
            height[rank[i]] = k;
        }
    }
}
