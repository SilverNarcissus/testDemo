import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by SilverNarcissus on 16/10/31.
 */
public class TestLOC {

    @Test
    public void testFail(){
        fail();
    }
    @Test
    public  void testEqual(){
        assertEquals("23","2");
    }
}
