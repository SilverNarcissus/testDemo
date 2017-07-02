package lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/3/19.
 */
public class SummaryTest {
    @Test
    public void avg() throws Exception {
        ArrayList<Data> data = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            data.add(new Data(i));
        }
        System.out.println(Summary.avg(data));
    }

    @Test
    public void avg2() throws Exception {
        ArrayList<Data> data = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            data.add(new Data(i));
        }
        System.out.println(Summary.avg2(data));
    }

}