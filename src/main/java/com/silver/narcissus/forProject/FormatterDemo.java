package com.silver.narcissus.forProject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Created by SilverNarcissus on 2017/3/31.
 */
public class FormatterDemo {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy",new Locale("en"));
        LocalDate parsedDate = LocalDate.parse("1 Mar 2017", formatter);
        System.out.println(parsedDate);
    }
}
