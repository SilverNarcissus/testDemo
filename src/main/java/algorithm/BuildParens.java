package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by SilverNarcissus on 2019/3/23.
 */
public class BuildParens {
    public static void main(String[] args) {
        BuildParens buildParens = new BuildParens();
        List<String> result = buildParens.build(10);
        System.out.println(result.size());
        System.out.println(new HashSet<>(result).size());
    }

    private List<String> build(int count){
        List<String> result = new ArrayList<>();
        buildRecursive(0, count, count, result, new StringBuilder());
        return result;
    }

    private void buildRecursive(int left, int leftRemain, int rightRemain, List<String> result, StringBuilder sb){
        if(left < 0 || leftRemain < 0 || rightRemain < 0){
            return;
        }

        if(leftRemain == rightRemain && leftRemain == 0){
            result.add(sb.toString());
        }

        sb.append('(');
        buildRecursive(left + 1, leftRemain - 1, rightRemain, result, sb);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(')');
        buildRecursive(left - 1, leftRemain, rightRemain - 1, result, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
}
