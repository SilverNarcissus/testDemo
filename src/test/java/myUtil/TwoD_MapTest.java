package myUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/1/23.
 */
public class TwoD_MapTest {
    @Test
    public void get1() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        assertEquals(twoD_map.get("s1", "s2").intValue(), 1);
    }

    @Test
    public void get2() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        assertEquals(twoD_map.get("s1", "s"), null);
    }

    @Test
    public void get3() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        assertEquals(twoD_map.get("s2", "s"), null);
    }

    @Test
    public void remove1() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.remove("s1", "s2");
        assertEquals(twoD_map.get("s1", "s2"), null);
    }

    @Test
    public void remove2() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.remove("s1", "s");
        assertEquals(twoD_map.get("s1", "s2").intValue(), 1);
    }

    @Test
    public void remove3() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        assertEquals(twoD_map.remove("s1", "s2").intValue(), 1);
    }

    @Test
    public void remove4() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        assertEquals(twoD_map.remove("s2", "s2"), null);
    }

    @Test
    public void allElementSize() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s1", "s4", 1);
        assertEquals(twoD_map.allElementSize(), 3);
    }

    @Test
    public void elementSize1() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s1", "s4", 1);
        assertEquals(twoD_map.elementSizeByFirstKey("s1"), 3);
    }

    @Test
    public void elementSize2() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s1", "s4", 1);
        assertEquals(twoD_map.elementSizeByFirstKey("s2"), 0);
    }

    @Test
    public void elementSize3() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 1);
        assertEquals(twoD_map.elementSizeByFirstKey("s2"), 1);
    }

    @Test
    public void elementSize4() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 1);
        assertEquals(twoD_map.elementSizeBySecondKey("s3"), 1);
    }

    @Test
    public void elementSize5() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 1);
        assertEquals(twoD_map.elementSizeBySecondKey("s1"), 0);
    }

    @Test
    public void getElementByFirstKey1() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 1);
        for (Integer integer : twoD_map.getElementByFirstKey("s1")) {
            assertEquals(integer.intValue(), 1);
        }
    }

    @Test
    public void getElementByFirstKey2() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 2);
        twoD_map.put("s2", "s4", 1);
        for (Integer integer : twoD_map.getElementByFirstKey("s3")) {
            fail();
        }
    }

    @Test
    public void getElementBySecondKey1() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 10);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 1);
        for (Integer integer : twoD_map.getElementBySecondKey("s2")) {
            assertEquals(integer.intValue(), 10);
        }
    }

    @Test
    public void getElementBySecondKey2() throws Exception {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 1);
        for (Integer integer : twoD_map.getElementBySecondKey("s1")) {
            fail();
        }
    }

    @Test
    public void removeElementsByFirstKey1() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 3);
        for (Integer i : twoD_map.removeElementsByFirstKey("s1").values()) {
            assertEquals(i.intValue(), 1);
        }
        assertEquals(twoD_map.allElementSize(), 1);
        assertEquals(twoD_map.elementSizeByFirstKey("s1"), 0);
    }

    @Test
    public void removeElementsByFirstKey2() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 3);
        twoD_map.removeElementsByFirstKey("s1");

        assertEquals(twoD_map.elementSizeBySecondKey("s2"), 0);
    }

    @Test
    public void removeElementsByFirstKey3() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s1", "s3", 1);
        twoD_map.put("s2", "s4", 3);
        assertEquals(null,twoD_map.removeElementsByFirstKey("s3"));
        assertEquals(twoD_map.elementSizeBySecondKey("s2"), 1);
    }

    @Test
    public void removeElementsBySecondKey1() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 10);
        twoD_map.put("s3", "s2", 10);
        twoD_map.put("s2", "s4", 3);
        for (Integer i : twoD_map.removeElementsBySecondKey("s2").values()) {
            assertEquals(i.intValue(), 10);
        }
        assertEquals(twoD_map.allElementSize(), 1);
        assertEquals(twoD_map.elementSizeBySecondKey("s2"), 0);
    }

    @Test
    public void removeElementsBySecondKey2() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s2", "s2", 1);
        twoD_map.put("s3", "s2", 1);
        twoD_map.put("s2", "s4", 3);
        twoD_map.removeElementsBySecondKey("s2");

        assertEquals(1, twoD_map.elementSizeByFirstKey("s2"));
    }

    @Test
    public void removeElementsBySecondKey3() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s2", "s2", 1);
        twoD_map.put("s3", "s2", 1);
        twoD_map.put("s2", "s4", 3);
        assertEquals(null,twoD_map.removeElementsBySecondKey("s3"));
        assertEquals(2, twoD_map.elementSizeByFirstKey("s2"));
    }


    @Test
    public void clean1() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s2", "s3", 2);
        twoD_map.put("s3", "s4", 3);
        twoD_map.removeElementsBySecondKey("s2");

        assertEquals(1, twoD_map.clean());
        assertEquals(2,twoD_map.allElementSize());
    }

    @Test
    public void clean2() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s2", "s3", 2);
        twoD_map.put("s3", "s4", 3);
        twoD_map.remove("s1", "s2");

        assertEquals(1, twoD_map.clean());
        assertEquals(2,twoD_map.allElementSize());
    }

    @Test
    public void clean3() {
        TwoD_Map<String, Integer> twoD_map = new TwoD_Map<String, Integer>();
        twoD_map.put("s1", "s2", 1);
        twoD_map.put("s2", "s3", 2);
        twoD_map.put("s3", "s4", 3);
        twoD_map.removeElementsByFirstKey("s1");

        assertEquals(0, twoD_map.clean());
    }
}