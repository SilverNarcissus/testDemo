package algorithm;

import java.util.*;

/**
 * Created by SilverNarcissus on 2019/4/8.
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 */
public class ExpressionAddOperators {
    Stack<Character> symbol = new Stack<>();
    Stack<Long> number = new Stack<>();
    boolean canCompute = true;
    char[] s = {'+', '-', '*'};

    public static void main(String[] args) {
        ExpressionAddOperators expressionAddOperators = new ExpressionAddOperators();
        StringBuilder sb = new StringBuilder("1*2*3*4*5-6+78-9");
        String[] s1 = {"1+2+3+4+5+6+7+8+9", "1+2+3+4+5-6*7+8*9", "1+2+3+4-5*6+7*8+9", "1+2+3+4-5*6-7+8*9", "1+2+3-4*5+6*7+8+9", "1+2+3-4*5-6+7*8+9", "1+2+3-4*5-6-7+8*9", "1+2+3-45+67+8+9", "1+2+3*4+5+6*7-8-9", "1+2+3*4*5+6-7-8-9", "1+2+3*45-6-78-9", "1+2+34-56/7/8+9", "1+2-3+4*5+6*7-8-9", "1+2-3+45+6-7-8+9", "1+2-3+45-6+7+8-9", "1+2-3-4-5*6+7+8*9", "1+2-3-45-6+7+89", "1+2-3*4+5*6+7+8+9", "1+2-3*4-5+6*7+8+9", "1+2-3*4-5-6+7*8+9", "1+2-3*4-5-6-7+8*9", "1+2-3*4*5+6+7+89", "1+2-34+5+6+7*8+9", "1+2-34+5+6-7+8*9", "1+2-34-5-6+78+9", "1+2*3+4*5-6+7+8+9", "1+2*3+4*5*6+7-89", "1+2*3-4-5-6*7+89", "1+2*3*4+5*6+7-8-9", "1+2*3*4-5+6*7-8-9", "1+2*3*4*5+6+7-89", "1+2*3*4*5/6+7+8+9", "1+2*34-5*6+7+8-9", "1+23+4+5+6+7+8-9", "1+23+4-5-67+89", "1+23+4-5*6+7*8-9", "1+23-4+56/7+8+9", "1+23-4-5+6+7+8+9", "1+23-4-5-6*7+8*9", "1+23-4*5+6*7+8-9", "1+23-4*5-6+7*8-9", "1+23-45+67+8-9", "1+23*4+5-6-7*8+9", "1+23*4-5-6*7+8-9", "1+23*4-56+7-8+9", "1-2+3+4-5*6+78-9", "1-2+3+45+6-7+8-9", "1-2+3-4-5+6*78/9", "1-2+3-4*5-6+78-9", "1-2+3-45+6-7+89", "1-2+3*4*5-6-7+8-9", "1-2-3+4-5+67-8-9", "1-2-3+4*56/7+8+9", "1-2-3+45-6-7+8+9", "1-2-3*4+5+6+7*8-9", "1-2-3*4+56*7/8+9", "1-2-3*4-5-6+78-9", "1-2-34+5+6+78-9", "1-2-34+56+7+8+9", "1-2-34+56/7+8*9", "1-2-34-5+6+7+8*9", "1-2*3+4+5+6*7+8-9", "1-2*3+4+5-6+7*8-9", "1-2*3+4+56+7-8-9", "1-2*3+4*5+6+7+8+9", "1-2*3+4*5-6*7+8*9", "1-2*3+45-67+8*9", "1-2*3-4+5*6+7+8+9", "1-2*3-4-5+6*7+8+9", "1-2*3-4-5-6+7*8+9", "1-2*3-4-5-6-7+8*9", "1-2*3*4+5-6+78-9", "1-2*3*4-5-6+7+8*9", "1-2*34+5*6-7+89", "1-23+4-5+67-8+9", "1-23+4*5-6*7+89", "1-23+45-67+89", "1-23-4+5+67+8-9", "1-23-4-5-6-7+89", "1-23*4+5+6*7+89", "1*2+3+4+5*6+7+8-9", "1*2+3+4-5+6*7+8-9", "1*2+3+4-5-6+7*8-9", "1*2+3+45+67-8*9", "1*2+3-4*56/7+8*9", "1*2+3-45+6+7+8*9", "1*2+3*4-56+78+9", "1*2+34+5-6-7+8+9", "1*2+34+56-7*8+9", "1*2+34+56/7-8+9", "1*2+34+56/7/8*9", "1*2+34-5+6+7-8+9", "1*2+34-56+7*8+9", "1*2+34-56-7+8*9", "1*2+34-56/7+8+9", "1*2+34*56/7/8+9", "1*2-3+4-5-6*7+89", "1*2-3-4+56-7-8+9", "1*2-3-4*5+67+8-9", "1*2-3*4+5+67-8-9", "1*2-3*4+56/7*8-9", "1*2-34+5*6+7*8-9", "1*2*3+4+5+6+7+8+9", "1*2*3+4+5-6*7+8*9", "1*2*3+4-5*6+7*8+9", "1*2*3+4-5*6-7+8*9", "1*2*3-4*5+6*7+8+9", "1*2*3-4*5-6+7*8+9", "1*2*3-4*5-6-7+8*9", "1*2*3-45+67+8+9", "1*2*3*4+5+6-7+8+9", "1*2*3*4*5-6-78+9", "1*2*34+56-7-8*9", "1*2*34-5+6-7-8-9", "1*23+4*5-6+7-8+9", "1*23-4-56-7+89", "1*23*4+5-6*78/9", "12+3+4-56-7+89", "12+3-4*5+67-8-9", "12+3-45+6+78-9", "12+3*4+5+6-7+8+9", "12+3*45-6-7-89", "12+34-5-6-7+8+9", "12-3+4+56-7-8-9", "12-3+4*5+6-7+8+9", "12-3+4*56/7/8*9", "12-3-4+5*6-7+8+9", "12-3-4+56*7/8-9", "12-3-4-56+7+89", "12-3-45-6+78+9", "12-3*4-5+67-8-9", "12-3*4*5+6+78+9", "12*3+4+5+6-7-8+9", "12*3+4+5-6+7+8-9", "12*3-4-5-6+7+8+9", "12*3-4-56+78-9", "12*3*4-5*6-78+9", "12*3/4+5*6+7+8-9", "12*3/4-5+6*7+8-9", "12*3/4-5-6+7*8-9", "12*3/4*5+6-7-8+9", "12*3/4*5-6+7+8-9", "12/3+4*5*6-7-8*9", "12/3+45+6+7-8-9", "12/3-4-5+67-8-9"};
        String[] s = {"1*2*3*4*5-6-78+9", "1*2*3*4+5+6-7+8+9", "1*2*3+4+5+6+7+8+9", "1*2*3+4+5-6*7+8*9", "1*2*3+4-5*6+7*8+9", "1*2*3+4-5*6-7+8*9", "1*2*3-4*5+6*7+8+9", "1*2*3-4*5-6+7*8+9", "1*2*3-4*5-6-7+8*9", "1*2*3-45+67+8+9", "1*2*34+56-7-8*9", "1*2*34-5+6-7-8-9", "1*2+3*4-56+78+9", "1*2+3+4+5*6+7+8-9", "1*2+3+4-5+6*7+8-9", "1*2+3+4-5-6+7*8-9", "1*2+3+45+67-8*9", "1*2+3-45+6+7+8*9", "1*2+34+5-6-7+8+9", "1*2+34+56-7*8+9", "1*2+34-5+6+7-8+9", "1*2+34-56+7*8+9", "1*2+34-56-7+8*9", "1*2-3*4+5+67-8-9", "1*2-3+4-5-6*7+89", "1*2-3-4*5+67+8-9", "1*2-3-4+56-7-8+9", "1*2-34+5*6+7*8-9", "1*23+4*5-6+7-8+9", "1*23-4-56-7+89", "1+2*3*4*5+6+7-89", "1+2*3*4+5*6+7-8-9", "1+2*3*4-5+6*7-8-9", "1+2*3+4*5*6+7-89", "1+2*3+4*5-6+7+8+9", "1+2*3-4-5-6*7+89", "1+2*34-5*6+7+8-9", "1+2+3*4*5+6-7-8-9", "1+2+3*4+5+6*7-8-9", "1+2+3*45-6-78-9", "1+2+3+4+5+6+7+8+9", "1+2+3+4+5-6*7+8*9", "1+2+3+4-5*6+7*8+9", "1+2+3+4-5*6-7+8*9", "1+2+3-4*5+6*7+8+9", "1+2+3-4*5-6+7*8+9", "1+2+3-4*5-6-7+8*9", "1+2+3-45+67+8+9", "1+2-3*4*5+6+7+89", "1+2-3*4+5*6+7+8+9", "1+2-3*4-5+6*7+8+9", "1+2-3*4-5-6+7*8+9", "1+2-3*4-5-6-7+8*9", "1+2-3+4*5+6*7-8-9", "1+2-3+45+6-7-8+9", "1+2-3+45-6+7+8-9", "1+2-3-4-5*6+7+8*9", "1+2-3-45-6+7+89", "1+2-34+5+6+7*8+9", "1+2-34+5+6-7+8*9", "1+2-34-5-6+78+9", "1+23*4+5-6-7*8+9", "1+23*4-5-6*7+8-9", "1+23*4-56+7-8+9", "1+23+4+5+6+7+8-9", "1+23+4-5*6+7*8-9", "1+23+4-5-67+89", "1+23-4*5+6*7+8-9", "1+23-4*5-6+7*8-9", "1+23-4-5+6+7+8+9", "1+23-4-5-6*7+8*9", "1+23-45+67+8-9", "1-2*3*4+5-6+78-9", "1-2*3*4-5-6+7+8*9", "1-2*3+4*5+6+7+8+9", "1-2*3+4*5-6*7+8*9", "1-2*3+4+5+6*7+8-9", "1-2*3+4+5-6+7*8-9", "1-2*3+4+56+7-8-9", "1-2*3+45-67+8*9", "1-2*3-4+5*6+7+8+9", "1-2*3-4-5+6*7+8+9", "1-2*3-4-5-6+7*8+9", "1-2*3-4-5-6-7+8*9", "1-2*34+5*6-7+89", "1-2+3*4*5-6-7+8-9", "1-2+3+4-5*6+78-9", "1-2+3+45+6-7+8-9", "1-2+3-4*5-6+78-9", "1-2+3-45+6-7+89", "1-2-3*4+5+6+7*8-9", "1-2-3*4-5-6+78-9", "1-2-3+4-5+67-8-9", "1-2-3+45-6-7+8+9", "1-2-34+5+6+78-9", "1-2-34+56+7+8+9", "1-2-34-5+6+7+8*9", "1-23*4+5+6*7+89", "1-23+4*5-6*7+89", "1-23+4-5+67-8+9", "1-23+45-67+89", "1-23-4+5+67+8-9", "1-23-4-5-6-7+89", "12*3*4-5*6-78+9", "12*3+4+5+6-7-8+9", "12*3+4+5-6+7+8-9", "12*3-4-5-6+7+8+9", "12*3-4-56+78-9", "12+3*4+5+6-7+8+9", "12+3*45-6-7-89", "12+3+4-56-7+89", "12+3-4*5+67-8-9", "12+3-45+6+78-9", "12+34-5-6-7+8+9", "12-3*4*5+6+78+9", "12-3*4-5+67-8-9", "12-3+4*5+6-7+8+9", "12-3+4+56-7-8-9", "12-3-4+5*6-7+8+9", "12-3-4-56+7+89", "12-3-45-6+78+9"};

        HashSet<String> set = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();

        for (String str : s1) {
            set.add(str);
        }
        for (String str : s) {
            set2.add(str);
        }
        set.removeAll(set2);
        System.out.println(set.size());
        System.out.println(set);

        System.out.println(expressionAddOperators.addOperators("3456237490", 9191));
//
    }

