package myUtil;

import java.util.Comparator;

/**
 * Created by SilverNarcissus on 2017/1/12.
 */
public class ComparatorCollection {
    private static class StringComparator implements Comparator<String>{

        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }


    }

    public static final StringComparator STRING_COMPARATOR=new StringComparator();

}
