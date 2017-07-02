package com.silver.narcissus.forProject;

import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/3/7.
 */
public class TestTest {
    @Test
    public void getHigher() throws Exception{
        com.silver.narcissus.forProject.Test test=new com.silver.narcissus.forProject.Test();
        System.out.println(test.getHigher(293753));
    }

}