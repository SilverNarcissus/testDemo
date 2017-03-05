package myUtil;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/2/15.
 */
public class Stair_Step_Access_TablesTest {
    static Stair_Step_Access_Tables stair_step_access_tables=new Stair_Step_Access_Tables();
    @Test
    public void getGrade1() throws Exception {
        assertEquals("F", stair_step_access_tables.getGrade(49));
    }

    @Test
    public void getGrade2() throws Exception {
        assertEquals("F", stair_step_access_tables.getGrade(0));
    }

    @Test
    public void getGrade3() throws Exception {
        assertEquals("D", stair_step_access_tables.getGrade(50));
    }

    @Test
    public void getGrade4() throws Exception {
        assertEquals("B", stair_step_access_tables.getGrade(75));
    }

    @Test
    public void getGrade5() throws Exception {
        assertEquals("B", stair_step_access_tables.getGrade(80));
    }

    @Test
    public void getGrade6() throws Exception {
        assertEquals("A", stair_step_access_tables.getGrade(95));
    }

    @Test
    public void getGrade7() throws Exception {
        assertEquals("A", stair_step_access_tables.getGrade(100));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getGrade() throws Exception {
        assertEquals("E",stair_step_access_tables.getGrade(-1));
    }
}