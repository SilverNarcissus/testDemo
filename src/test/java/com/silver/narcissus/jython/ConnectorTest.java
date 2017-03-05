package com.silver.narcissus.jython;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/2/27.
 */
public class ConnectorTest {

    @Test
    public void executeCommand() throws Exception {
        ArrayList<String> integers = new ArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            integers.add("123");
        }
        List<String> ints = new ArrayList<String>();
        ints = integers.stream().filter(x -> x.compareTo("12") > 0).map(x -> "1").collect(Collectors.toList());
    }

    @Test
    public void executeFile() throws Exception {
        ArrayList<String> integers = new ArrayList<String>();
        for (int i = 0; i < 1000000; i++) {
            integers.add("123");
        }
        List<String> ints = new ArrayList<String>();
        for (String x : integers)
            if (x.compareTo("12") > 0) {
                ints.add(x);
            }
    }

    @Test
    public void executeFunction() throws Exception {
        Process process=Runtime.getRuntime().exec("Python ");
    }

}