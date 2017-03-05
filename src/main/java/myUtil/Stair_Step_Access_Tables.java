package myUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by SilverNarcissus on 2017/2/15.
 * 1.0 version on 2017/2/15.
 */
public class Stair_Step_Access_Tables {
    private List<Integer> rangeLimit;
    private List<String> grade;

    public Stair_Step_Access_Tables() {
        rangeLimit = Arrays.asList(50, 65, 75, 90, 100);
        grade = Arrays.asList("F", "D", "C", "B", "A");
    }

    public String getGrade(int score) {
        rangeCheck(score);
        return grade.get(getGradeIndex(score));
    }

    /**
     * check the range of the score
     *
     * @param score the score of someone
     */
    private void rangeCheck(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("the score should between 0~100 but it's:" + score);
        }
    }

    /**
     * use liner method to find the index of grade, can be replaced by binary-search
     *
     * @param score the score of someone
     * @return the index of the grade
     */
    private int getGradeIndex(int score) {
        //for special case
        if (score == 100) {
            return rangeLimit.size() - 1;
        }
        //
        for (int index = 0; index < rangeLimit.size(); index++) {
            if (score < rangeLimit.get(index)) {
                return index;
            }
        }
        assert false : "shouldn't go here because we have already check the range";
        return -1;
    }
}
