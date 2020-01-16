package exam;


import java.util.*;

/**
 * Created by SilverNarcissus on 2019/4/10.
 */
public class Solution1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<String> result = new ArrayList<>();

        for(int i = 0; i < n; i++){
            String s= sc.next();
            StringBuilder sb = new StringBuilder();
            int loc = 0;
            while(loc < s.length()){
                for (int j = 0; j < 8; j++) {
                    if(loc < s.length()){
                        sb.append(s.charAt(loc));
                        loc++;
                    }
                    else{
                        sb.append('0');
                    }
                }
                result.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
        Collections.sort(result);
        for(String s : result){
            System.out.print(s);
        }
    }
}
