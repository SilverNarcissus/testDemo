class Solution {
    HashMap<Long, Integer> dp = new HashMap<>();
    
    public int beautifulNumbers(int l, int r) {
        //System.out.println(count(100));
        //System.out.println(count(19));
        
        return count(r) - count(l - 1);
    }

    private int count(int val){
        List<Integer> list = new ArrayList<>();
        //dp = new HashMap<>();
        while(val > 0){
            list.add(val % 10);
            val /= 10;
        }

        int[] arr = new int[list.size()];
        int loc = 0;
        for(int i : list){
            arr[loc++] = i;
        }

        return dfs(arr, arr.length - 1, 1, 0, true);
    }
    

    private int dfs(int[] arr, int loc, int p, int sum, boolean isFull){
        if(loc < 0){
            if(sum == 0){
                return 0;
            }

            return p % sum == 0 ? 1 : 0;
        }

        
        long key = toKey(loc, p, sum);

        if(!isFull && dp.containsKey(key)){
            return dp.get(key);
        }

        int res = 0;
        int end = isFull ? arr[loc] : 9;
        for(int i = 0; i <= end; i++){
            int nextP = p * i;
            int nextSum = sum + i;

            if(sum == 0 && i == 0){
                nextP = p;
            }

            res += dfs(arr, loc - 1, nextP, nextSum, isFull && (i == end));
        }

        if(!isFull){
            dp.put(key, res);
        }

        return res;
    }


    private long toKey(int loc, int p, int sum){
        long key = loc;
        key = 1_000_000_0000L * key + p;
        key *= 100;
        key += sum;

        return key;
    }
}
