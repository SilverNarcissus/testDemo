package algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int N = s.nextInt();
        N /= 10;
        int m = s.nextInt();
        ArrayList<Integer>[] weight = new ArrayList[m + 1];
        ArrayList<Integer>[] val = new ArrayList[m + 1];

        for(int i = 1; i <= m; i++){
            int v = s.nextInt();
            v /= 10;
            int important = s.nextInt();
            int major = s.nextInt();

            if(major == 0){
                ArrayList<Integer> l = new ArrayList();
                l.add(v);
                ArrayList<Integer> l2 = new ArrayList();
                l2.add(v * important);

                weight[i] = l;
                val[i] = l2;
            }
            else{
                weight[major].add(v);
                val[major].add(v * important);
            }
        }

        for(int i = 1; i <= m; i++){
            if(weight[i] != null && weight[i].size() == 3){
                weight[i].add(weight[i].get(1) + weight[i].get(2));
                val[i].add(val[i].get(1) + val[i].get(2));
            }
        }


        int[] dp = new int[N + 1];
        for(int i = 1; i <= m; i++){
            if(weight[i] != null){
                for(int j = N; j > 0; j--){
                    int w = weight[i].get(0);
                    if(j - w >= 0){
                        dp[j] = Math.max(dp[j], dp[j - w] + val[i].get(0));
                    }

                    for(int k = 1; k < weight[i].size(); k++){
                        w =  weight[i].get(k) + weight[i].get(0);
                        if(j - w > 0){
                            dp[j] = Math.max(dp[j],
                                    dp[j - w] + val[i].get(0) + val[i].get(k));
                        }
                    }
                }
            }
        }
        System.out.println(dp[N + 1] * 10);
    }
}