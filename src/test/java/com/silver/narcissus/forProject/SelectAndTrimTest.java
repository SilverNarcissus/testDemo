package com.silver.narcissus.forProject;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SilverNarcissus on 2017/3/2.
 */
public class SelectAndTrimTest {
    SelectAndTrim selectAndTrim = new SelectAndTrim("/Users/SilverNarcissus/Documents/软工三大作业/top_1000_movies.txt");

    @Test
    public void trimTxt() throws Exception {
        selectAndTrim.trimTxt();
    }

    @Test
    public void find1() {
        selectAndTrim.findReviewByMovieId("B001HN68ZU");
//        selectAndTrim.findMoviesById("B004ASK9J0");
        //B000MV9026
    }

    @Test
    public void find2() {
        selectAndTrim.findReviewByMovieId("B000MV9026");
    }

    @Test
    public void find3() {
        selectAndTrim.findReviewsByUserId("A1S749MYKSGHQE");
    }

    @Test
    public void te() {
        System.out.println(Thread.activeCount());
    }
}