class Solution {
    int k;
    int n;
    char[] res;
    int[] maxPrime;

    HashMap<Integer, Integer> map = new HashMap<>();
    int maxP = 20;
    int INF = 1_000_000_007;
    
    public String smallestPalindrome(String s, int k) {
        int m = s.length();

        int[] count = new int[26];

        for(char c : s.toCharArray()){
            count[c - 'a']++;
        }
      
        maxPrime = new int[m + 1];
        for(int i = 2; i <= m; i++){
            if(maxPrime[i] == 0){
                for(int j = i; j <= m; j += i){
                    maxPrime[j] = i;
                }
            }
        }

        // 1 * 2 * 3 ... * m
        for(int i = 1; i <= m; i++){
            mul(i, 1);
        }

        // divide by frac[count[0]], frac[count[1]].....
        for(int i = 0; i < 26; i++){
            for(int j = 1; j <= curCount[i]; j++){
                mul(j, -1);
            }
        }

        //System.out.println(map);
        //System.out.println(Arrays.toString(curCount));

        if(getVal() < k){
            return "";
        }

        for(int i = 0; i < m; i++){
            mul(m - i, -1);

            for(int j = 0; j < 26; j++){
                if(curCount[j] > 0){
                    mul(count[j], 1);

                    int cur = getVal();

                    //System.out.println(cur + " " + map);

                    if(cur >= k){
                        char c = (char)('a' + j);
                        //System.out.println("put: " + c + " at : " + i);
                        res[i] = c;
                        res[n - i - 1] = c;
                        curCount[j]--;
                        break;
                    }

                    k -= cur;
                    mul(count[j], -1);
                }
            }
        }

        return new String(res);
    } 

    private void mul(int val, int up){
        while(val > 1){
            int cur = maxPrime[val];
            val /= maxPrime[val];
            int next = map.getOrDefault(cur, 0);
            next += up;

            if(next == 0){
                map.remove(cur);
            }
            else{
                map.put(cur, next);
            }
        }
    }

    private int getVal(){
        if(map.size() > maxP){
            return INF;
        }

        long res = 1;
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            for(int i = 0; i < e.getValue(); i++){
                res *= e.getKey();

                if(res > INF){
                    return INF;
                }
            }
        }

        return (int)res;
    }
}
