class Solution {
    int[][] parent;
    int[] depth;
    int mod = 1_000_000_007;
    List<Integer>[] map;
    
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 2;
        parent = new int[n][18];
        depth = new int[n];
        
        map = new List[n];
        Arrays.setAll(map, i -> new ArrayList<>());
        for(int[] edge : edges){
            map[edge[0]].add(edge[1]);
            map[edge[1]].add(edge[0]);
        }

        dfs(1, 1, 0);

        for(int i = 1; i <= 17; i++){
            for(int j = 0; j < n; j++){
                parent[j][i] = parent[parent[j][i - 1]][i - 1];
            }
        }

        // System.out.println(Arrays.deepToString(parent));
        // System.out.println(move(6,1));
        // System.out.println(move(6,2));
        // System.out.println(move(6,3));
        // System.out.println(move(6,4));

        int m = queries.length;
        int[] res = new int[m];

        for(int i = 0; i < m; i++){
            int[] query = queries[i];

            int a = query[0];
            int b = query[1];
            int lca = findLCA(a, b);

            int len = depth[a] + depth[b] - (depth[lca] << 1);

            res[i] = cal(len);
        }

        return res;
    }

    private int findLCA(int a, int b){
        int d1 = depth[a];
        int d2 = depth[b];

        if(d1 > d2){
            int temp = a;
            a = b;
            b = temp;

            temp = d1;
            d1 = d2;
            d2 = temp;
        }

        b = move(b, d2 - d1);

        if(a == b){
            return a;
        }

        for(int i = 17; i >= 0; i--){
            int r1 = parent[a][i];
            int r2 = parent[b][i];
            if(r1 != r2){
                a = r1;
                b = r2;
            }
        }

        return parent[a][0];
    }

    private int move(int loc, int step){
        int cur = loc;
        for(int i = 17; i >= 0; i--){
            int mask = 1 << i;
            if((mask & step) > 0){
                cur = parent[cur][i];
            }
        }

        return cur;
    }



    private void dfs(int cur, int p, int level){
        parent[cur][0] = p;
        depth[cur] = level;

        for(int child : map[cur]){
            if(child == p){
                continue;
            }

            dfs(child, cur, level + 1);
        }
    }

    private int cal(int len){
        if(len == 0){
            return 0;
        }
        
        return pow(2, len - 1);
    }

    private int pow(int base, int up){
        long res = 1;
        long mul = base;

        while(up > 0){
            if((up & 1) == 1){
                res *= mul;
                res %= mod;
            }

            mul *= mul;
            mul %= mod;
            up >>= 1;
        }

        return (int) res;
    }
}
