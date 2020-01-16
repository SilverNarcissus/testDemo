package algorithm;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by SilverNarcissus on 2019/3/17.
 * Given a positive integer N,
 * return the number of positive integers less than or equal to N that have at least 1 repeated digit.
 */
public class NumbersWith1RepeatedDigit {
    public static void main(String[] args) {
        Node html = new Node("html");
        Node div1 = new Node("div1");
        Node div2 = new Node("div2");
        html.n = new Node[2];
        html.n[0] = div1;
        html.n[1] = div2;
        NumbersWith1RepeatedDigit n = new NumbersWith1RepeatedDigit();
        n.postOrder(html);




        //
        //System.out.println(n.bruteForce(1000000000));
        //System.out.println(n.buildAndCheck(1, 100));
        //System.out.println(n.numDupDigitsAtMostN(1000000000));
    }

    private int bruteForce(int N) {
        int result = 0;
        for (int i = 11; i <= N; i++) {
            if (check(i)) {
                result++;
            }
        }

        return result;
    }

    public int numDupDigitsAtMostN(int N) {
        int bit = countBit(N);
        if (bit == 10) {
            return 1 + numDupDigitsAtMostN(N - 1);
        }

        return N - buildUnrepeatable(bit - 1) - buildAndCheck(bit, N);
    }

    private int countBit(int N) {
        int result = 0;
        while (N != 0) {
            N /= 10;
            result++;
        }

        return result;
    }

    private int buildUnrepeatable(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 9;
        }
        int result = 9;
        for (int i = 2; i <= n; i++) {
            result += (A(10, i) - A(9, i - 1));
        }

        return result;
    }

    private int A(int down, int up) {
        int result = 1;
        for (int i = 0; i < up; i++) {
            result *= down;
            down--;
        }

        return result;
    }

    private int buildAndCheck(int bit, int limit) {
        return recursive(new boolean[10], bit, 0, limit);
    }

    private int recursive(boolean[] dp, int loc, int cur, int limit) {
        if (loc <= 0) {
            return cur <= limit ? 1 : 0;
        }

        int result = 0;
        for (int i = 0; i <= 9; i++) {
            if ((cur != 0 || i != 0) && !dp[i]) {
                dp[i] = true;
                result += recursive(dp, loc - 1, cur * 10 + i, limit);
                dp[i] = false;
            }
        }

        return result;
    }

    private boolean check(int cur) {
        int[] dp = new int[10];
        while (cur > 0) {
            int remind = cur % 10;
            if (dp[remind] != 0) {
                return true;
            }
            dp[remind]++;
            cur = cur /= 10;
        }

        return false;
    }

    private void postOrder(Node root) {
        Deque<Node> s = new LinkedList<>();
        Stack<Boolean> b = new Stack<>();
        Stack<Integer> i = new Stack<>();
        s.push(root);
        b.push(true);
        i.push(0);

        while (!s.isEmpty()) {
            Node cur = s.pop();
            boolean flag = b.pop();
            int level = i.pop();
            if (flag) {
                //<html>
                System.out.println(buildAlign(level) + "<" + cur.val + ">");

                s.push(cur);
                b.push(false);
                i.push(level);
                if(cur.n != null) {
                    for (Node n : cur.n) {
                        s.push(n);
                        b.push(true);
                        i.push(level + 1);
                    }
                }

            } else {
                //</html>
                System.out.println(buildAlign(level) + "</" + cur.val + ">");
            }
        }
    }

    private String buildAlign(int level){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append("  ");
        }

        return sb.toString();
    }

    private static class Node {
        public Node(String val) {
            this.val = val;
        }

        Node[] n;
        String val;
    }
}
