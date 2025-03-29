class Solution {
    List<Integer> prime;
    int mod = 1_000_000_007;

    public int monoStack(List<Integer> nums, int k) {
        int[] left = new int[n];
        Arrays.fill(left, -1);

        Stack<Integer> locs = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!locs.isEmpty() && score[locs.peek()] < score[i]){
                locs.pop();
            }

            if(!locs.isEmpty()){
                left[i] = locs.peek();
            }

            locs.push(i);
        }

        //System.out.println(Arrays.toString(left));

        int[] right = new int[n];
        locs = new Stack<>();
        Arrays.fill(right, n);

        for(int i = n - 1; i >= 0; i--){
            while(!locs.isEmpty() && score[locs.peek()] <= score[i]){
                locs.pop();
            }

            if(!locs.isEmpty()){
                right[i] = locs.peek();
            }

            locs.push(i);
        }

        //System.out.println(Arrays.toString(right));


        PriorityQueue<long[]> queue = new PriorityQueue<>((a, b) -> -Long.compare(a[0], b[0]));
        for(int i = 0; i < n; i++){
            int l = left[i];
            int r = right[i];

            int lLen = i - l;
            int rLen = r - i;

            long count = 1L * lLen * rLen;
            long[] cur = new long[]{nums.get(i), count};

            queue.add(cur);
        }
      
        return (int) res;
    }

    private List<Integer> prime(){
        boolean[] record = new boolean[400];
        List<Integer> res = new ArrayList<>();

        for(int i = 2; i < 400; i++){
            if(!record[i]){
                res.add(i);
            }

            int cur = i;
            while(cur < 400){
                record[cur] = true;
                cur += i;
            }
        }

        return res;
    }

    private long pow(long base, int up){
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

        return res;
    }
}
