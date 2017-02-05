package concurrent;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/1/30.
 * 1.0 version on 2017/1/30.
 */
public class CyclicBarrierTestTest {
    @Test
    public void concurrent() throws Exception {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        cyclicBarrierTest.concurrent();
    }

    @Test
    public void single() throws Exception {
        CyclicBarrierTest cyclicBarrierTest = new CyclicBarrierTest();
        cyclicBarrierTest.single();
    }

}