package algorithm;

/**
 * Created by SilverNarcissus on 2019/1/6.
 */
public class RationalNumberCompare {
    public static void main(String[] args) {
        RationalNumberCompare solution = new RationalNumberCompare();
        System.out.println(solution.isRationalEqual("0.08(999999)", "0.09(0)"));
//        System.out.println(Arrays.toString("123".split("\\(")));
//        System.out.println("123".split("\\(").length);
        System.out.println(-12 % 10);
    }

    public boolean isRationalEqual(String S, String T) {
        RationalNumber r1 = new RationalNumber(S);
        RationalNumber r2 = new RationalNumber(T);

        System.out.println(r1);
        System.out.println(r2);
        return r1.equals(r2);
    }
}

class RationalNumber {
    int int_part = 0;
    String last_part;
    int align = 8;
    int repeat_length = 6;

    public RationalNumber(String S) {
        String[] s_part = S.split("\\.");
        int_part = Integer.parseInt(s_part[0]);
        if (s_part.length == 1) {
            last_part = align("0", null);
        } else {
            String[] behind_part = s_part[1].split("\\(");
            //System.out.println(Arrays.toString(behind_part));
            if (behind_part.length == 1) {
                last_part = align(behind_part[0], null);
            } else {
                last_part = align(behind_part[0], behind_part[1].substring(0, behind_part[1].length() - 1));
            }
        }

        approximate();
    }

    private void approximate() {
        if (last_part.substring(last_part.length() - repeat_length).equals(buildRepeat("9", repeat_length))) {
            int last = Integer.parseInt(last_part) + 1;
            last_part = String.valueOf(last);
            int n = last_part.length();
            if (last_part.length() > align) {
                int_part++;
                last_part = align("0", "0");
            } else {
                last_part = buildRepeat("0", align - n) + last_part;
            }
        }
    }

    private String buildRepeat(String repeat, int len) {
        StringBuilder sb = new StringBuilder();
        int loc = 0;
        for (int i = 0; i < len; i++) {
            sb.append(repeat.charAt(loc++));
            if (loc >= repeat.length()) {
                loc = 0;
            }
        }

        return sb.toString();
    }

    private String align(String before, String repeat) {
        //System.out.println(before);
        int n = before.length();
        if (repeat == null) {
            before += buildRepeat("0", align - n);
        } else {
            before += buildRepeat(repeat, align - n);
        }

        return before;
    }

    @Override
    public boolean equals(Object obj) {
        RationalNumber other = (RationalNumber) obj;
        return int_part == other.int_part && last_part.equals(other.last_part);
    }

    @Override
    public String toString() {
        return int_part + "." + last_part;
    }
}