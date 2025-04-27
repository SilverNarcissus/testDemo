class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++){
            nodes[i] = new Node(i, nums[i], 0);
        }

        Arrays.sort(nodes, (a, b) -> Integer.compare(a.val, b.val));

        int[] map = new int[n];
        for(int i = 0; i < n; i++){
            map[nodes[i].id] = i;
            if(i != 0){
                nodes[i].group = nodes[i - 1].group + (nodes[i].val - nodes[i - 1].val > maxDiff ? 1 : 0);
            }
        }

        // Bit pow 2 dp
        int[][] dis = new int[n][18];
        int r = 0;
        for(int i = 0; i < n; i++){
            while(r + 1 < n && nodes[r + 1].val - nodes[i].val <= maxDiff){
                r++;
            }

            dis[i][0] = r;
        }

        for(int i = 1; i <= 17; i++){
            for(int j = 0; j < n; j++){
                dis[j][i] = dis[dis[j][i - 1]][i - 1];
            }
        }
        // end of bit pow 2 dp

        int m = queries.length;
        int[] res = new int[m];
        for(int i = 0; i < m; i++){
            int l = map[queries[i][0]];
            r = map[queries[i][1]];

            if(l == r){
                res[i] = 0;
            }
            else if(nodes[l].group != nodes[r].group){
                res[i] = -1;
            }
            else{
                int jump = 0;
                if(l > r){
                    int temp = l;
                    l = r;
                    r = temp;
                }

                // usage of bit pow2 dp
                for(int j = 17; j >= 0; j--){
                    if(dis[l][j] < r){
                        jump += (1 << j);
                        l = dis[l][j];
                    }
                }

                res[i] = jump + 1;
            }
        }

        return res;
    }
}

class Node{
    int id;
    int val;
    int group;

    public Node(int id, int val, int group){
        this.id = id;
        this.val = val;
        this.group = group;
    }
}
