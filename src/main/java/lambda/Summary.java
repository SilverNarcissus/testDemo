package lambda;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;

/**
 * Created by SilverNarcissus on 2017/3/19.
 */
public class Summary {
    public static double avg(Collection<Data> collection) {
        return getSummary(collection).getAverage();
    }

    public static double max(Collection<Data> collection) {
        return getSummary(collection).getMax();
    }

    public static double min(Collection<Data> collection) {
        return getSummary(collection).getMin();
    }

    public static double sum(Collection<Data> collection) {
        return getSummary(collection).getSum();
    }

    private static DoubleSummaryStatistics getSummary(Collection<Data> collection) {
        return collection.stream().mapToDouble(Data::getPrice).summaryStatistics();
    }

    public static double avg2(Collection<Data> collection) {
        double result = 0;
        for (Data d : collection) {
            result += d.getPrice();
        }
        return result / collection.size();
    }
}
