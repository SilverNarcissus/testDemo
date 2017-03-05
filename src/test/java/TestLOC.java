import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by SilverNarcissus on 16/10/31.
 */
public class TestLOC {

    @Test
    public void testFail() {

    }

    @Test
    public void testEqual() {
        assertEquals("23", "23");
    }

    @Test
    public void testMul() {
        long x = 1;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            x = x * 2;
        }
        System.out.println(x);
    }

    @Test
    public void testMove() {
        long x = 1;
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            x = x << 2;
        }
        System.out.println(x);
    }

    @Test
    public void testPrintNano(){
        System.out.println(System.nanoTime()/1000);
    }
}