    //method 1 (O(4^N*N))
    public List<String> addOperators(String num, int target) {
        symbol.push('#');
        List<String> result = new ArrayList<>();
        buildStr(num, 0, new StringBuilder(), target, result);

        return result;
    }


    private void buildStr(String num, int loc, StringBuilder sb, int target, List<String> result) {
        int curSize = sb.length();
        if (num.charAt(loc) == '0') {
            sb.append('0');
            handleAdd(num, loc, sb, target, result);
        } else {
            for (int j = loc; j < num.length(); j++) {
                sb.append(num.charAt(j));
                handleAdd(num, j, sb, target, result);
            }
        }
        sb.delete(curSize, sb.length());
    }

    private void handleAdd(String num, int loc, StringBuilder sb, int target, List<String> result) {
        if (loc + 1 == num.length()) {
            if (check(sb, target)) {
                result.add(sb.toString());
            }
        } else {
            for (int i = 0; i < 3; i++) {
                sb.append(s[i]);
                buildStr(num, loc + 1, sb, target, result);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }


    private boolean check(StringBuilder s, int target) {
        System.out.println(s);
        int loc = 0;
        while (loc < s.length()) {
            loc = parseNumber(s, loc);
            //System.out.println(number);
            if (loc != s.length()) {
                parseSymbol(s.charAt(loc));
                if (!canCompute) {
                    clear();
                    return false;
                }
                loc++;
            }
        }

        completeCompute();
        if (!canCompute) {
            clear();
            return false;
        }

        long res = number.pop();
        //System.out.println(res);
        boolean result = res == target;
        clear();
        return result;
    }

    private int priority(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '#':
                return 0;
        }

        return -1;
    }

    private void completeCompute() {
        while (symbol.size() > 1) {
            long b = number.pop();
            long a = number.pop();
            number.push(compute(a, b, symbol.pop()));
            if (!canCompute) {
                return;
            }
        }
    }

    private long compute(long a, long b, char c) {
        switch (c) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0 || a % b != 0) {
                    canCompute = false;
                    return -1;
                }
                return a / b;
        }

        return -1;
    }

    private void parseSymbol(char c) {
        while (priority(symbol.peek()) >= priority(c)) {
            long b = number.pop();
            long a = number.pop();
            number.push(compute(a, b, symbol.pop()));
            if (!canCompute) {
                return;
            }
        }

        symbol.push(c);
    }


    private int parseNumber(StringBuilder s, int loc) {
        for (int i = loc; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                number.push(Long.valueOf(s.substring(loc, i)));
                return i;
            }
        }

        number.push(Long.valueOf(s.substring(loc)));
        return s.length();
    }

    private void clear() {
        number.clear();
        symbol.clear();
        symbol.push('#');
        canCompute = true;
    }

    // ************************************************************************************
    // method 2 (O(4^N))
    public List<String> addOperators2(String num, int target) {
        List<String> result = new ArrayList<>();
        buildStrUseMemo(num, 0, new StringBuilder(), target, result, 0, 0, 0);

        return result;
    }


    private void buildStrUseMemo(String num, int loc, StringBuilder sb, int target, List<String> result, int beforeOp, long before, long cur) {
        int curSize = sb.length();
        char op = curSize == 0 ? '+' : sb.charAt(sb.length() - 1);
        if (num.charAt(loc) == '0') {
            sb.append('0');
            handleAddUseMemo(num, loc, sb, target, result, beforeOp, before, op, cur, 0);
        } else {
            for (int j = loc; j < num.length(); j++) {
                sb.append(num.charAt(j));
                handleAddUseMemo(num, j, sb, target, result, beforeOp, before, op, cur, Long.parseLong(num.substring(loc, j + 1)));
            }
        }
        sb.delete(curSize, sb.length());
    }

    private void handleAddUseMemo(String num, int loc, StringBuilder sb, int target, List<String> result, int beforeOP, long before, char op, long cur, long number) {
        switch (op) {
            case '+':
                cur = cur + number;
                before = number;
                beforeOP = 1;
                break;
            case '-':
                cur = cur - number;
                before = number;
                beforeOP = 2;
                break;
            case '*':
                if (beforeOP == 1) {
                    cur = cur - before;
                    cur += before * number;
                } else if (beforeOP == 2) {
                    cur = cur + before;
                    cur -= before * number;
                }
                before = before * number;
                break;
        }
        if (loc + 1 == num.length()) {
            if (cur == target) {
                result.add(sb.toString());
            }
        } else {
            for (int i = 0; i < 3; i++) {
                sb.append(s[i]);
                buildStrUseMemo(num, loc + 1, sb, target, result, beforeOP, before, cur);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    // ************************************************************************************
    //method 3, eliminate complex stringBuilder and string
    public List<String> addOperators3(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num.isEmpty()) {
            return result;
        }
        long cur = 0;
        char[] path = new char[num.length() << 1];
        int len = 0;
        for (int i = 0; i < num.length(); i++) {
            cur = cur * 10 + num.charAt(i) - '0';
            path[len++] = num.charAt(i);
            recursive(num, target, path, len, i + 1, result, 0, cur);

            if (num.charAt(0) == '0') {
                break;
            }
        }

        return result;
    }

    private void recursive(String num, int target, char[] path, int len, int loc, List<String> result, long left, long cur) {
        if (loc == num.length()) {
            if (left + cur == target) {
                result.add(new String(path, 0, len));
            }
            return;
        }

        long number = 0;
        int size = len + 1;
        for (int i = loc; i < num.length(); i++) {
            char c = num.charAt(i);
            number = number * 10 + c - '0';
            path[size++] = c;

            path[len] = '+';
            recursive(num, target, path, size, i + 1, result, left + cur, number);

            path[len] = '-';
            recursive(num, target, path, size, i + 1, result, left + cur, -number);

            path[len] = '*';
            recursive(num, target, path, size, i + 1, result, left, cur * number);


            if (num.charAt(loc) == '0') {
                break;
            }
        }
    }
}
